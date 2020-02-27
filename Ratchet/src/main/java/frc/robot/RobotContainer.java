package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.ClimbLift;
import frc.robot.commands.ClimbWinches;
import frc.robot.commands.IntakeBelts;
import frc.robot.commands.IntakeRoller;
import frc.robot.commands.OuttakeFlywheels;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Outtake;
import frc.robot.util.MappedXboxController;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  public static final MappedXboxController controller1 = new MappedXboxController(Constants.controller1);
  public static final MappedXboxController controller2 = new MappedXboxController(Constants.controller2);

  private final Drive drive = new Drive();
  private final Intake intake = new Intake(); 
  private final Outtake outtake = new Outtake();
  private final Climb climb = new Climb();

  private final IntakeRoller intakeRoller = new IntakeRoller(intake);
  private final IntakeBelts intakeBelts = new IntakeBelts(intake);
  private final IntakeBelts intakeBeltsBlocked = new IntakeBelts(intake, true);
  private final OuttakeFlywheels outtakeFlywheels = new OuttakeFlywheels(outtake);
  private final ClimbLift climbLiftUp = new ClimbLift(climb, Climb.Direction.UP);
  private final ClimbLift climbLiftDown = new ClimbLift(climb, Climb.Direction.DOWN);
  
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    controller2.x.whileHeld(intakeRoller);
    controller1.b.whileHeld(outtakeFlywheels);
    controller2.y.whileHeld(climbLiftUp);
    controller2.a.whileHeld(climbLiftDown);
    controller2.rb.whileHeld(intakeBelts);
    controller2.lb.whileHeld(intakeBeltsBlocked);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }

}
