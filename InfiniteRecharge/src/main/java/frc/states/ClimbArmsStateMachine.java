/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.states;

public class ClimbArmsStateMachine {
    private static State state = State.INIT;

    public enum State {
        INIT, IDLE, INTAKE, CLIMB;
    }

    public static void updateState(State desiredState) {
        state = desiredState;
    }

    public static State getState() {
        return state;
    }
}
