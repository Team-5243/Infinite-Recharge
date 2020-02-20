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
import frc.robot.subsystems.ClimbSubsystem;

public class ManualJointCommand extends CommandBase {
  ClimbSubsystem m_climbSubsystem;
  XboxController m_xboxController;
  /**
   * Creates a new ManualJointCommand.
   */
  public ManualJointCommand(ClimbSubsystem subsystem, XboxController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_climbSubsystem = subsystem;
    m_xboxController = controller;
    addRequirements(m_climbSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_climbSubsystem.runLowerJoint(m_xboxController.getAButton() ? 
      m_xboxController.getTriggerAxis(Hand.kRight)* -0.1 : 
      m_xboxController.getTriggerAxis(Hand.kRight)* 0.25);
    m_climbSubsystem.runUpperJoint(m_xboxController.getAButton() ?
      m_xboxController.getTriggerAxis(Hand.kLeft)*-0.1 :
      m_xboxController.getTriggerAxis(Hand.kLeft)*0.35);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
