/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeOuttakeSubsystem extends SubsystemBase {

  WPI_TalonSRX roller, leftConveyor, rightConveyor;
  CANSparkMax leftFly, rightFly;
  
  public IntakeOuttakeSubsystem() {
    roller = new WPI_TalonSRX(Constants.BACK_LEFT);
    leftConveyor = new WPI_TalonSRX(Constants.LEFT_CONVEYOR);
    rightConveyor = new WPI_TalonSRX(Constants.RIGHT_CONVEYOR);
    leftFly = new CANSparkMax(Constants.LEFT_FLY, MotorType.kBrushless);
    rightFly = new CANSparkMax(Constants.RIGHT_FLY, MotorType.kBrushless);

    rightConveyor.follow(leftConveyor);

    leftFly.follow(rightFly);
    rightFly.setInverted(true);
  }

  public double getFlyWheelMeasurement() {
    return rightFly.getEncoder().getPosition();
  }

  public double getFlyWheelVelocity() {
    return rightFly.getEncoder().getVelocity();
  }

 public void intake(double inPower) {
    roller.set(inPower);
    leftConveyor.set(0.3);
  }
  
  public void outtake(double outSpeed){
    leftConveyor.set(0.3);
    leftFly.set(outSpeed);
    rightFly.set(outSpeed);
  }

public void stop(boolean in, boolean out) {
  if(in) {
    roller.set(0);
  }
  
  if(out) {
    leftFly.set(0);
    rightFly.set(0);
  }
  
  leftConveyor.set(0);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
