/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile.State;
import frc.lib.TripleProfiledPIDSubsystem;
import frc.robot.Constants;

/**
 * First ProfiledPIDController runs the winches, the second ProfiledPIDController runs
 * the lower joint motors, and the third ProfiledPIDController runs the upper joint.
 */
public class AdvancedClimbSubsystem extends TripleProfiledPIDSubsystem {
  private CANSparkMax upperWinch, lowerWinch;
  private CANSparkMax lowerJoint, upperJoint;
  private Superstructure superstructure;

  private double winchPower;
  private double previousLowerVelocity, previousUpperVelocity;

  /**
   * Creates a new AdvancedClimbSubsystem.
   */
  public AdvancedClimbSubsystem(Superstructure superstructure) {
    super(new ProfiledPIDController(0, 0, 0, new TrapezoidProfile.Constraints(0, 0)),
          new ProfiledPIDController(0, 0, 0, new TrapezoidProfile.Constraints(0, 0)),
          new ProfiledPIDController(0, 0, 0, new TrapezoidProfile.Constraints(0, 0)),
          0d, 0d, 0d);
    upperWinch = new CANSparkMax(Constants.UPPER_WINCH_CLIMB, MotorType.kBrushless);
    lowerWinch = new CANSparkMax(Constants.LOWER_WINCH_CLIMB, MotorType.kBrushless);
    lowerJoint = new CANSparkMax(Constants.LOWER_CLIMB_JOINT, MotorType.kBrushed);
    upperJoint = new CANSparkMax(Constants.UPPER_CLIMB_JOINT, MotorType.kBrushed);

    this.superstructure = superstructure;
    winchPower = 0d;
    previousLowerVelocity = 0d;
    previousUpperVelocity = 0d;
  }

  @Override
  protected void useOutput(int profiledPIDControllerNumber, double output, State setpoint) {
    double lowerAngularVelocity = getLowerVelocity();
    double upperAngularVelocity = getUpperVelocity();
    double lowerAngularAcceleration = (lowerAngularVelocity - previousLowerVelocity) / 200;
    double upperAngularAcceleration = (upperAngularVelocity - previousUpperVelocity) / 200;
    if(profiledPIDControllerNumber == 1) {
      upperWinch.set(winchPower + output);
      lowerWinch.set(winchPower - output);
    } else if(profiledPIDControllerNumber == 2) {
      if(superstructure.isClimbSafe(getLowerAngle(), getUpperAngle())) {
        lowerJoint.set(Constants.kF_CLIMB_LOWER_BAR * Constants.DUAL_JOINTED_ARM_DYNAMIC_MODEL.getLowerJointTorque(
          setpoint.position, upperAngularVelocity,
          setpoint.velocity, upperAngularAcceleration));
      }
    } else if(profiledPIDControllerNumber == 3) {
      if(superstructure.isClimbSafe(getLowerAngle(), getUpperAngle())) {
        upperJoint.set(Constants.kF_CLIMB_UPPER_BAR * Constants.DUAL_JOINTED_ARM_DYNAMIC_MODEL.getUpperJointTorque(
          lowerAngularVelocity, setpoint.position,
          lowerAngularAcceleration, setpoint.velocity));
      }
    }

    previousLowerVelocity = lowerAngularVelocity;
    previousUpperVelocity = upperAngularVelocity;
  }

  @Override
  protected double getMeasurement(int profiledPIDControllerNumber) {
    if(profiledPIDControllerNumber == 1) {
      return (lowerWinch.getEncoder().getPosition() - upperWinch.getEncoder().getPosition()) 
              / Constants.WINCH_CLIMB_ENCODER_PULSES_PER_INCH;
    } else if(profiledPIDControllerNumber == 2) {
      //TODO: Divide by encoder ticks per inch
      return lowerJoint.getEncoder().getPosition();
      
    } else if(profiledPIDControllerNumber == 3) {
      //TODO: Divide by encoder ticks per inch
      return upperJoint.getEncoder().getPosition();
    }

    return 0;
  }

  @Override
  public void periodic() {
    super.periodic();
    Constants.DUAL_JOINTED_ARM_DYNAMIC_MODEL.updateBarAngles(getLowerAngle(), getUpperAngle());
  }

  public void setWinchPower(double power) {
    winchPower = power;
  }

  public double getLowerAngle() {
    //0.733038 = 40 deg in rad
    return Math.toRadians(40d)+((lowerJoint.getEncoder().getPosition()/Constants.NEO_ENCODER_PULSES_PER_REVOLUTION)*(2*Math.PI));
  }

  public double getLowerVelocity() {
    return (lowerJoint.getEncoder().getVelocity()/Constants.NEO_ENCODER_PULSES_PER_REVOLUTION)*(2*Math.PI);
  }

  public double getUpperVelocity() {
    return (upperJoint.getEncoder().getVelocity()/Constants.NEO_ENCODER_PULSES_PER_REVOLUTION)*(2*Math.PI);
  }

  public double getUpperAngle() {
    return -getLowerAngle() + ((upperJoint.getEncoder().getPosition()/Constants.NEO_ENCODER_PULSES_PER_REVOLUTION)*(2*Math.PI));
  }
}
