package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

    private final CANSparkMax roller, rightBelt, leftBelt;

    public Intake() {
        roller = new CANSparkMax(Constants.roller, MotorType.kBrushed);
        rightBelt = new CANSparkMax(Constants.rightBelt, MotorType.kBrushed);
        leftBelt = new CANSparkMax(Constants.leftBelt, MotorType.kBrushed);

        rightBelt.setInverted(true);
    }

    public void setRoller(double speed) {
        roller.set(speed);
    }

    public void setBelts(double speed) {
        rightBelt.set(speed);
        leftBelt.set(speed);
    }

    public void setBelts(double rightSpeed, double leftSpeed) {
        rightBelt.set(rightSpeed);
        leftBelt.set(leftSpeed);
    }

}