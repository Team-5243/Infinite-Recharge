/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveSubsystem extends SubsystemBase {
  CANSparkMax frontLeft, frontRight, backLeft, backRight;
  AHRS gyro;
  Pose2d estimatedPose;
  Pose2d drivetrainPower;

  /**
   * Creates a new chassis.
   */
  public DriveSubsystem(RobotContainer robotContainer) {
    frontLeft = new CANSparkMax(Constants.FRONT_LEFT, MotorType.kBrushless);
    backLeft = new CANSparkMax(Constants.BACK_LEFT, MotorType.kBrushless);
    frontRight = new CANSparkMax(Constants.FRONT_RIGHT, MotorType.kBrushless);
    backRight = new CANSparkMax(Constants.BACK_RIGHT, MotorType.kBrushless);

    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

    frontRight.setInverted(true);

    gyro = robotContainer.getGyro();
    estimatedPose = new Pose2d();
    drivetrainPower = new Pose2d();
  }

  public AHRS getGyro() {
    return gyro;
  }

  public Pose2d getPose() {
    return estimatedPose;
  }

  public void setMotors(double right, double left) {
    frontLeft.set(left);
    frontRight.set(right);
  }

  public void steerDrive(double drivePower, double steerPower) {
    setDrivetrainPower(new Pose2d(new Translation2d(drivePower, 0d), new Rotation2d(steerPower)));
    //frontLeft.set(drivePower - steerPower);
    //frontRight.set(drivePower + steerPower);
  }

  public void stopMotors() {
    setMotors(0, 0);
  }

  public Pose2d getDrivetrainPower() {
    return drivetrainPower;
  }

  public double getLeftDisplacement() {
    return frontLeft.getEncoder().getPosition() / Constants.NEO_ENCODER_PULSES_PER_REVOLUTION*
      (Constants.DRIVE_WHEEL_CIRCUMFERENCE * Constants.DRIVETRAIN_GEAR_RATIO);
  }

  public void setDrivetrainPower(Pose2d power) {
    drivetrainPower = power;
  }

  public double getDrivePower() {
    return drivetrainPower.getTranslation().getX();
  }

  public double getSteerPower() {
    return drivetrainPower.getRotation().getRadians();
  }

  @Override
  public void periodic() {
    double leftVelocity = (frontLeft.getEncoder().getVelocity()/Constants.NEO_ENCODER_PULSES_PER_REVOLUTION)*
      (Constants.DRIVE_WHEEL_CIRCUMFERENCE * Constants.DRIVETRAIN_GEAR_RATIO);
    double rightVelocity = (frontRight.getEncoder().getVelocity()/Constants.NEO_ENCODER_PULSES_PER_REVOLUTION)*
      (Constants.DRIVE_WHEEL_CIRCUMFERENCE * Constants.DRIVETRAIN_GEAR_RATIO);
    Pose2d negativePoseChange = new Pose2d(new Translation2d(-(leftVelocity + rightVelocity) / 2d, 0d), new Rotation2d((leftVelocity - rightVelocity) / (2d * Constants.DRIVE_WHEEL_SEPARATION_DISTANCE)));
    estimatedPose.minus(negativePoseChange);
  }
}
