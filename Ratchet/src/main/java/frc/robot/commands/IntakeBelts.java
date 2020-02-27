package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeBelts extends CommandBase {

    private final Intake intake;

    private boolean blocked;

    public IntakeBelts(Intake intake) {
        this(intake, false);
    }

    public IntakeBelts(Intake intake, boolean blocked) {
        this.intake = intake;
        this.blocked = blocked;

        addRequirements(intake);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        if (blocked) {
            intake.setBelts(.25, -.25);
        } else {
            intake.setBelts(.5);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        intake.setBelts(0);
    }

}