/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {
  private final CANSparkMax frontWinch, backWinch;
  private final CANSparkMax climb;
  
  /**
   * Creates a new ClimbSubsystemFinal.
   */
  public ClimbSubsystem() {
    frontWinch = new CANSparkMax(Constants.FRONT_WINCH_CLIMB, MotorType.kBrushless);
    backWinch = new CANSparkMax(Constants.BACK_WINCH_CLIMB, MotorType.kBrushless);
    climb = new CANSparkMax(Constants.CLIMB, MotorType.kBrushless);
  }

  public void setClimb(double power) {
    climb.set(power);
  }

  public void stopClimb() {
    setClimb(0);
  }
  //TODO: Check if the use of radians/degrees is correct throughout the code
  @Override
  public void periodic() {
   
  }

  public double getUpperWinchPosition() {
    return frontWinch.getEncoder().getPosition() / Constants.WINCH_CLIMB_ENCODER_PULSES_PER_INCH;
  }

  public double getLowerWinchPosition() {
    return backWinch.getEncoder().getPosition() / Constants.WINCH_CLIMB_ENCODER_PULSES_PER_INCH;
  }

  public CANSparkMax getlowerWinch() {
    return frontWinch;
  }

  public CANSparkMax getUpperWinch() {
    return backWinch;
  }

  public void manuallyControlUpperWinch(double power) {
    frontWinch.set(power);
  }

  public void manuallyControlLowerWinch(double power) {
    backWinch.set(power);
  }
}
