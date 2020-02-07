package frc.robot.subsystems;

import frc.robot.Constants;

public class Superstructure {
    private static final double MAX_BUMPER_DISTANCE = 12; //in
    private static final double CLIMB_THRESHOLD = 2d;


    public boolean isClimbSafe(double lowBarAngle, double upperBarAngle) {
        return -Constants.LOW_CLIMB_BAR_LENGTH*Math.cos(lowBarAngle) + 
            Constants.UPPER_CLIMB_BAR_LENGTH*Math.cos(upperBarAngle) < MAX_BUMPER_DISTANCE - CLIMB_THRESHOLD;
    }

}