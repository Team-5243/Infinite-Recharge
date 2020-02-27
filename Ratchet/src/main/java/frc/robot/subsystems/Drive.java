package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.ArcadeDrive;

public class Drive extends SubsystemBase {

    private final CANSparkMax frontRight, frontLeft, backRight, backLeft;

    public Drive() {
        frontRight = new CANSparkMax(Constants.frontRight, MotorType.kBrushless);
        frontLeft = new CANSparkMax(Constants.frontLeft, MotorType.kBrushless);
        backRight = new CANSparkMax(Constants.backRight, MotorType.kBrushless);
        backLeft = new CANSparkMax(Constants.backLeft, MotorType.kBrushless);

        backRight.follow(frontRight);
        backLeft.follow(frontLeft);

        frontRight.setInverted(true);

        setDefaultCommand(new ArcadeDrive(this));
    }

    public void set(double right, double left) {
        frontRight.set(right);
        frontLeft.set(left);
    }

}