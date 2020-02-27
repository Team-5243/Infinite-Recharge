package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Outtake;

public class OuttakeFlywheels extends CommandBase {

    private final Outtake outtake;

    public OuttakeFlywheels(Outtake outtake) {
        this.outtake = outtake;

        addRequirements(outtake);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        outtake.setFlywheels(-.75);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        outtake.setFlywheels(0);
    }

}