/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class ClimbSubsystem extends PIDSubsystem {
  private CANSparkMax lowerJoint, upperJoint;
  private CANSparkMax upperWinch, lowerWinch;

  private double power;

  /**
   * Creates a new ClimbSubsystem.
   */
  public ClimbSubsystem() {
    super(new PIDController(Constants.kP_CLIMB_SYNCHRONIZE, Constants.kI_CLIMB_SYNCHRONIZE, Constants.kD_CLIMB_SYNCHRONIZE));
    lowerJoint = new CANSparkMax(Constants.LOW_CLIMB_JOINT, MotorType.kBrushless);
    upperJoint = new CANSparkMax(Constants.LOW_CLIMB_JOINT, MotorType.kBrushless);
    upperWinch     = new CANSparkMax(Constants.UPPER_WINCH_CLIMB, MotorType.kBrushless);
    lowerWinch     = new CANSparkMax(Constants.LOWER_WINCH_CLIMB, MotorType.kBrushless);
    power          = 0d;
  }

  //TODO: Find proper encoder constant

  public double getLowerAngle() {
    //0.733038 = 40 deg in rad
    return 0.733038+((lowerJoint.getEncoder().getPosition()/Constants.NEO_ENCODER_PULSES_PER_REVOLUTION)*(2*Math.PI));
  }

  public double getUpperAngle() {
    return -getLowerAngle() + ((upperJoint.getEncoder().getPosition()/Constants.NEO_ENCODER_PULSES_PER_REVOLUTION)*(2*Math.PI));
  }

  public void setPower(double power) {
    this.power = power;
  }

  public void runLowerJoint(double power) {
    lowerJoint.set(power);
  }

  public void runUpperJoint(double power) {
    upperJoint.set(power);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    lowerWinch.set(power + output);
    upperWinch.set(power - output);
  }

  @Override
  public double getMeasurement() {
    return (upperWinch.getEncoder().getPosition() - lowerWinch.getEncoder().getPosition()) / Constants.WINCH_CLIMB_ENCODER_PULSES_PER_INCH;
  }

  public void switchEnable() {
    if(isEnabled()) {
      disable();
    } else {
      enable();
    }
  }
}
