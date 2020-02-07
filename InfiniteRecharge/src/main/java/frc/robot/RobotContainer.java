/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.RollerCommand;
import frc.robot.commands.FlywheelCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.IntakeOuttakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final IntakeOuttakeSubsystem m_intakeOuttakeSubsystem = new IntakeOuttakeSubsystem();
  private final ClimbSubsystem        m_climbSubsystem        = new ClimbSubsystem();

  private final RollerCommand m_rollerCommand = new RollerCommand(m_intakeOuttakeSubsystem);

  private final FlywheelCommand m_flywheelCommand = new FlywheelCommand(m_intakeOuttakeSubsystem);

  private final XboxController m_controller = new XboxController(Constants.CONTROLLER);

  JoystickButton aButton = new JoystickButton(m_controller, 1);
  JoystickButton bButton = new JoystickButton(m_controller, 2);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  public FlywheelCommand getM_flywheelCommand() {
    return m_flywheelCommand;
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    aButton.toggleWhenPressed(m_rollerCommand);
    bButton.whenPressed(m_climbSubsystem::switchEnable);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;//(m_autoCommand);
  }
  
  public XboxController getController() {
   return(m_controller);
  }

  public IntakeOuttakeSubsystem getIntakeOutakeSubsystem() {
    return(m_intakeOuttakeSubsystem);
  }

  public RollerCommand getRollerCommand() {
    return(m_rollerCommand);
  }
}




