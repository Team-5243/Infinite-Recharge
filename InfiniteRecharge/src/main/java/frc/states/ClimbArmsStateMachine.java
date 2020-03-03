/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.states;

import frc.robot.Robot;

public class ClimbArmsStateMachine {
    private static State state = State.INIT;

    public enum State {
        INIT(Math.toRadians(40d), 0d), IDLE(Math.PI / 2, 0d), INTAKE(0d, 0d), CLIMB(Math.PI/2, Math.PI/2);

        private final double lowerAngle;
        private final double upperAngle;

        State(double lowerAngle, double upperAngle) {
            this.lowerAngle = lowerAngle;
            this.upperAngle = upperAngle;
        }

        public double getLowerAngle() {
            return lowerAngle;
        }

        public double getUpperAngle() {
            return upperAngle;
        }
    }

    public static void updateState(State desiredState) {
        state = desiredState;
    }

    public static boolean hasReachedGoal() {
        return Math.abs(getState().getLowerAngle() - Robot.getRobotContainer().getClimbSubsystem().getLowerAngle()) < 1d
            && Math.abs(getState().getUpperAngle() - Robot.getRobotContainer().getClimbSubsystem().getUpperAngle()) < 1d;
    }

    public static State getState() {
        return state;
    }
}
