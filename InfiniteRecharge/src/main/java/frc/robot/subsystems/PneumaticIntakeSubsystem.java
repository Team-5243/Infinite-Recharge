/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class PneumaticIntakeSubsystem extends SubsystemBase {
  //private DoubleSolenoid m_leftSolenoid, m_rightSolenoid;
  private Compressor m_compressor;
  private CANSparkMax m_roller;

  /**
   * Creates a new PneumaticIntakeSubsystem.  
   */
  public PneumaticIntakeSubsystem() {
    //m_leftSolenoid = new DoubleSolenoid(0,1);
    //m_rightSolenoid = new DoubleSolenoid(0,1);

    m_roller = new CANSparkMax(Constants.ROLLER, MotorType.kBrushed);
    try {
			//m_compressor = new Compressor();
			//m_compressor.start();
    } 
    catch (Exception e) {
			e.printStackTrace();
    }

  }

  public void runIntake(double power) {
    m_roller.set(power);
  }

  public void stopIntake() {
    m_roller.set(0);
  }
  /*
  public void toggleLeftSol() {
		if (m_leftSolenoid.get().equals(Value.kReverse) || m_leftSolenoid.get().equals(Value.kOff)) {
			m_leftSolenoid.set(Value.kForward);
		} else {
			m_leftSolenoid.set(Value.kReverse);
		}
	}
	
	public void toggleRightSol() {
		if (m_rightSolenoid.get().equals(Value.kReverse) || m_rightSolenoid.get().equals(Value.kOff)) {
			m_rightSolenoid.set(Value.kForward);
		} else {
			m_rightSolenoid.set(Value.kReverse);
		}
	}
	
	public void setClosedLoopControl(boolean on) {
		if (m_compressor != null)
			m_compressor.setClosedLoopControl(on);
	}
	
	public void disableCompressor() {
		m_compressor.stop();
  }
  
	public boolean compressorEnabled() {
		return m_compressor.enabled();
  }

  public void setLeftSolenoid(Value direction) {
    m_leftSolenoid.set(direction);
  }
  
  public void setRightSolenoid(Value direction) {
    m_rightSolenoid.set(direction);
  }
  
  public DoubleSolenoid getLeftSolenoid() {
		return m_leftSolenoid;
  }
  
	public DoubleSolenoid getRightSolenoid() {
		return m_rightSolenoid;
  }
  
  public Compressor getCompressor() {
    return m_compressor;
  }
  */

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
