/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.ConvertedXboxController;
import frc.lib.IMotionProfile;
import frc.lib.ResidualVibrationReductionMotionProfiler;
import frc.robot.subsystems.ClimbSubsystem;

public class ProfiledClimbCommand extends CommandBase {
  private static final double MAX_EXTENSION_HEIGHT = 15d;

  private ClimbSubsystem m_climbSubsystem;
  private ConvertedXboxController m_controller;
  private IMotionProfile m_motionProfile;
  private Direction m_direction;

  public enum Direction {
    EXTEND, RETRACT
  }

  /**
   * Creates a new ProfiledClimbCommand.
   */
  public ProfiledClimbCommand(ClimbSubsystem climbSubsystem, ConvertedXboxController controller) {
    m_controller = controller;
    addRequirements(m_climbSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_motionProfile = null;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_motionProfile == null || m_motionProfile.isDone()) {
      m_climbSubsystem.setClimb(m_controller.getXboxController().getTriggerAxis(Hand.kRight) - m_controller.getXboxController().getTriggerAxis(Hand.kLeft));
      if(m_controller.getDpadValue() == 0) {
        //Climb extends
        m_direction = Direction.EXTEND;
        m_motionProfile = new ResidualVibrationReductionMotionProfiler(m_climbSubsystem.getClimbPosition(), MAX_EXTENSION_HEIGHT, 5d, 10d);
        m_motionProfile.start();
      } else if(m_controller.getDpadValue() == 180) {
        //Climb retracts
        m_direction = Direction.RETRACT;
        m_motionProfile = new ResidualVibrationReductionMotionProfiler(m_climbSubsystem.getClimbPosition(), 0d, 5d, 10d);
        m_motionProfile.start();
      }
    } else if(m_motionProfile != null) {
      //There is a motion profile to follow
      if(m_direction.equals(Direction.EXTEND)) {
        final double kP = 1E-9d;
        final double kS = 0.001d;
        final double kV = 0d;
        final double kA = 0d;
        double error = m_motionProfile.getPosition() - m_climbSubsystem.getClimbPosition();
        double output = kS + kP * error +
                        kV * m_motionProfile.getVelocity() +
                        kA * m_motionProfile.getAcceleration();
        m_climbSubsystem.setClimb(output);
      } else if(m_direction.equals(Direction.RETRACT)) {
        final double kP = 1E-9d;
        final double kS = 0.001d;
        final double kV = 0d;
        final double kA = 0d;
        double error = m_motionProfile.getPosition() - m_climbSubsystem.getClimbPosition();
        double output = kS + kP * error +
                        kV * m_motionProfile.getVelocity() +
                        kA * m_motionProfile.getAcceleration();
        m_climbSubsystem.setClimb(output);
      }
    } else {
      m_climbSubsystem.setClimb(m_controller.getXboxController().getTriggerAxis(Hand.kRight) - m_controller.getXboxController().getTriggerAxis(Hand.kLeft));
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climbSubsystem.stopClimb();
    m_climbSubsystem.manuallyControlBackWinch(0);
    m_climbSubsystem.manuallyControlFrontWinch(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
