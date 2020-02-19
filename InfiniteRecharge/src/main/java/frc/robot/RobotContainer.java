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
import frc.robot.commands.RollerCommand;
import frc.lib.ConvertedXboxController;
import frc.robot.commands.FlywheelCommand;
import frc.robot.commands.GeneralDriveCommand;
import frc.robot.subsystems.AdvancedClimbSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeOuttakeSubsystem;
import frc.robot.subsystems.Superstructure;
import frc.robot.subsystems.WheelOfFortuneSubsystem;
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

  private final GeneralDriveCommand m_generalDriveCommand = new GeneralDriveCommand(m_driveSubsystem, 
                                    m_driveSubsystem::getDrivePower, m_driveSubsystem::getSteerPower);
  //private final RollerCommand   m_rollerCommand   = new RollerCommand(m_intakeOuttakeSubsystem);
  //private final FlywheelCommand m_flywheelCommand = new FlywheelCommand(m_intakeOuttakeSubsystem);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
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
  }

  
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
}




