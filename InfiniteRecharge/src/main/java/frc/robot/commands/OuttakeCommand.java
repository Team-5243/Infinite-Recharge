/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.IntakeOuttakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class OuttakeCommand extends PIDCommand {
  /**
   * Creates a new OuttakeCommand.
   */
  public OuttakeCommand(IntakeOuttakeSubsystem subsystem, double speed) {
    super(
        // The controller that the command will use
        new PIDController(1E-9, 0, 0), //TODO: set PID Controller Constants
        // This should return the measurement
        subsystem::getFlyWheelVelocity,
        // This should return the setpoint (can also be a constant)
        () -> speed,
        // This uses the output
        output -> {
          // Use the output here
          subsystem.outtake(output);
        });
    addRequirements(subsystem);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.m_controller.atSetpoint();
  }
}
