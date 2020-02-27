package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeRoller extends CommandBase {

    private final Intake intake;

    public IntakeRoller(Intake intake) {
        this.intake = intake;
        
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        intake.setRoller(1);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        intake.setRoller(0);
    }

}