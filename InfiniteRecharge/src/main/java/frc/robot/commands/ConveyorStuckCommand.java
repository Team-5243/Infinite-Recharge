/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeOuttakeSubsystem;

public class ConveyorStuckCommand extends CommandBase {
  private IntakeOuttakeSubsystem m_intakeOuttakeSubsystem;
  /**
   * Creates a new ConveyorStuckCommand.
   */
  public ConveyorStuckCommand(IntakeOuttakeSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_intakeOuttakeSubsystem = subsystem;
    addRequirements(m_intakeOuttakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intakeOuttakeSubsystem.unstuck();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intakeOuttakeSubsystem.stopConveyor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
