/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

@Deprecated
public class TurnCommand extends CommandBase {
  private DriveSubsystem m_driveSubsystem;
  private double m_power; 
  private double m_degrees;
  private AHRS m_gyro;

  /**
   * Creates a new TurnCommand.
   */
    public TurnCommand(DriveSubsystem driveSubsystem, double power, double degrees) {
    m_driveSubsystem = driveSubsystem;
    m_power = power;
    m_degrees = degrees;
    m_gyro = m_driveSubsystem.getGyro();
    addRequirements(m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double heading = m_gyro.getYaw();
    if (m_degrees < 0) {
      while (heading > m_degrees) {
        m_driveSubsystem.setMotors(m_power, -m_power);
        heading = m_gyro.getYaw();
      }
      m_driveSubsystem.stopMotors();
    }

    else if (m_degrees > 0) {
      while (heading > m_degrees) {
        m_driveSubsystem.setMotors(-m_power, m_power);
        heading = m_gyro.getYaw();
      }
      m_driveSubsystem.stopMotors();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
