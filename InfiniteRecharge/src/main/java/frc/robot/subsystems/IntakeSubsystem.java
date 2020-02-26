/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  CANSparkMax roller, leftConveyor;
  WPI_TalonSRX rightConveyor;
   /**
   * Creates a new IntakeSubsystem.
   */
  public IntakeSubsystem() {
    roller = new CANSparkMax(Constants.ROLLER, MotorType.kBrushed);
    leftConveyor = new CANSparkMax(Constants.LEFT_CONVEYOR, MotorType.kBrushed);
    rightConveyor = new WPI_TalonSRX(Constants.RIGHT_CONVEYOR);
 
  }

  public void runConveyor(double leftPower, double rightPower) {
    leftConveyor.set(leftPower);
    rightConveyor.set(rightPower);
  }

  public void intake(double inPower) {
      roller.set(inPower);
  }

  public void stop() {
    roller.set(0);
    leftConveyor.set(0);
    rightConveyor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}




