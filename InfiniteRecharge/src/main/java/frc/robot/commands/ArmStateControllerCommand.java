/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimbSubsystem;
import frc.states.ClimbArmsStateMachine;

public class ArmStateControllerCommand extends CommandBase {
  ClimbSubsystem m_climbSubsystem;
  ClimbArmsStateMachine.State m_state;

  /**
   * Creates a new ArmStateControllerCommand.
   */
  public ArmStateControllerCommand(ClimbSubsystem subsystem, ClimbArmsStateMachine.State state) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_climbSubsystem = subsystem;
    m_state = state;
    addRequirements(m_climbSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ClimbArmsStateMachine.State oldState = ClimbArmsStateMachine.getState();
    ClimbArmsStateMachine.updateState(m_state);
    System.out.println("old state: " + (oldState == ClimbArmsStateMachine.State.INIT ? "init" : "not init"));
    if((m_climbSubsystem.getLowerProfile() != null && m_climbSubsystem.getUpperProfile() != null &&
      m_climbSubsystem.getLowerProfile().isDone() && m_climbSubsystem.getUpperProfile().isDone()) ||
        (m_climbSubsystem.getLowerProfile() == null && m_climbSubsystem.getUpperProfile() == null)) {
      if(oldState.equals(ClimbArmsStateMachine.State.INIT)) {
        if(m_state.equals(ClimbArmsStateMachine.State.CLIMB)) {
          System.out.println("entered Climb motion profile");
          m_climbSubsystem.setLowerProfile(Constants.CLIMB_LOWER_INIT_TO_CLIMB_PROFILE);
          m_climbSubsystem.setUpperProfile(Constants.CLIMB_UPPER_INIT_TO_CLIMB_PROFILE);
        }
      } else if(oldState.equals(ClimbArmsStateMachine.State.IDLE)) {
        if(m_state.equals(ClimbArmsStateMachine.State.INTAKE)) {
          m_climbSubsystem.setLowerProfile(Constants.CLIMB_LOWER_IDLE_TO_INTAKE_PROFILE);
          m_climbSubsystem.setUpperProfile(Constants.CLIMB_UPPER_IDLE_TO_INTAKE_PROFILE);
        } else if(m_state.equals(ClimbArmsStateMachine.State.CLIMB)) {
          m_climbSubsystem.setLowerProfile(Constants.CLIMB_LOWER_IDLE_TO_CLIMB_PROFILE);
          m_climbSubsystem.setUpperProfile(Constants.CLIMB_UPPER_IDLE_TO_CLIMB_PROFILE);
        }
      } else if(oldState.equals(ClimbArmsStateMachine.State.INTAKE)) {
        if(m_state.equals(ClimbArmsStateMachine.State.IDLE)) {
          m_climbSubsystem.setLowerProfile(Constants.CLIMB_LOWER_INTAKE_TO_IDLE_PROFILE);
          m_climbSubsystem.setUpperProfile(Constants.CLIMB_UPPER_INTAKE_TO_IDLE_PROFILE);
        }
      } else if(oldState.equals(ClimbArmsStateMachine.State.CLIMB)) {
        if(m_state.equals(ClimbArmsStateMachine.State.IDLE)) {
          m_climbSubsystem.setLowerProfile(Constants.CLIMB_LOWER_CLIMB_TO_IDLE_PROFILE);
          m_climbSubsystem.setUpperProfile(Constants.CLIMB_UPPER_CLIMB_TO_IDLE_PROFILE);
        }
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
