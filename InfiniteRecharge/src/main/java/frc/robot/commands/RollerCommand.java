/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.OuttakeSubsystem;
import frc.robot.subsystems.PneumaticIntakeSubsystem;


public class RollerCommand extends CommandBase {
  private final PneumaticIntakeSubsystem m_pneumaticIntakeSubsystem;
  private final OuttakeSubsystem m_outtakeSubsystem;
  

  public RollerCommand(PneumaticIntakeSubsystem intakeSubsystem, OuttakeSubsystem outtakeSubsystem) {
    m_pneumaticIntakeSubsystem = intakeSubsystem;
    m_outtakeSubsystem = outtakeSubsystem;
    addRequirements(m_pneumaticIntakeSubsystem, m_outtakeSubsystem);
  }
  // Called when the command is initially scheduled.

  
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_pneumaticIntakeSubsystem.runIntake(-0.4);
    m_outtakeSubsystem.conveyorIntake();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_pneumaticIntakeSubsystem.stopIntake(); 
    m_outtakeSubsystem.stopConveyor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
