/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TurnPIDCommand extends PIDCommand {
  /**
   * Creates a new TurnPIDCommand.
   */
  DriveSubsystem m_driveSubsystem;
  double m_desiredHeading, m_initialHeading;
  boolean m_relative;


  public TurnPIDCommand(DriveSubsystem driveSubsystem, double desiredHeading, double initialHeading, boolean relative) {
    super(
        // The controller that the command will use
        new PIDController(Constants.kP_TURN, 0, 0),
        // This should return the measurement
        driveSubsystem.getGyro()::getYaw,
        // This should return the setpoint (can also be a constant)
        () -> relative ? desiredHeading + initialHeading : desiredHeading,
        // This uses the output
        output -> driveSubsystem.steerDrive(0d, output));
    
    m_driveSubsystem = driveSubsystem;
    m_desiredHeading = desiredHeading;
    m_initialHeading = initialHeading;
    m_relative = relative;
    addRequirements(driveSubsystem);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_relative ? Math.abs((m_desiredHeading+m_initialHeading) - m_driveSubsystem.getGyro().getYaw()) <= 1 :
      (Math.abs(m_desiredHeading - m_driveSubsystem.getGyro().getYaw()) <= 1);
  }
}
