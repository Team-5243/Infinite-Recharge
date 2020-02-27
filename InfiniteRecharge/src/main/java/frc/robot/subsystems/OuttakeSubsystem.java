/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import frc.robot.Constants;
public class OuttakeSubsystem extends SubsystemBase {
  CANSparkMax leftFly;
  WPI_TalonSRX rightFly;
  
  /**
   * Creates a new Outtake_Subsystem.
   */

  public OuttakeSubsystem() {
    leftFly = new CANSparkMax(Constants.LEFT_FLY, MotorType.kBrushed);
    rightFly = new WPI_TalonSRX(Constants.RIGHT_FLY);
  }  

    /*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

  public void outtake(final double outSpeed) {
    leftFly.set(outSpeed);
    rightFly.set(outSpeed);
  }

  public void stop() {
      leftFly.set(0);
      rightFly.set(0);
  } 
    
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
