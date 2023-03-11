/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class DriveStraightWithHeadingCommand extends CommandBase {
  DriveSubsystem m_driveSubsystem;
  private AHRS m_gyro;
  private double m_power;
  private double m_distance, m_targetHeading;
  private double m_initialLateralPosition;

  public DriveStraightWithHeadingCommand(DriveSubsystem subsystem, double power, double distance, double heading) {
    m_driveSubsystem = subsystem;
    m_power = power;
    m_distance = distance;
    m_targetHeading = heading;
    
    addRequirements(m_driveSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_gyro = m_driveSubsystem.getGyro();
    m_initialLateralPosition = m_driveSubsystem.getLeftDisplacement();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double heading = m_gyro.getAngle() - (((int)((m_gyro.getAngle()/360)))*360); 
    double headingError = m_targetHeading - heading;
    m_driveSubsystem.steerDrive(m_power, Constants.kP_DRIVE_STRAIGHT * headingError);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (m_driveSubsystem.getLeftDisplacement()/*getPose().getTranslation().getX()*/ - m_initialLateralPosition) >= m_distance;
  }
}
