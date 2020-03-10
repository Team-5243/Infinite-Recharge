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

public class ManualWinchControlCommand extends CommandBase {
  private ClimbSubsystem m_climbSubsystem;
  private XboxController m_controller;
  /**
   * Creates a new ManualWinchControlCommand.
   */
  public ManualWinchControlCommand(ClimbSubsystem subsystem, XboxController controller){
    // Use addRequirements() here to declare subsystem dependencies.
    m_controller     = controller;
    m_climbSubsystem = subsystem;
    addRequirements(m_climbSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_climbSubsystem.manuallyControlFrontWinch(-0.5*(m_controller.getY(Hand.kRight)));
    m_climbSubsystem.manuallyControlBackWinch(-0.5*(m_controller.getY(Hand.kLeft)));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climbSubsystem.manuallyControlFrontWinch(0);
    m_climbSubsystem.manuallyControlBackWinch(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
