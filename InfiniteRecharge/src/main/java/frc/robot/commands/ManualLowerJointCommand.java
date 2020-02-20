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

public class ManualLowerJointCommand extends CommandBase {

  ClimbSubsystem m_climbSubsystem;
  DoubleSupplier m_triggerVal;
  
  public ManualLowerJointCommand(ClimbSubsystem subsystem, DoubleSupplier triggerVal) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_climbSubsystem = subsystem;
    m_triggerVal = triggerVal;
    addRequirements(m_climbSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_climbSubsystem.runLowerJoint(m_triggerVal.getAsDouble() * 0.05);
    System.out.println(m_triggerVal.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climbSubsystem.runLowerJoint(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
