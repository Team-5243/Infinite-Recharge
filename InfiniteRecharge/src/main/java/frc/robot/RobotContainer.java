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
import frc.robot.commands.ConveyorIntakeCommand;
import frc.robot.commands.ConveyorStuckCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.FlywheelCommand;
import frc.robot.commands.GeneralDriveCommand;
import frc.robot.commands.ManualLowerJointCommand;
import frc.robot.commands.ManualUpperJointCommand;
import frc.robot.commands.ManualWinchControlCommand;
import frc.robot.subsystems.ClimbSubsystem;
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
  
  private final IntakeOuttakeSubsystem m_intakeOuttakeSubsystem = new IntakeOuttakeSubsystem();
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem(this);
  private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem(m_superstructure);

  private final GeneralDriveCommand m_generalDriveCommand = new GeneralDriveCommand(m_driveSubsystem, m_driveSubsystem::getDrivePower, m_driveSubsystem::getSteerPower);

  private final DriveCommand m_driveCommand = new DriveCommand(m_driveSubsystem, m_driverController.getXboxController());

  //private final ManualWinchControlCommand m_manualWinchControlCommand = new ManualWinchControlCommand(m_climbSubsystem, m_mechanismController.getXboxController());
  //private final ManualJointCommand m_manualJointCommand = new ManualJointCommand(m_climbSubsystem, m_driverController.getXboxController());

  //private final ArmStateControllerCommand m_armStateControllerCommand = new ArmStateControllerCommand(m_climbSubsystemFinal);
  // private final ManualLowerJointCommand m_raiseLowerJointCommand = new ManualLowerJointCommand(m_climbSubsystem, true);
  // private final ManualUpperJointCommand m_raiseUpperJointCommand = new ManualUpperJointCommand(m_climbSubsystem, true);
  // private final ManualLowerJointCommand m_lowerLowerJointCommand = new ManualLowerJointCommand(m_climbSubsystem, false);
  // private final ManualUpperJointCommand m_lowerUpperJointCommand = new ManualUpperJointCommand(m_climbSubsystem, false);
  
  private final RollerCommand   m_rollerCommand   = new RollerCommand(m_intakeOuttakeSubsystem);
  private final ConveyorIntakeCommand m_conveyorIntakeCommand = new ConveyorIntakeCommand(m_intakeOuttakeSubsystem);
  private final ConveyorStuckCommand m_conveyorStuckCommand = new ConveyorStuckCommand(m_intakeOuttakeSubsystem);
  private final FlywheelCommand m_flywheelCommand = new FlywheelCommand(m_intakeOuttakeSubsystem);

  private final ArmStateControllerCommand m_armIntakeStateCommand = new ArmStateControllerCommand(m_climbSubsystem, ClimbArmsStateMachine.State.INTAKE);
  private final ArmStateControllerCommand m_armClimbStateCommand  = new ArmStateControllerCommand(m_climbSubsystem, ClimbArmsStateMachine.State.CLIMB);
  private final ArmStateControllerCommand m_armIdleStateCommand   = new ArmStateControllerCommand(m_climbSubsystem, ClimbArmsStateMachine.State.IDLE);

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
    //m_mechanismController.bButton.whenPressed(m_climbSubsystem::switchEnable);
    //m_driverController.aButton.whenPressed(m_armStateControllerCommand);
    //m_driverController.bButton.whenPressed(() -> ClimbArmsStateMachine.updateState(ClimbArmsStateMachine.State.INIT));
    
    m_mechanismController.xButton.toggleWhenPressed(m_rollerCommand);
    m_mechanismController.rightBumper.whileHeld(m_conveyorIntakeCommand);
    m_mechanismController.leftBumper.whileHeld(m_conveyorStuckCommand);
    //m_mechanismController.aButton.whileHeld(m_lowerLowerJointCommand);
    //m_mechanismController.yButton.whileHeld(m_raiseLowerJointCommand);

   // m_driverController.aButton.whileHeld(m_lowerUpperJointCommand);
    //m_driverController.yButton.whileHeld(m_raiseUpperJointCommand);
    m_driverController.bButton.whileHeld(m_flywheelCommand);

    m_mechanismController.aButton.whenPressed(m_armIntakeStateCommand);
    m_mechanismController.bButton.whenPressed(m_armIdleStateCommand);
    m_mechanismController.yButton.whenPressed(m_armClimbStateCommand);
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

  public ClimbSubsystem getClimbSubsystem() {
    return m_climbSubsystem;
  }
}




