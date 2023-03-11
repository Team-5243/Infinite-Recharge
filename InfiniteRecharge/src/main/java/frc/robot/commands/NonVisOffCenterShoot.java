/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class NonVisOffCenterShoot extends SequentialCommandGroup {
  /**
   * Creates a new NonVisOffCenterShoot.
   */
  public NonVisOffCenterShoot(RobotContainer m_robotContainer) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new DriveStraightCommand(m_robotContainer.getDriveSubsystem(), 1, 100), 
          new FlywheelCommand(m_robotContainer.getOuttakeSubsystem()),
          new DriveStraightCommand(m_robotContainer.getDriveSubsystem(), -1, 120));
  }
}