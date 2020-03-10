/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class OuttakeSubsystem extends SubsystemBase {
  CANSparkMax leftFly, rightFly;
  CANSparkMax m_rightConveyor, m_leftConveyor;
  
  public OuttakeSubsystem() {
    m_leftConveyor = new CANSparkMax(Constants.LEFT_CONVEYOR, MotorType.kBrushed);
    m_rightConveyor = new CANSparkMax(Constants.RIGHT_CONVEYOR, MotorType.kBrushed);

    m_leftConveyor.setInverted(true);

    leftFly = new CANSparkMax(Constants.LEFT_FLY, MotorType.kBrushed);
    rightFly = new CANSparkMax(Constants.RIGHT_FLY, MotorType.kBrushed);

    leftFly.setIdleMode(IdleMode.kCoast);
    rightFly.setIdleMode(IdleMode.kCoast);

    leftFly.follow(rightFly, false);
  }

  public void runConveyor(double leftPower, double rightPower) {
    m_leftConveyor.set(leftPower);
    m_rightConveyor.set(rightPower);
  }

  public void stopConveyor() {
    runConveyor(0, 0);
  }
  
  public void outtake(double outSpeed){
    m_leftConveyor.set(0.7);
    m_rightConveyor.set(0.7);
    rightFly.set(outSpeed);
  }

  public void conveyorIntake() {
    m_leftConveyor.set(-0.5);
    m_rightConveyor.set(-0.5);
  }

  public void unstuck() {
    m_leftConveyor.set(-0.5);
    m_rightConveyor.set(0.5);
  }

  public void stopOuttake() {
    rightFly.set(0);
    stopConveyor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
