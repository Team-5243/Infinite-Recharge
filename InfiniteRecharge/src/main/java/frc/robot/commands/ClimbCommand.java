/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.ConvertedXboxController;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbCommand extends CommandBase {
  private ClimbSubsystem m_climbSubsystem;
  private ConvertedXboxController m_controller;
  /**
   * Creates a new ClimbCommand.
   */
  public ClimbCommand(ClimbSubsystem subsystem, ConvertedXboxController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_climbSubsystem = subsystem;
    m_controller = controller;
    addRequirements(m_climbSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_climbSubsystem.setClimb(-(m_controller.getXboxController().getTriggerAxis(Hand.kRight) - m_controller.getXboxController().getTriggerAxis(Hand.kLeft)) / 4d);
    m_climbSubsystem.manuallyControlBackWinch(-0.5*(m_controller.getXboxController().getY(Hand.kRight)));
    m_climbSubsystem.manuallyControlFrontWinch(-0.5*(m_controller.getXboxController().getY(Hand.kLeft)));

    //18 in horizontal bar -> 18 * 7 / 12 = 10.5 oz
    //18.75 in vertical stage -> 18.75 * 7 / 12 = 10.9 oz
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climbSubsystem.stopClimb();
    m_climbSubsystem.manuallyControlBackWinch(0);
    m_climbSubsystem.manuallyControlFrontWinch(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
