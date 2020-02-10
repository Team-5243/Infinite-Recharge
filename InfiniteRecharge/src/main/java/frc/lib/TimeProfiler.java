/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib;

public class TimeProfiler {
    private static final double MILLISECONDS_PER_SECOND = 1000d;
    private double initialTime;

    public TimeProfiler(boolean runOnInitialization) {
        if (runOnInitialization) {
            start();
        }
    }

    public void reset() {
        initialTime = System.currentTimeMillis() / MILLISECONDS_PER_SECOND;
    }

    public void start() {
        reset();
    }

    public double getDeltaTime(boolean reset) {
        double deltaTime = System.currentTimeMillis() / MILLISECONDS_PER_SECOND - initialTime;
        if (reset) {
            reset();
        }

        return deltaTime;
    }

    public static void main(String... args) {
        TimeProfiler timeProfiler = new TimeProfiler(false);
        timeProfiler.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(timeProfiler.getDeltaTime(true));
    }
}
