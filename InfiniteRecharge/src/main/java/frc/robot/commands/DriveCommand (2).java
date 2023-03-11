/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase {
  final private DriveSubsystem m_driveSubsystem;
  final private XboxController m_xboxController;

  /**
   * Creates a new DriveCommand.
   */
  public DriveCommand(DriveSubsystem subsystem, XboxController controller) {
    m_driveSubsystem = subsystem;
    m_xboxController = controller;
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
    double rightPower = clamp(-m_xboxController.getY(Hand.kLeft) - m_xboxController.getX(Hand.kRight), -1, 1);
    double leftPower = clamp(-m_xboxController.getY(Hand.kLeft) +m_xboxController.getX(Hand.kRight), -1, 1);
    m_driveSubsystem.setMotors(rightPower, leftPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  private double clamp(double val, double min, double max) {
    return Math.max(Math.min(max, val), min);
  }
}
