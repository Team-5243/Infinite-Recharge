/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.lib.DualJointedArmDynamicModel;
import frc.lib.IMotionProfile;
import frc.lib.ResidualVibrationReductionMotionProfiler;
import frc.states.ClimbArmsStateMachine;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //---------------------------------------------------------------
    // Physics Constants
    //---------------------------------------------------------------
    public static final double GRAVITATIONAL_ACCELERATION = 9.81d; //m/s^2

    //---------------------------------------------------------------
    // Motor Constants
    //---------------------------------------------------------------
    public static final int NEO_ENCODER_PULSES_PER_REVOLUTION = 42;

    //---------------------------------------------------------------
    // Drive Constants
    //---------------------------------------------------------------
    public static final double DRIVETRAIN_GEAR_RATIO = 10.75d;
    public static final double DRIVE_WHEEL_CIRCUMFERENCE = 6d * Math.PI; //in
    public static final double DRIVE_WHEEL_SEPARATION_DISTANCE = 22.375d; //in

    //---------------------------------------------------------------
    // Ports
    //---------------------------------------------------------------
    public static final int CONTROLLER = 0;

    //---------------------------------------------------------------
    // Drive Ports
    //---------------------------------------------------------------
    public static final int FRONT_RIGHT = 14;
    public static final int FRONT_LEFT = 1;
    public static final int BACK_LEFT = 2;
    public static final int BACK_RIGHT = 13;

    //---------------------------------------------------------------
    // Intake Ports
    //---------------------------------------------------------------
    public static final int ROLLER = 9;
    public static final int RIGHT_CONVEYOR = 11;
    public static final int LEFT_CONVEYOR = 4;

    //---------------------------------------------------------------
    // Outtake Ports
    //---------------------------------------------------------------
    public static final int LEFT_FLY = 8;
    public static final int RIGHT_FLY = 9;

    //---------------------------------------------------------------
    // Climb Ports
    //---------------------------------------------------------------
    public static final int LOWER_CLIMB_JOINT = 12;
    public static final int UPPER_CLIMB_JOINT = 7;
    public static final int UPPER_WINCH_CLIMB = 15;
    public static final int LOWER_WINCH_CLIMB = 3;

    //---------------------------------------------------------------
    // Climb Constants
    //---------------------------------------------------------------
    public static final double LOW_CLIMB_BAR_LENGTH = 1.5; //Filler (meters)
    public static final double UPPER_CLIMB_BAR_LENGTH = 2d; //Filler (meters)

    public static final double WINCH_RADIUS = 1d; //in
    public static final double WINCH_CLIMB_GEAR_RATIO = 60d; //geared for torque
    public static final double WINCH_CLIMB_ENCODER_PULSES_PER_REVOLUTION = NEO_ENCODER_PULSES_PER_REVOLUTION * WINCH_CLIMB_GEAR_RATIO; //enc / rev
    public static final double WINCH_CLIMB_ENCODER_PULSES_PER_INCH = WINCH_CLIMB_ENCODER_PULSES_PER_REVOLUTION / (2 * Math.PI * WINCH_RADIUS); //enc / in

    public static final double LOWER_BAR_MAX_ANGULAR_SPEED = 0d; //deg / s
    public static final double UPPER_BAR_MAX_ANGULAR_SPEED = 0d; //deg / s

    public static final double LOWER_BAR_MAX_ANGULAR_ACCELERATION = 0d; //deg / s^2
    public static final double UPPER_BAR_MAX_ANGULAR_ACCELERATION = 0d; //deg / s^2

    public static final double kP_CLIMB_WINCH_SYNCHRONIZE = 0.001d; //motor power / in
    public static final double kI_CLIMB_WINCH_SYNCHRONIZE = 0d; //motor power / (in * s)
    public static final double kD_CLIMB_WINCH_SYNCHRONIZE = 0d; //motor power * s / in

    public static final double kS_CLIMB_LOWER_BAR = 0d; //motor power
    public static final double kP_CLIMB_LOWER_BAR = 0d; //motor power / deg
    public static final double kI_CLIMB_LOWER_BAR = 0d; //motor power / (deg * s)
    public static final double kD_CLIMB_LOWER_BAR = 0d; //motor power * s / 
    public static final double kF_CLIMB_LOWER_BAR = 0d; //motor power / N

    public static final double kS_CLIMB_UPPER_BAR = 0d; //motor power
    public static final double kP_CLIMB_UPPER_BAR = 0d; //motor power deg
    public static final double kI_CLIMB_UPPER_BAR = 0d; //motor power / (deg * s)
    public static final double kD_CLIMB_UPPER_BAR = 0d; //motor power * s / deg
    public static final double kF_CLIMB_UPPER_BAR = 0d; //motor power / N

    //Lower bar motion profiles for climb setpoints
    public static final IMotionProfile CLIMB_LOWER_INIT_TO_INTAKE_PROFILE = 
        new ResidualVibrationReductionMotionProfiler(
            ClimbArmsStateMachine.State.INIT.getLowerAngle(), 
            ClimbArmsStateMachine.State.INTAKE.getLowerAngle(), 
            LOWER_BAR_MAX_ANGULAR_SPEED, 
            LOWER_BAR_MAX_ANGULAR_ACCELERATION);
    public static final IMotionProfile CLIMB_LOWER_INTAKE_TO_IDLE_PROFILE = 
        new ResidualVibrationReductionMotionProfiler(
            ClimbArmsStateMachine.State.INTAKE.getLowerAngle(),
            ClimbArmsStateMachine.State.IDLE.getLowerAngle(),
            LOWER_BAR_MAX_ANGULAR_SPEED, 
            LOWER_BAR_MAX_ANGULAR_ACCELERATION);
    public static final IMotionProfile CLIMB_LOWER_IDLE_TO_INTAKE_PROFILE = 
        new ResidualVibrationReductionMotionProfiler(
            ClimbArmsStateMachine.State.IDLE.getLowerAngle(),
            ClimbArmsStateMachine.State.INTAKE.getLowerAngle(),
            LOWER_BAR_MAX_ANGULAR_SPEED, 
            LOWER_BAR_MAX_ANGULAR_ACCELERATION);
    public static final IMotionProfile CLIMB_LOWER_IDLE_TO_CLIMB =
        new ResidualVibrationReductionMotionProfiler(
            ClimbArmsStateMachine.State.IDLE.getLowerAngle(),
            ClimbArmsStateMachine.State.CLIMB.getLowerAngle(), 
            LOWER_BAR_MAX_ANGULAR_SPEED, 
            LOWER_BAR_MAX_ANGULAR_ACCELERATION);
    public static final IMotionProfile CLIMB_LOWER_CLIMB_TO_IDLE =
        new ResidualVibrationReductionMotionProfiler(
            ClimbArmsStateMachine.State.CLIMB.getLowerAngle(),
            ClimbArmsStateMachine.State.IDLE.getLowerAngle(), 
            LOWER_BAR_MAX_ANGULAR_SPEED, 
            LOWER_BAR_MAX_ANGULAR_ACCELERATION);

    //Upper bar motion profiles for climb setpoints
    public static final IMotionProfile CLIMB_UPPER_INIT_TO_INTAKE_PROFILE = 
        new ResidualVibrationReductionMotionProfiler(
            ClimbArmsStateMachine.State.INIT.getUpperAngle(), 
            ClimbArmsStateMachine.State.INTAKE.getUpperAngle(), 
            UPPER_BAR_MAX_ANGULAR_SPEED, 
            UPPER_BAR_MAX_ANGULAR_ACCELERATION);
    public static final IMotionProfile CLIMB_UPPER_INTAKE_TO_IDLE_PROFILE = 
        new ResidualVibrationReductionMotionProfiler(
            ClimbArmsStateMachine.State.INTAKE.getUpperAngle(),
            ClimbArmsStateMachine.State.IDLE.getUpperAngle(), 
            UPPER_BAR_MAX_ANGULAR_SPEED, 
            UPPER_BAR_MAX_ANGULAR_ACCELERATION);
    public static final IMotionProfile CLIMB_UPPER_IDLE_TO_INTAKE_PROFILE = 
        new ResidualVibrationReductionMotionProfiler(
            ClimbArmsStateMachine.State.IDLE.getUpperAngle(),
            ClimbArmsStateMachine.State.INTAKE.getUpperAngle(),
            UPPER_BAR_MAX_ANGULAR_SPEED, 
            UPPER_BAR_MAX_ANGULAR_ACCELERATION);
    public static final IMotionProfile CLIMB_UPPER_IDLE_TO_CLIMB =
        new ResidualVibrationReductionMotionProfiler(
            ClimbArmsStateMachine.State.IDLE.getUpperAngle(),
            ClimbArmsStateMachine.State.CLIMB.getUpperAngle(),  
            UPPER_BAR_MAX_ANGULAR_SPEED, 
            UPPER_BAR_MAX_ANGULAR_ACCELERATION);
    public static final IMotionProfile CLIMB_UPPER_CLIMB_TO_IDLE =
        new ResidualVibrationReductionMotionProfiler(
            ClimbArmsStateMachine.State.CLIMB.getUpperAngle(),
            ClimbArmsStateMachine.State.IDLE.getUpperAngle(), 
            UPPER_BAR_MAX_ANGULAR_SPEED, 
            UPPER_BAR_MAX_ANGULAR_ACCELERATION);

    //---------------------------------------------------------------
    // Drive Constants
    //---------------------------------------------------------------
    public static final double kP_DRIVE_STRAIGHT = 1 / 30d; //motor power / degree

    public static final double kS_TURN = 0d;
    public static final double kP_TURN = 1 / 30d; //motor power / degree
    public static final double kI_TURN = 0d;
    public static final double kD_TURN = 0d;

    //---------------------------------------------------------------
    // Wheel of Fortune Ports
    //---------------------------------------------------------------
    public static final int ARM_RETRACT = 0;
    public static final int WHEEL_SPINNER = 0;

    //---------------------------------------------------------------
    // Outtake Constants
    //---------------------------------------------------------------
    public static final double MAX_FLYWHEEL_SPEED = 6000d; //rpm

    //---------------------------------------------------------------
    // Dynamic Models
    //---------------------------------------------------------------
    public static final DualJointedArmDynamicModel DUAL_JOINTED_ARM_DYNAMIC_MODEL = 
        new DualJointedArmDynamicModel(0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d);
}
