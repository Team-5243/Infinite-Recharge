/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class ClimbSubsystem extends PIDSubsystem {
  private CANSparkMax virtualFourBar;
  private CANSparkMax leftWinch, rightWinch;

  private double power;

  /**
   * Creates a new ClimbSubsystem.
   */
  public ClimbSubsystem() {
    super(new PIDController(Constants.kP_CLIMB_SYNCHRONIZE, Constants.kI_CLIMB_SYNCHRONIZE, Constants.kD_CLIMB_SYNCHRONIZE));
    virtualFourBar = new CANSparkMax(Constants.VIRTUAL_FOUR_BAR, MotorType.kBrushless);
    leftWinch      = new CANSparkMax(Constants.LEFT_WINCH_CLIMB, MotorType.kBrushless);
    rightWinch     = new CANSparkMax(Constants.RIGHT_WINCH_CLIMB, MotorType.kBrushless);
    power          = 0d;
  }

  public void setPower(double power) {
    this.power = power;
  }

  public void runFourBar(double power) {
    virtualFourBar.set(power);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    leftWinch.set(power + output);
    rightWinch.set(power - output);
  }

  @Override
  public double getMeasurement() {
    return (rightWinch.getEncoder().getPosition() - leftWinch.getEncoder().getPosition()) / Constants.WINCH_CLIMB_ENCODER_PULSES_PER_INCH;
  }

  public void switchEnable() {
    if(isEnabled()) {
      disable();
    } else {
      enable();
    }
  }
}
