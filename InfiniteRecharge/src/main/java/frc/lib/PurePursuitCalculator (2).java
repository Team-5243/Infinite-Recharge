/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib;

import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Add your docs here.
 */
public class PurePursuitCalculator {

    public static Translation2d getVector(final CurvePoint targetPoint, final CurvePoint previousPoint) {
        final DriveSubsystem driveSubsystem = Robot.getRobotContainer().getDriveSubsystem();

        final Translation2d robotVector = driveSubsystem.getPose().getTranslation();
        final Translation2d pointVector = new Translation2d(targetPoint.getXCoordinate() - previousPoint.getXCoordinate(),
            targetPoint.getYCoordinate() - previousPoint.getYCoordinate());

        final double dotProductTargetRobotScalar = (pointVector.getX()*robotVector.getX()) + (robotVector.getY() * pointVector.getY());

        final Translation2d targetVector = (pointVector.div(pointVector.getNorm())).times(((dotProductTargetRobotScalar / pointVector.getNorm())) + targetPoint.getLookAheadDistance());        
        final Translation2d powerVector = targetVector.minus(robotVector);
        
        final double heading = Math.toRadians(driveSubsystem.getGyro().getYaw());

        Translation2d robotPower = powerVector.rotateBy(new Rotation2d(heading));

        robotPower.div(robotPower.getX()+robotPower.getY());
        
        return robotPower;
    }
}
