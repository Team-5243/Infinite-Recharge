/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib;

public interface IMotionProfile {
    void generateProfile();
    double getDuration();
    double getPosition();
    double getVelocity();
    double getAcceleration();
    double getJerk();
    double getPosition(double timeStamp);
    double getVelocity(double timeStamp);
    double getAcceleration(double timeStamp);
    double getJerk(double timeStamp);
    double getRuntime();
    boolean isDone(double timeStamp);
    boolean isDone();
    boolean hasStarted();
    void start();
    void setNegative(boolean negative);
    boolean isNegative();
}
