/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  CANSparkMax frontLeft, frontRight, backLeft, backRight;

  /**
   * Creates a new chassis.
   */
  public DriveSubsystem() {
    frontLeft = new CANSparkMax(Constants.FRONT_LEFT, MotorType.kBrushless);
    backLeft = new CANSparkMax(Constants.BACK_LEFT, MotorType.kBrushless);
    frontRight = new CANSparkMax(Constants.FRONT_RIGHT, MotorType.kBrushless);
    backRight = new CANSparkMax(Constants.BACK_RIGHT, MotorType.kBrushless);

    backLeft.follow(frontLeft);
    backRight.follow(frontRight);
  }

  public void setMotors(double right, double left) {
    frontLeft.set(left);
    frontRight.set(right);
  }

  public void steerDrive(double drivePower, double steerPower) {
    frontLeft.set(drivePower - steerPower);
    frontRight.set(drivePower + steerPower);
  }

  public void stopMotors() {
    setMotors(0, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
