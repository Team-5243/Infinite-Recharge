/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot; 

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int NEO_ENCODER_PULSES_PER_REVOLUTION = 42;

    public static final int CONTROLLER = 0;

    public static final int FRONT_RIGHT = 1;
    public static final int FRONT_LEFT = 2;
    public static final int BACK_LEFT = 3;
    public static final int BACK_RIGHT = 4;

    public static final int ROLLER = 5;
    public static final int CONVEYER = 6;

    public static final int LEFT_FLY = 7;
    public static final int RIGHT_FLY = 8;

    public static final int VIRTUAL_FOUR_BAR = 9;
    public static final int LEFT_WINCH_CLIMB = 10;
    public static final int RIGHT_WINCH_CLIMB = 11;

    public static final double WINCH_RADIUS = 1d; //in
    public static final double WINCH_CLIMB_GEAR_RATIO = 60d; //geared for torque
    public static final double WINCH_CLIMB_ENCODER_PULSES_PER_REVOLUTION = NEO_ENCODER_PULSES_PER_REVOLUTION * WINCH_CLIMB_GEAR_RATIO; //enc / rev
    public static final double WINCH_CLIMB_ENCODER_PULSES_PER_INCH = WINCH_CLIMB_ENCODER_PULSES_PER_REVOLUTION / (2 * Math.PI * WINCH_RADIUS); //enc / in

    public static final double kP_CLIMB_SYNCHRONIZE = 0.001d; //in / motor power
    public static final double kI_CLIMB_SYNCHRONIZE = 0d; //in / (motor power s)
    public static final double kD_CLIMB_SYNCHRONIZE = 0d; //in s / motor power

    public static final double MAX_FLYWHEEL_SPEED = 6000d; //rpm
}