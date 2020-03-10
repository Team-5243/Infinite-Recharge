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

    climb.getEncoder().setPosition(0d);
    climb.setIdleMode(IdleMode.kBrake);
  }

  public void setClimb(double power) {
    if(climb.getIdleMode().equals(IdleMode.kCoast) && power != 0) {
      climb.setIdleMode(IdleMode.kBrake);
    }
    climb.set(power);
  }

  public void stopClimb() {
    setClimb(0);
  }
  //TODO: Check if the use of radians/degrees is correct throughout the code
  @Override
  public void periodic() {
    if(climb.getIdleMode().equals(IdleMode.kCoast)) {
      System.out.println("Coasting");
    } else if(climb.getIdleMode().equals(IdleMode.kBrake)) {
      System.out.println("Brake");
    } else {
      System.out.println("Oh god no");
    }
  }

  public double getFrontWinchPosition() {
    return frontWinch.getEncoder().getPosition() / Constants.WINCH_CLIMB_ENCODER_PULSES_PER_INCH;
  }

  public double getBackWinchPosition() {
    return backWinch.getEncoder().getPosition() / Constants.WINCH_CLIMB_ENCODER_PULSES_PER_INCH;
  }

  public double getClimbPosition() {
    return climb.getEncoder().getPosition() / Constants.CASCADE_CLIMB_ENCODER_PULSES_PER_INCH;
  }

  public CANSparkMax getFrontWinch() {
    return frontWinch;
  }

  public CANSparkMax getBackWinch() {
    return backWinch;
  }

  public void manuallyControlFrontWinch(double power) {
    frontWinch.set(power);
    if(Math.abs(power) >= 0.2) {
      climb.setIdleMode(IdleMode.kCoast);
    }
  }

  public void manuallyControlBackWinch(double power) {
    backWinch.set(power);
  }

  public void runWinchesSynchronized(double power) {
    double error = backWinch.getEncoder().getPosition() - frontWinch.getEncoder().getPosition();

    backWinch.set(power - Constants.kP_CLIMB_WINCH_SYNCHRONIZE*(error > 1 ? error : 0));
    frontWinch.set(power + Constants.kP_CLIMB_WINCH_SYNCHRONIZE*(error > 1 ? error : 0));
  }
}
