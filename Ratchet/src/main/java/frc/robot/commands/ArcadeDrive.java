package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drive;

import static frc.robot.util.MathUtils.clamp;

public class ArcadeDrive extends CommandBase {

    private final Drive drive;

    private final double maxThrottle;

    public ArcadeDrive(Drive drive) {
        this(drive, 1);
    }

    public ArcadeDrive(Drive drive, double maxThrottle) {
        this.drive = drive;
        this.maxThrottle = maxThrottle;

        addRequirements(drive);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        double right = clamp(-RobotContainer.controller1.leftY() - RobotContainer.controller1.rightX(), -maxThrottle, maxThrottle);
        double left = clamp(-RobotContainer.controller1.leftY() + RobotContainer.controller1.rightX(), -maxThrottle, maxThrottle);
        drive.set(right, left);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        drive.set(0, 0);
    }

}