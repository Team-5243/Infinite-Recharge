package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class DriveStraightCommand extends CommandBase {
  private DriveSubsystem m_driveSubsystem;
  private AHRS m_gyro;
  private double m_power;
  private double m_distance, m_initialHeading;
  private double m_initialLateralPosition;

  /**
   * Creates a new DriveStraightCommand.
   */
  public DriveStraightCommand(DriveSubsystem subsystem, double power, double distance) {
    m_driveSubsystem         = subsystem;
    m_power                  = power;
    m_distance               = distance;
    //getPose().getTranslation().getX();
    addRequirements(m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_initialLateralPosition = m_driveSubsystem.getLeftDisplacement();
    m_gyro                   = m_driveSubsystem.getGyro();
    m_initialHeading         = m_gyro.getAngle(); //in deg
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double heading      = m_gyro.getAngle();
    double headingError = m_initialHeading - heading;
    m_driveSubsystem.setMotors(m_power, m_power); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("Current Travel: "+(m_driveSubsystem.getLeftDisplacement() - m_initialLateralPosition));  
    return (m_driveSubsystem.getLeftDisplacement()/*getPose().getTranslation().getX()*/ - m_initialLateralPosition) >= m_distance;
  }
}