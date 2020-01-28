/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeOutakeSubsystem extends SubsystemBase {

  WPI_TalonSRX roller, conveyer, leftFly, rightFly;
  
  public IntakeOutakeSubsystem() {
    roller = new WPI_TalonSRX(Constants.BACK_LEFT);
    conveyer = new WPI_TalonSRX(Constants.CONVEYER);
    leftFly = new WPI_TalonSRX(Constants.LEFT_FLY);
    rightFly = new WPI_TalonSRX(Constants.RIGHT_FLY);

    leftFly.follow(rightFly);
    rightFly.setInverted(true);
  }

 public void intake(double inPower, double tread) {
    roller.set(inPower);
    conveyer.set(tread);
  }
  
  public void outtake(double outSpeed, double tread){
    conveyer.set(tread);
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
  
  conveyer.set(0);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
