/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib;

import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class TripleProfiledPIDSubsystem extends SubsystemBase {
  protected final ProfiledPIDController m_controller1;
  protected final ProfiledPIDController m_controller2;
  protected final ProfiledPIDController m_controller3;
  protected boolean m_enabled1;
  protected boolean m_enabled2;
  protected boolean m_enabled3;
  
  private TrapezoidProfile.State m_goal1;
  private TrapezoidProfile.State m_goal2;
  private TrapezoidProfile.State m_goal3;
  
  public TripleProfiledPIDSubsystem(ProfiledPIDController controller1,
                                    ProfiledPIDController controller2,
                                    ProfiledPIDController controller3,
                                    double initialPosition1,
                                    double initialPosition2,
                                    double initialPosition3) {
    m_controller1 = controller1;
    m_controller2 = controller2;
    m_controller3 = controller3;
    setGoal(1, initialPosition1);
    setGoal(2, initialPosition2);
    setGoal(3, initialPosition3);
  }

  public void setGoal(int profiledPIDControllerNumber, TrapezoidProfile.State goal) {
    if(profiledPIDControllerNumber == 1) {
      m_goal1 = goal;
    } else if(profiledPIDControllerNumber == 2) {
      m_goal2 = goal;
    } else if(profiledPIDControllerNumber == 3) {
      m_goal3 = goal;
    }
  }

  public void setGoal(int profiledPIDControllerNumber, double goal) {
    setGoal(profiledPIDControllerNumber, new TrapezoidProfile.State(goal, 0));
  }

  protected abstract void useOutput(int profiledPIDControllerNumber, double output, TrapezoidProfile.State setpoint);
  protected abstract double getMeasurement(int profiledPIDControllerNumber);

  public void enable(int profiledPIDControllerNumber) {
    if(profiledPIDControllerNumber == 1) {
      m_enabled1 = true;
      m_controller1.reset(getMeasurement(1));
    } else if(profiledPIDControllerNumber == 2) {
      m_enabled2 = true;
      m_controller2.reset(getMeasurement(2));
    } else if(profiledPIDControllerNumber == 3) {
      m_enabled3 = true;
      m_controller3.reset(getMeasurement(3));
    }
  }

  public void disable(int profiledPIDControllerNumber) {
    if(profiledPIDControllerNumber == 1) {
      m_enabled1 = false;
      useOutput(1, 0, new TrapezoidProfile.State());
    } else if(profiledPIDControllerNumber == 2) {
      m_enabled2 = false;
      useOutput(2, 0, new TrapezoidProfile.State());
    } else if(profiledPIDControllerNumber == 3) {
      m_enabled3 = false;
      useOutput(3, 0, new TrapezoidProfile.State());
    }
  }

  public boolean isEnabled(int profiledPIDControllerNumber) {
    return profiledPIDControllerNumber == 1 ? m_enabled1 :
          profiledPIDControllerNumber == 2 ? m_enabled2 : m_enabled3;
  }

  @Override
  public void periodic() {
    if(m_enabled1) {
      useOutput(1, m_controller1.calculate(getMeasurement(1)), m_goal1);
    }

    if(m_enabled2) {
      useOutput(2, m_controller1.calculate(getMeasurement(2)), m_goal2);
    }

    if(m_enabled3) {
      useOutput(3, m_controller1.calculate(getMeasurement(3)), m_goal3);
    }
  }
}
