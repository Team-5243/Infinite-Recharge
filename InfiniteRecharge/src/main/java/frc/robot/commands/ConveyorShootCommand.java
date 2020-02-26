/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.OuttakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ConveyorShootCommand extends ParallelCommandGroup {
  /**
   * Creates a new ConveyorShootCommand.
   */
  public ConveyorShootCommand(IntakeSubsystem intakeSubsystem, OuttakeSubsystem outtakeSubsystem) {
    // Add your commands in the super() call, e.g.
    super(new RollerCommand(intakeSubsystem), new FlywheelCommand(outtakeSubsystem));
  }
}
