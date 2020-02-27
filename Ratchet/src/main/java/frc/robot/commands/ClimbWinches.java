package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

public class ClimbWinches extends CommandBase {

    private final Climb climb;
    private final Climb.Direction direction;

    public ClimbWinches(Climb climb, Climb.Direction direction) {
        this.climb = climb;
        this.direction = direction;

        addRequirements(climb);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        climb.setWinches(direction == Climb.Direction.UP ? .5 : -.5);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        climb.setWinches(0);
    }

}