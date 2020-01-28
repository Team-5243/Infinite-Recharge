/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeOutakeSubsystem;

public class RollerCommand extends CommandBase {
  public static IntakeOutakeSubsystem m_IntakeOutakeSubsystem;

  public RollerCommand(IntakeOutakeSubsystem intakeOutakeSubsystem) {
    m_IntakeOutakeSubsystem = intakeOutakeSubsystem;
    addRequirements(m_IntakeOutakeSubsystem);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_IntakeOutakeSubsystem.intake(1, .3);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_IntakeOutakeSubsystem.stop(true, false); 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
