/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.ConvertedXboxController;
import frc.robot.subsystems.IntakeSubsystem;

public class ConveyorCommand extends CommandBase {
  private IntakeSubsystem m_intakeSubsystem;
  private ConvertedXboxController m_controller;
  /**
   * Creates a new ConveyorCommand.
   */
  public ConveyorCommand(IntakeSubsystem subsystem, ConvertedXboxController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_controller = controller;
    m_intakeSubsystem = subsystem;
    addRequirements(m_intakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_controller.isDpadDown()){
      m_intakeSubsystem.runConveyor(-.6, -.6);
    } else if(m_controller.isDpadLeft()) {
      m_intakeSubsystem.runConveyor(.6, -.6);
    } else if(m_controller.isDpadRight()) {
      m_intakeSubsystem.runConveyor(-.6, .6);
    } else {
      m_intakeSubsystem.runConveyor(.6, .6);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intakeSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
