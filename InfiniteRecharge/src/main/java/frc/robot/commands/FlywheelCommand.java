/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeOuttakeSubsystem;

public class FlywheelCommand extends CommandBase {
  private IntakeOuttakeSubsystem m_IntakeOuttakeSubsystem;
  /**
   * Creates a new FlywheelCommand.
   */
  public FlywheelCommand(IntakeOuttakeSubsystem intakeOuttakeSubsystem) {
    m_IntakeOuttakeSubsystem = intakeOuttakeSubsystem;
    addRequirements(m_IntakeOuttakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_IntakeOuttakeSubsystem.outtake(.7, .3);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_IntakeOuttakeSubsystem.stop(false, true); 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
