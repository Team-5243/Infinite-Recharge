package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

public class ClimbLift extends CommandBase {

    private final Climb climb;
    private final Climb.Direction direction;

    public ClimbLift(Climb climb, Climb.Direction direction) {
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
        climb.setLift(direction == Climb.Direction.UP ? .5 : -.1);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        climb.setLift(0);
    }

}