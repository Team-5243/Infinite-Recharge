/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WheelOfFortuneSubsystem extends SubsystemBase {

  Servo arm;
  CANSparkMax wheelSpin;

  /**
   * Creates a new WheelOfFortuneSubsystem.
   */
  public WheelOfFortuneSubsystem() {
    arm = new Servo(Constants.ARM_RETRACT);
    wheelSpin = new CANSparkMax(Constants.WHEEL_SPINNER, MotorType.kBrushless); 
  }

  public void armMovement(double angle) {
    arm.setAngle(angle); 
  }

  public void spinWheel(double power) {
    wheelSpin.set(power);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
}
