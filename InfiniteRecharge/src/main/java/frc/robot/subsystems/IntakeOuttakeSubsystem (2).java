/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeOuttakeSubsystem extends SubsystemBase {
  CANSparkMax roller, leftFly, rightFly;
  TalonSRX rightConveyor, leftConveyor;
  
  public IntakeOuttakeSubsystem() {
    roller = new CANSparkMax(Constants.ROLLER, MotorType.kBrushed);

    leftConveyor = new TalonSRX(Constants.LEFT_CONVEYOR);
    rightConveyor = new TalonSRX(Constants.RIGHT_CONVEYOR);

    leftFly = new CANSparkMax(Constants.LEFT_FLY, MotorType.kBrushed);
    rightFly = new CANSparkMax(Constants.RIGHT_FLY, MotorType.kBrushed);

    leftFly.setIdleMode(IdleMode.kCoast);
    rightFly.setIdleMode(IdleMode.kCoast);

    leftFly.follow(rightFly, false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
