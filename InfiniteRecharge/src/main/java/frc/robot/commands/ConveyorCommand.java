/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeOuttakeSubsystem;

public class ConveyorCommand extends CommandBase {
  IntakeOuttakeSubsystem m_intakeOuttakeSubsystem;
  double m_leftPower, m_rightPower;
  /**
   * Creates a new ConveyorCommand.
   */
  public ConveyorCommand(IntakeOuttakeSubsystem subsystem, double leftPower, double rightPower) {
    m_intakeOuttakeSubsystem = subsystem;
    m_leftPower = leftPower;
    m_rightPower = rightPower;
    addRequirements(m_intakeOuttakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intakeOuttakeSubsystem.runConveyor(m_leftPower, m_rightPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intakeOuttakeSubsystem.stop(false, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
