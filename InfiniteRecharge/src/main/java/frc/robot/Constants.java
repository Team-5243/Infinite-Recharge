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
    public static final int DRIVER_CONTROLLER = 0;
    public static final int MECHANISM_CONTROLLER = 1;

    //---------------------------------------------------------------
    // Drive Ports
    //---------------------------------------------------------------
    public static final int FRONT_RIGHT = 10;
    public static final int FRONT_LEFT = 11;
    public static final int BACK_LEFT = 2;
    public static final int BACK_RIGHT = 1;

    //---------------------------------------------------------------
    // Intake Ports
    //---------------------------------------------------------------
    public static final int ROLLER = 7;
    public static final int RIGHT_CONVEYOR = 4;
    public static final int LEFT_CONVEYOR = 12;

    //---------------------------------------------------------------
    // Outtake Ports
    //---------------------------------------------------------------
    public static final int LEFT_FLY = 8; 
    public static final int RIGHT_FLY = 9; 

    //---------------------------------------------------------------
    // Climb Ports
    //---------------------------------------------------------------
    public static final int LOWER_CLIMB_JOINT = 5;
    public static final int UPPER_CLIMB_JOINT = 6;
    public static final int FRONT_WINCH_CLIMB = 3;
    public static final int BACK_WINCH_CLIMB = 15;

    public static final int CLIMB = UPPER_CLIMB_JOINT;

    //---------------------------------------------------------------
    // Climb Constants
    //---------------------------------------------------------------
    public static final double LOW_CLIMB_BAR_LENGTH = 30d * 0.0254d; //meters
    public static final double UPPER_CLIMB_BAR_LENGTH = 30d * 0.0254d; //meters

    public static final double LOWER_JOINT_GEAR_RATIO = 64d;
    public static final double UPPER_JOINT_GEAR_RATIO = 100d;

    public static final double WINCH_RADIUS = 1d; //in
    public static final double WINCH_CLIMB_GEAR_RATIO = 60d; //geared for torque
    public static final double WINCH_CLIMB_ENCODER_PULSES_PER_REVOLUTION = NEO_ENCODER_PULSES_PER_REVOLUTION * WINCH_CLIMB_GEAR_RATIO; //enc / rev
    public static final double WINCH_CLIMB_ENCODER_PULSES_PER_INCH = WINCH_CLIMB_ENCODER_PULSES_PER_REVOLUTION / (2 * Math.PI * WINCH_RADIUS); //enc / in

    public static final double LOWER_BAR_MAX_ANGULAR_SPEED = 535d * Math.PI / 180d; //rad / s
    public static final double UPPER_BAR_MAX_ANGULAR_SPEED = 345d * Math.PI / 180d; //rad / s

    public static final double LOWER_BAR_MAX_ANGULAR_ACCELERATION = 1000d * Math.PI / 180d; //rad / s^2
    public static final double UPPER_BAR_MAX_ANGULAR_ACCELERATION = 600d * Math.PI / 180d; //rad / s^2

    public static final double kP_CLIMB_WINCH_SYNCHRONIZE = 0d;//0.001d; //motor power / in
    public static final double kI_CLIMB_WINCH_SYNCHRONIZE = 0d; //motor power / (in * s)
    public static final double kD_CLIMB_WINCH_SYNCHRONIZE = 0d; //motor power * s / in

    public static final double kS_CLIMB_LOWER_BAR = 0.018d; //motor power
    public static final double kP_CLIMB_LOWER_BAR = 1E-9; //motor power / rad
    public static final double kI_CLIMB_LOWER_BAR = 0d; //motor power / (rad * s)
    public static final double kD_CLIMB_LOWER_BAR = 0d; //motor power * s / rad
    public static final double kF_CLIMB_LOWER_BAR = 1d;//0.5d; //motor power / N

    public static final double kS_CLIMB_UPPER_BAR = 0d; //motor power
    public static final double kP_CLIMB_UPPER_BAR = 0d; //motor power rad
    public static final double kI_CLIMB_UPPER_BAR = 0d; //motor power / (rad * s)
    public static final double kD_CLIMB_UPPER_BAR = 0d; //motor power * s / rad
    public static final double kF_CLIMB_UPPER_BAR = 0d; //motor power / N

    //Lower bar motion profiles for climb setpoints
  
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
    public static final double LBS_TO_KG = 0.453592d;
    public static final double INCH_TO_METER = 0.0254d;

    public static final DualJointedArmDynamicModel DUAL_JOINTED_ARM_DYNAMIC_MODEL = 
        new DualJointedArmDynamicModel(
            (1d - (1d - 0.125d) * (1d - 0.125d)) * (LBS_TO_KG / 10d) * LOW_CLIMB_BAR_LENGTH / INCH_TO_METER, 
            (1d - (1d - 0.125d) * (1d - 0.125d)) * (LBS_TO_KG / 10d) * UPPER_CLIMB_BAR_LENGTH / INCH_TO_METER, 
            (1d - (1d - 0.125d) * (1d - 0.125d)) * (LBS_TO_KG / 10d) * (LOW_CLIMB_BAR_LENGTH / INCH_TO_METER) * LOW_CLIMB_BAR_LENGTH * LOW_CLIMB_BAR_LENGTH, 
            (1d - (1d - 0.125d) * (1d - 0.125d)) * (LBS_TO_KG / 10d) * (UPPER_CLIMB_BAR_LENGTH / INCH_TO_METER) * UPPER_CLIMB_BAR_LENGTH * UPPER_CLIMB_BAR_LENGTH, 
            LOW_CLIMB_BAR_LENGTH, 
            UPPER_CLIMB_BAR_LENGTH,
            LOW_CLIMB_BAR_LENGTH / 2d, 
            UPPER_CLIMB_BAR_LENGTH / 2d
    );
    
}
