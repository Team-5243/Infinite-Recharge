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

    // Drive Constants
    public static final int frontRight = 10, frontLeft = 11, backRight = 1, backLeft = 2;
    
    // Intake Constants
    public static final int roller = 7, rightBelt = 8, leftBelt = 9;

    //Outtake Constants
    public static final int rightFlywheel = 12, leftFlywheel = 4;

    // Climb Constants
    public static final int firstStageClimb = 5, secondStageClimb = 6, frontWinch = 3, backWinch = 15;

    // OI Constants
    public static final int controller1 = 0, controller2 = 1;

}
