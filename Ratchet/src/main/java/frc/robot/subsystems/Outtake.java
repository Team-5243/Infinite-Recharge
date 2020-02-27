package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.OuttakeFlywheels;

public class Outtake extends SubsystemBase {

    private final CANSparkMax leftFlywheel;
    private final TalonSRX rightFlywheel;

    public Outtake() {
        leftFlywheel = new CANSparkMax(Constants.leftFlywheel, MotorType.kBrushed);
        rightFlywheel = new TalonSRX(Constants.rightFlywheel);

        rightFlywheel.setInverted(true);

    }

    public void setFlywheels(double speed) {
        leftFlywheel.set(speed);
        rightFlywheel.set(ControlMode.PercentOutput, speed);
    }

}