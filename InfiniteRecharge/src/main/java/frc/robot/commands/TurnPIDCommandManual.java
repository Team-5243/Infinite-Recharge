/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class TurnPIDCommandManual extends CommandBase {
  private DriveSubsystem m_driveSubsystem;
  private XboxController m_xController;
  private AHRS m_gyro;
  private double m_desiredHeading, m_initialHeading;
  private boolean m_relative;
  private double m_lastError;
  private double m_runningSum;

  /**
   * Creates a new TurnPIDCommandManual.
   */
  public TurnPIDCommandManual(DriveSubsystem driveSubsystem, XboxController controller, double desiredHeading, boolean relative) {
    m_driveSubsystem = driveSubsystem;
    m_xController = controller;
    m_gyro = driveSubsystem.getGyro();
    m_desiredHeading = desiredHeading;
    m_relative = relative;
    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_initialHeading = m_gyro.getAngle();
    m_lastError      = 0d;
    m_runningSum     = 0d;
    if(m_relative) {
      m_desiredHeading += m_initialHeading;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double dt = 1 / 200d;
    double heading = m_gyro.getAngle();
    double error = m_desiredHeading - heading;
    m_runningSum += error * dt;
    double output = 
      Constants.kP_TURN * error + 
      Constants.kD_TURN * (error - m_lastError) / dt + 
      Constants.kI_TURN * m_runningSum;
    output += Constants.kS_TURN * Math.signum(output);
    m_driveSubsystem.steerDrive(0d, output);
    m_lastError = error;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(m_relative) {
      m_desiredHeading -= m_initialHeading;
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_xController.getYButtonPressed();
    //return m_relative ? Math.abs((m_desiredHeading+m_initialHeading) - m_driveSubsystem.getGyro().getAngle()) <= 1 :
    //  (Math.abs(m_desiredHeading - m_driveSubsystem.getGyro().getAngle()) <= 1);
  }
}
