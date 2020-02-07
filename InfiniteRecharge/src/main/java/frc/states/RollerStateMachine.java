/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.states;

public class RollerStateMachine {
    private static State state = State.IDLE;

    public enum State {
        IDLE(0d), RUNNING(0.5d);

        final double power;
        
        State(double power) {
            this.power = power;
        }

        public double getPower() {
            return power;
        }
    }

    public static void main(String... args) {
        updateState(State.RUNNING);
        //Run rollers
        //motor.set(RollerStateMachine.getState().getPower());
    }

    public static void updateState(State desiredState) {
        state = desiredState;
    }

    public static State getState() {
        return state;
    }
}
