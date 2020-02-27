package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climb extends SubsystemBase {

    private final CANSparkMax firstStage, secondStage, frontWinch, backWinch;

    public Climb() {
        firstStage = new CANSparkMax(Constants.firstStageClimb, MotorType.kBrushless);
        secondStage = new CANSparkMax(Constants.secondStageClimb, MotorType.kBrushless);
        frontWinch = new CANSparkMax(Constants.frontWinch, MotorType.kBrushless);
        backWinch = new CANSparkMax(Constants.backWinch, MotorType.kBrushless);
    }

    public void setLift(double speed) {
        firstStage.set(speed);
        // secondStage.set(2 * speed);
    }

    public void setWinches(double speed) {
        frontWinch.set(speed);
        backWinch.set(speed);
    }

    public enum Direction {
        UP,
        DOWN;
    }

}