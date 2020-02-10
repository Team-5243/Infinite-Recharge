/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.states;

public class ConveyerStateMachine {
    private static State state = State.IDLE;

    public enum State {
        IDLE(0d, 0d), INTAKE(0.3d, 0.3d), REALIGN(0.3d, -0.3d) /*To compensate for jamming*/;

        private final double leftConveyerPower;
        private final double rightConveyerPower;

        State(double leftConveyerPower, double rightConveyerPower) {
            this.leftConveyerPower = leftConveyerPower;
            this.rightConveyerPower = rightConveyerPower;
        }

        public double getLeftConveyerPower() {
            return leftConveyerPower;
        }

        public double getRightConveyerPower() {
            return rightConveyerPower;
        }
    }

    public static void updateState(State desiredState) {
        state = desiredState;
    }

    public static boolean hasReachedGoal() {
        return true;
    }

    public static State getState() {
        return state;
    }
}
