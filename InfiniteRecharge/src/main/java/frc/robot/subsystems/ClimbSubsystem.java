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
import frc.lib.IMotionProfile;
import frc.lib.Range;
import frc.lib.TimeProfiler;
import frc.robot.Constants;
import frc.states.ClimbArmsStateMachine;

public class ClimbSubsystem extends SubsystemBase {
  private final CANSparkMax upperWinch, lowerWinch;
  private final CANSparkMax upperJoint, lowerJoint;
  private final Superstructure superstructure;

  private double winchPower;
  private double lastLowerVelocity, lastUpperVelocity;

  private double lastWinchError;
  private double lastLowerJointError;
  private double lastUpperJointError;

  private double winchRunningSum;
  private double lowerJointRunningSum;
  private double upperJointRunningSum;

  private double upperJointAngleTracker;
  private double upperJointVelocity;
  private double upperJointAcceleration;

  private TimeProfiler timeProfiler;

  private IMotionProfile lowerJointProfile;
  private IMotionProfile upperJointProfile;

  /**
   * Creates a new ClimbSubsystemFinal.
   */
  public ClimbSubsystem(Superstructure superstructure) {
    upperWinch = new CANSparkMax(Constants.UPPER_WINCH_CLIMB, MotorType.kBrushless);
    lowerWinch = new CANSparkMax(Constants.LOWER_WINCH_CLIMB, MotorType.kBrushless);
    lowerJoint = new CANSparkMax(Constants.LOWER_CLIMB_JOINT, MotorType.kBrushless);
    upperJoint = new CANSparkMax(Constants.UPPER_CLIMB_JOINT, MotorType.kBrushless);
    
    lowerJoint.getEncoder().setPosition(0);
    upperJoint.getEncoder().setPosition(0);

    upperJointAngleTracker = getUpperAngle();
    upperJointVelocity = 0;
    upperJointAcceleration = 0;

    this.superstructure = superstructure;
    winchPower          = 0d;
    lastLowerVelocity   = 0d;
    lastUpperVelocity   = 0d;

    lastWinchError       = 0d;
    lastLowerJointError  = 0d;
    lastUpperJointError  = 0d;
    winchRunningSum      = 0d;
    lowerJointRunningSum = 0d;
    upperJointRunningSum = 0d;

    timeProfiler = new TimeProfiler(false);

    lowerJointProfile = null;
    upperJointProfile = null;
  }

  public void start() {
    timeProfiler.start();
  }

  //TODO: Check if the use of radians/degrees is correct throughout the code
  @Override
  public void periodic() {
    //TODO: Use the dynamic model for feedforward control.
    //Update the dynamic model for the dual-jointed-arm for the current angles of the joints.
    //Constants.DUAL_JOINTED_ARM_DYNAMIC_MODEL.updateBarAngles(getLowerAngle(), getUpperAngle());
    //Get the elapsed time since the last call to periodic.
    double dt = timeProfiler.getDeltaTime(true);

    //Define feedforward variables for the lower joint.
    double lowerJointDesiredAngle               = ClimbArmsStateMachine.getState().getLowerAngle();
    double lowerJointDesiredAngularVelocity     = 0d;
    double lowerJointDesiredAngularAcceleration = 0d;
    double lowerJointDesiredTorque              = 0d;

    //Define feedforward variables for the upper joint.
    double upperJointDesiredAngle               = ClimbArmsStateMachine.getState().getUpperAngle();
    double upperJointDesiredAngularVelocity     = 0d;
    double upperJointDesiredAngularAcceleration = 0d;
    double upperJointDesiredTorque              = 0d;

    Constants.DUAL_JOINTED_ARM_DYNAMIC_MODEL.updateBarAngles(lowerJointDesiredAngle, upperJointDesiredAngle);
    //Check if there are motion profiles for the joints to follow.
    if(lowerJointProfile != null && upperJointProfile != null) {
      if(!lowerJointProfile.isDone() && !upperJointProfile.isDone()) {
        //Set lower joint variables for obtaining the desired torque for the joints.
        lowerJointDesiredAngle               = lowerJointProfile.getPosition();
        lowerJointDesiredAngularVelocity     = lowerJointProfile.getVelocity();
        lowerJointDesiredAngularAcceleration = lowerJointProfile.getAcceleration();

        //Set upper joint variables for obtaining the desired torque for the joints.
        upperJointDesiredAngle               = upperJointProfile.getPosition();
        upperJointDesiredAngularVelocity     = upperJointProfile.getVelocity();
        upperJointDesiredAngularAcceleration = upperJointProfile.getAcceleration();

        //Obtain the desired torque for the lower joint using the dynamic model and motion profiles.
        lowerJointDesiredTorque = Constants.DUAL_JOINTED_ARM_DYNAMIC_MODEL.getLowerJointTorque(
          lowerJointDesiredAngularVelocity, upperJointDesiredAngularVelocity, 
          lowerJointDesiredAngularAcceleration, upperJointDesiredAngularAcceleration);
          //lowerJointDesiredTorque = Math.cos(lowerJointDesiredAngle);
        //Obtain the desired torque for the upper joint using the dynamic model and motion profiles.
        upperJointDesiredTorque = Constants.DUAL_JOINTED_ARM_DYNAMIC_MODEL.getUpperJointTorque(
          lowerJointDesiredAngularVelocity, upperJointDesiredAngularVelocity, 
          lowerJointDesiredAngularAcceleration, upperJointDesiredAngularAcceleration);
          //upperJointDesiredTorque = 0d;
      }
    }

    //Get the errors of the system relative to their setpoints.
    double winchError      = getUpperWinchPosition() - getLowerWinchPosition();
    double lowerJointError = lowerJointDesiredAngle - getLowerAngle();
    double upperJointError = upperJointDesiredAngle - getUpperAngle();

    // //Update integral control.
    winchRunningSum      += winchError * dt;
    lowerJointRunningSum += lowerJointError * dt;
    upperJointRunningSum += upperJointError * dt;

    //Prevent integral windup.
    winchRunningSum = Range.clip(winchRunningSum, 
      -1 / Constants.kI_CLIMB_WINCH_SYNCHRONIZE, 1 / Constants.kI_CLIMB_WINCH_SYNCHRONIZE);
    lowerJointRunningSum = Range.clip(lowerJointRunningSum,
      -1 / Constants.kI_CLIMB_LOWER_BAR, 1 / Constants.kI_CLIMB_LOWER_BAR);
    upperJointRunningSum = Range.clip(upperJointRunningSum, 
      -1 / Constants.kI_CLIMB_UPPER_BAR, 1 / Constants.kI_CLIMB_UPPER_BAR);

    //Calculate the motor power output using PIDF - feedback and feedforward controllers
    double winchOutput = 
      Constants.kP_CLIMB_WINCH_SYNCHRONIZE * winchError + 
      Constants.kI_CLIMB_WINCH_SYNCHRONIZE * winchRunningSum +
      Constants.kD_CLIMB_WINCH_SYNCHRONIZE * (winchError - lastWinchError) / dt;
    double lowerJointOutput = 
      Constants.kF_CLIMB_LOWER_BAR * (lowerJointDesiredTorque) +
      Constants.kP_CLIMB_LOWER_BAR * lowerJointError +
      Constants.kI_CLIMB_LOWER_BAR * lowerJointRunningSum +
      Constants.kD_CLIMB_LOWER_BAR * ((lowerJointError - lastLowerJointError) / dt - lowerJointDesiredAngularVelocity);
    double upperJointOutput = 
      Constants.kF_CLIMB_UPPER_BAR * upperJointDesiredTorque +
      Constants.kP_CLIMB_UPPER_BAR * upperJointError +
      Constants.kI_CLIMB_UPPER_BAR * upperJointRunningSum +
      Constants.kD_CLIMB_UPPER_BAR * ((upperJointError - lastUpperJointError) / dt - upperJointDesiredAngularVelocity);
 
    //Static friction compensation to minimize the need for integral control.
    lowerJointOutput += Constants.kS_CLIMB_LOWER_BAR * Math.signum(lowerJointOutput);
    //upperJointOutput += Constants.kS_CLIMB_UPPER_BAR * Math.signum(upperJointOutput);

    //Update the motor powers using the calculated motor power outputs above.
    //upperWinch.set(winchPower);
    //lowerWinch.set(winchPower + winchOutput);
    lowerJointOutput = Range.clip(lowerJointOutput, -0.3, 0.3);
    upperJointOutput = Range.clip(upperJointOutput, -0.2, 0.2);
    lowerJoint.set(lowerJointOutput);
    upperJoint.set(upperJointOutput);

    //Update the new, old values for the next call to periodic.
    lastWinchError      = winchError;
    lastLowerJointError = lowerJointError;
    lastUpperJointError = upperJointError;
  }

  public double getUpperWinchPosition() {
    return 0;//upperWinch.getEncoder().getPosition() / Constants.WINCH_CLIMB_ENCODER_PULSES_PER_INCH;
  }

  public double getLowerWinchPosition() {
    return 0;//lowerWinch.getEncoder().getPosition() / Constants.WINCH_CLIMB_ENCODER_PULSES_PER_INCH;
  }

  public void setWinchPower(double power) {
    winchPower = power;
  }

  //in radians
  public double getLowerAngle() {
    return Math.toRadians(40) + (lowerJoint.getEncoder().getPosition()*(2*Math.PI/Constants.LOWER_JOINT_GEAR_RATIO));//Math.toRadians(40d)+((lowerJoint.getEncoder().getPosition()/Constants.NEO_ENCODER_PULSES_PER_REVOLUTION)*(2*Math.PI));
  }

  public double getLowerVelocity() {
    return (lowerJoint.getEncoder().getVelocity()*(2*Math.PI/Constants.LOWER_JOINT_GEAR_RATIO));
  }

  public double getUpperVelocity() {
    return upperJoint.getEncoder().getVelocity()*(2*Math.PI/Constants.UPPER_JOINT_GEAR_RATIO);
  }

  public double getUpperAngle() {
    return -getLowerAngle() + (upperJoint.getEncoder().getPosition()*(2*Math.PI/Constants.UPPER_JOINT_GEAR_RATIO));
  }

  public void setLowerProfile(IMotionProfile motionProfile) {
    lowerJointProfile = motionProfile;
    lowerJointProfile.start();
  }

  public void setUpperProfile(IMotionProfile motionProfile) {
    upperJointProfile = motionProfile;
    upperJointProfile.start();
  }

  public IMotionProfile getLowerProfile() {
    return lowerJointProfile;
  }

  public IMotionProfile getUpperProfile() {
    return upperJointProfile;
  }

  public CANSparkMax getLowerJoint() {
    return lowerJoint;
  }

  public CANSparkMax getUpperJoint() {
    return upperJoint;
  }

  public CANSparkMax getlowerWinch() {
    return lowerWinch;
  }

  public CANSparkMax getUpperWinch() {
    return upperWinch;
  }

  public void manuallyControlUpperWinch(double power) {
    upperWinch.set(power);
  }

  public void manuallyControlLowerWinch(double power) {
    lowerWinch.set(power);
  }

  public void manuallyControlLowerJoint(double power) {
    lowerJoint.set(power);
  }

  public void manuallyControlUpperJoint(double power) {
    upperJoint.set(power);
  }
}
