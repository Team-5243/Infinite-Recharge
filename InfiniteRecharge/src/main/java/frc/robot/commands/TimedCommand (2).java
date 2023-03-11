/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.TimeProfiler;

public abstract class TimedCommand extends CommandBase {
  private TimeProfiler timeProfiler;
  private double timeToWait;

  /**
   * Creates a new TimedCommand.
   */
  public TimedCommand(double timeToWait) {
    timeProfiler = new TimeProfiler(false);
    this.timeToWait = timeToWait;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timeProfiler.start();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timeProfiler.getDeltaTime(false) >= timeToWait;
  }
}
