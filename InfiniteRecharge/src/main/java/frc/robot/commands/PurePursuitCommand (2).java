/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.CurvePoint;
import frc.lib.PurePursuitCalculator;
import frc.robot.subsystems.DriveSubsystem;

public class PurePursuitCommand extends CommandBase {
  DriveSubsystem m_driveSubsystem;
  CurvePoint previousPoint, targetPoint;
  /**
   * Creates a new PurePursuitCommand.
   */
  public PurePursuitCommand(DriveSubsystem subsystem, CurvePoint previous, CurvePoint target) {
    m_driveSubsystem = subsystem;
    previousPoint = previous;
    targetPoint = target;
    addRequirements(m_driveSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Translation2d motion = PurePursuitCalculator.getVector(targetPoint, previousPoint);
    m_driveSubsystem.steerDrive(motion.getX(), motion.getY());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return targetPoint.getDistanceFromLookAhead(new CurvePoint(m_driveSubsystem.getPose().getTranslation())) < 1.5;
  }
}
