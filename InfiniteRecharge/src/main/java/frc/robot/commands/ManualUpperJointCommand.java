/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;

public class ManualUpperJointCommand extends CommandBase {

  ClimbSubsystem m_climbSubsystem;
  private boolean up;
  
  public ManualUpperJointCommand(ClimbSubsystem subsystem, boolean up) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_climbSubsystem = subsystem;
    this.up = up;
    addRequirements(m_climbSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_climbSubsystem.manuallyControlUpperJoint(up ? 0.2 : -0.1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climbSubsystem.manuallyControlUpperJoint(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
