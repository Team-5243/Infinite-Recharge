/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.commands.RollerCommand;
import frc.lib.ConvertedXboxController;
import frc.robot.commands.ArmStateControllerCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.FlywheelCommand;
import frc.robot.commands.GeneralDriveCommand;
import frc.robot.commands.ManualJointCommand;
import frc.robot.commands.ManualLowerJointCommand;
import frc.robot.commands.ManualUpperJointCommand;
import frc.robot.subsystems.AdvancedClimbSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.ClimbSubsystemFinal;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeOuttakeSubsystem;
import frc.robot.subsystems.Superstructure;
import frc.robot.subsystems.WheelOfFortuneSubsystem;
import frc.states.ClimbArmsStateMachine;
import edu.wpi.first.wpilibj2.command.Subsystem;
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

  private final ConvertedXboxController m_driverController = new ConvertedXboxController(Constants.DRIVER_CONTROLLER);
  private final ConvertedXboxController m_mechanismController = new ConvertedXboxController(Constants.MECHANISM_CONTROLLER);

  private final AHRS m_gyro = new AHRS();

  private final Superstructure m_superstructure = new Superstructure();
  
  //private final IntakeOuttakeSubsystem  m_intakeOuttakeSubsystem  = new IntakeOuttakeSubsystem();
  //private final AdvancedClimbSubsystem  m_advancedClimbSubsystem  = new AdvancedClimbSubsystem(m_superstructure);
  private final DriveSubsystem          m_driveSubsystem          = new DriveSubsystem(this);
  //private final WheelOfFortuneSubsystem m_wheelOfFortuneSubsystem = new WheelOfFortuneSubsystem();
  //private final ClimbSubsystem          m_climbSubsystem          = new ClimbSubsystem();

  private final ClimbSubsystemFinal     m_climbSubsystemFinal     = new ClimbSubsystemFinal(m_superstructure);

  private final GeneralDriveCommand m_generalDriveCommand = new GeneralDriveCommand(m_driveSubsystem, 
                                    m_driveSubsystem::getDrivePower, m_driveSubsystem::getSteerPower);
  private final DriveCommand m_driveCommand = new DriveCommand(m_driveSubsystem, m_driverController.getXboxController());

  //private final ManualJointCommand m_manualJointCommand = new ManualJointCommand(m_climbSubsystem, m_driverController.getXboxController());

  private final ArmStateControllerCommand m_armStateControllerCommand = new ArmStateControllerCommand(m_climbSubsystemFinal);
  // private final ManualLowerJointCommand m_lowerJointCommand = new ManualLowerJointCommand(m_climbSubsystem, () -> m_driverController.getXboxController().getTriggerAxis(Hand.kRight));
  // private final ManualUpperJointCommand m_upperJointCommand = new ManualUpperJointCommand(m_climbSubsystem, () -> m_driverController.getXboxController().getTriggerAxis(Hand.kLeft));
  //private final RollerCommand   m_rollerCommand   = new RollerCommand(m_intakeOuttakeSubsystem);
  //private final FlywheelCommand m_flywheelCommand = new FlywheelCommand(m_intakeOuttakeSubsystem);
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_climbSubsystemFinal.start();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //m_mechanismController.aButton.toggleWhenPressed(m_rollerCommand);
    //m_mechanismController.bButton.whenPressed(m_climbSubsystem::switchEnable);
    m_driverController.aButton.whenPressed(m_armStateControllerCommand);
    m_driverController.bButton.whenPressed(() -> ClimbArmsStateMachine.updateState(ClimbArmsStateMachine.State.INIT));
  }

  // public ManualJointCommand getManualJointCommand() {
  //   return m_manualJointCommand;
  // }
  
  public AHRS getGyro() {
    return(m_gyro);
  }

  public Superstructure getSuperstructure() {
    return m_superstructure;
  }
  
  public ConvertedXboxController getDriverController() {
    return m_driverController;
  }

  public ConvertedXboxController getMechanismController() {
    return m_mechanismController;
  }

  public IntakeOuttakeSubsystem getIntakeOutakeSubsystem() {
    return null;//m_intakeOuttakeSubsystem;
  }

  public DriveSubsystem getDriveSubsystem() {
    return m_driveSubsystem;
  }

  public WheelOfFortuneSubsystem getWheelOfFortuneSubsystem(){
    return null; //m_wheelOfFortuneSubsystem;
  }

  public AdvancedClimbSubsystem getAdvancedClimbSubsystem() {
    return null; //m_advancedClimbSubsystem;
  }

  public FlywheelCommand getFlyWheelCommand() {
    return null; //m_flywheelCommand;
  }

  public RollerCommand getRollerCommand() {
    return null; //m_rollerCommand;
  }

  public GeneralDriveCommand getGeneralDriveCommand() {
    return m_generalDriveCommand;
  }

  public DriveCommand getDriveCommand() {
    return m_driveCommand;
  }

  // public Subsystem getClimbSubsystem() {
  //   return m_climbSubsystem;
  // }
}




