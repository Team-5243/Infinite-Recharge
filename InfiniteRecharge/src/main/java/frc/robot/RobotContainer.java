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
import frc.robot.commands.ToggleIntakeCommand;
import frc.lib.ConvertedXboxController;
import frc.robot.commands.ClimbCommand;
import frc.robot.commands.ConveyorIntakeCommand;
import frc.robot.commands.ConveyorStuckCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.FlywheelCommand;
import frc.robot.commands.GeneralDriveCommand;
import frc.robot.commands.ManualWinchControlCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.OuttakeSubsystem;
import frc.robot.subsystems.PneumaticIntakeSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.subsystems.WheelOfFortuneSubsystem;

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

  private final OuttakeSubsystem m_outtakeSubsystem = new OuttakeSubsystem();
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem(this);
  private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();
  private final PneumaticIntakeSubsystem m_pneumaticIntakeSubsystem = new PneumaticIntakeSubsystem();
  private final VisionSubsystem m_visionSubsystem = new VisionSubsystem();

  private final GeneralDriveCommand m_generalDriveCommand = new GeneralDriveCommand(m_driveSubsystem, m_driveSubsystem::getDrivePower, m_driveSubsystem::getSteerPower);

  private final DriveCommand m_driveCommand = new DriveCommand(m_driveSubsystem, m_driverController.getXboxController());

  //private final ManualWinchControlCommand m_manualWinchControlCommand = new ManualWinchControlCommand(m_climbSubsystem, m_mechanismController.getXboxController());
  //private final ManualJointCommand m_manualJointCommand = new ManualJointCommand(m_climbSubsystem, m_driverController.getXboxController());

  //private final ArmStateControllerCommand m_armStateControllerCommand = new ArmStateControllerCommand(m_climbSubsystemFinal);
 
  private final RollerCommand   m_rollerCommand   = new RollerCommand(m_pneumaticIntakeSubsystem, m_outtakeSubsystem);
  private final ConveyorIntakeCommand m_conveyorIntakeCommand = new ConveyorIntakeCommand(m_outtakeSubsystem);
  private final ConveyorStuckCommand m_conveyorStuckCommand = new ConveyorStuckCommand(m_outtakeSubsystem);
  private final FlywheelCommand m_flywheelCommand = new FlywheelCommand(m_outtakeSubsystem);
  private final ToggleIntakeCommand m_toggleIntakeCommand = new ToggleIntakeCommand(m_pneumaticIntakeSubsystem);
  private final ManualWinchControlCommand m_winchControlCommand = new ManualWinchControlCommand(m_climbSubsystem, m_mechanismController.getXboxController());
  private final ClimbCommand m_climbCommand = new ClimbCommand(m_climbSubsystem, m_mechanismController);
 // private final ArmStateControllerCommand m_armIntakeStateCommand = new ArmStateControllerCommand(m_climbSubsystem, ClimbArmsStateMachine.State.INTAKE);
 // private final ArmStateControllerCommand m_armClimbStateCommand  = new ArmStateControllerCommand(m_climbSubsystem, ClimbArmsStateMachine.State.CLIMB);
 // private final ArmStateControllerCommand m_armIdleStateCommand   = new ArmStateControllerCommand(m_climbSubsystem, ClimbArmsStateMachine.State.IDLE);

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
    m_mechanismController.xButton.toggleWhenPressed(m_rollerCommand);
    m_mechanismController.rightBumper.whileHeld(m_conveyorIntakeCommand);
    m_mechanismController.leftBumper.whileHeld(m_conveyorStuckCommand);
    m_mechanismController.aButton.whenPressed(m_toggleIntakeCommand);

    m_driverController.bButton.whileHeld(m_flywheelCommand);
  }
  
  public AHRS getGyro() {
    return(m_gyro);
  }
  
  public ConvertedXboxController getDriverController() {
    return m_driverController;
  }

  public ConvertedXboxController getMechanismController() {
    return m_mechanismController;
  }

  public OuttakeSubsystem getOuttakeSubsystem() {
    return m_outtakeSubsystem;
  }

  public DriveSubsystem getDriveSubsystem() {
    return m_driveSubsystem;
  }

  public WheelOfFortuneSubsystem getWheelOfFortuneSubsystem(){
    return null; //m_wheelOfFortuneSubsystem;
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

  public ClimbCommand getClimbCommand() {
    return m_climbCommand;
  }

  public ClimbSubsystem getClimbSubsystem() {
    return m_climbSubsystem;
  }

  public ManualWinchControlCommand getManualWinchControlCommand() {
    return m_winchControlCommand;
  }

  public PneumaticIntakeSubsystem getPneumaticIntakeSubsystem(){
    return null;//m_pneumaticIntakeSubsystem;
  }

  public VisionSubsystem getVisionSubsystem() {
    return m_visionSubsystem;
  }
}




