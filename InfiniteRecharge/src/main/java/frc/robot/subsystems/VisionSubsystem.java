/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {
  private final NetworkTable m_networkTable;

  public VisionSubsystem() {
    m_networkTable = NetworkTableInstance.getDefault().getTable("limelight");
  }

  public NetworkTable getTable() {
    return m_networkTable;
  }

  public double getX() {
    return m_networkTable.getEntry("tx").getDouble(0.0);
  }

  public double getY() {
    return m_networkTable.getEntry("ty").getDouble(0.0);
  }

  public double getArea() {
    return m_networkTable.getEntry("ta").getDouble(0.0);
  }

  public double getShortestSide() {
    return m_networkTable.getEntry("tshort").getDouble(0.0);
  }

  public double getLongestSide() {
    return m_networkTable.getEntry("tlong").getDouble(0.0);
  }

  public double getHorizontal() {
    return m_networkTable.getEntry("thor").getDouble(0.0);
  }

  public double getVertical() {
    return m_networkTable.getEntry("tvert").getDouble(0.0);
  }

  public double getVerticalOffset() {
    return m_networkTable.getEntry("ty").getDouble(0.0);
  }

  public double getHorizontalOffset() {
    return m_networkTable.getEntry("tx").getDouble(0.0);
  }

  /*
  LED Mode:
  0 - Use the LED Mode set in current pipeline
  1 - Off
  2 - Blink
  3 - On
  Cam Mode:
  0 - Vision
  1 - Driver
  */

  public void setLed(final int mode) {
    m_networkTable.getEntry("ledMode").setNumber(mode);
  }

  public void setCamMode(final int mode) {
    m_networkTable.getEntry("camMode").setNumber(mode);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
