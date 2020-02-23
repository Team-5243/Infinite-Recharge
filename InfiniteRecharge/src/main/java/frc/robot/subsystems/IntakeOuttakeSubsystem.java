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
  CANSparkMax roller, leftConveyor, rightConveyor;
  CANSparkMax leftFly;
  WPI_TalonSRX rightFly;
  
  public IntakeOuttakeSubsystem() {
    roller = new CANSparkMax(Constants.ROLLER, MotorType.kBrushed);
    leftConveyor = new CANSparkMax(Constants.LEFT_CONVEYOR, MotorType.kBrushed);
    rightConveyor = new CANSparkMax(Constants.RIGHT_CONVEYOR, MotorType.kBrushed);
    leftFly = new CANSparkMax(Constants.LEFT_FLY, MotorType.kBrushed);
    rightFly = new WPI_TalonSRX(Constants.RIGHT_FLY);

  }

  public void runConveyor(double leftPower, double rightPower) {
    leftConveyor.set(leftPower);
    rightConveyor.set(rightPower);
  }

  public void intake(double inPower) {
      roller.set(inPower);
  }
  
  public void outtake(double outSpeed){
    leftConveyor.set(0.3);
    rightConveyor.set(0.3);

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
    rightConveyor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
