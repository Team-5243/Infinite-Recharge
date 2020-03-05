/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib;

import frc.robot.Constants;

public class DualJointedArmDynamicModel {
    private final double lowerBarMass; //kg
    private final double upperBarMass; //kg
    private final double momentOfInertiaLowerBar; //kg m^2 - with respect to axis of rotation of the bar
    private final double momentOfInertiaUpperBar; //kg m^2 - with respect to axis of rotation of the bar
    private final double lowerBarLength; //m
    private final double upperBarLength; //m
    private final double lowerBarDistanceFromPivotToCenterOfMass; //m
    private final double upperBarDistanceFromPivotToCenterOfMass; //m

    private double lowerBarAngle;
    private double upperBarAngle;

    public DualJointedArmDynamicModel(double lowerBarMass, double upperBarMass, double momentOfInertiaLowerBar,
                                    double momentOfInertiaUpperBar, double lowerBarLength, double upperBarLength,
                                    double lowerBarDistanceFromPivotToCenterOfMass, double upperBarDistanceFromPivotToCenterOfMass) {
        this.lowerBarMass = lowerBarMass;
        this.upperBarMass = upperBarMass;
        this.momentOfInertiaLowerBar = momentOfInertiaLowerBar;
        this.momentOfInertiaUpperBar = momentOfInertiaUpperBar;
        this.lowerBarLength = lowerBarLength;
        this.upperBarLength = upperBarLength;
        this.lowerBarDistanceFromPivotToCenterOfMass = lowerBarDistanceFromPivotToCenterOfMass;
        this.upperBarDistanceFromPivotToCenterOfMass = upperBarDistanceFromPivotToCenterOfMass;
    }

    public double getLowerJointTorque(double lowerBarAngularVelocity, double upperBarAngularVelocity,
                                    double lowerBarAngularAcceleration, double upperBarAngularAcceleration) {

        double cosLowerAngle = Math.cos(lowerBarAngle);
        double sinLowerAngle = Math.sin(lowerBarAngle);
        double cosUpperAngle = Math.cos(upperBarAngle);
        double sinUpperAngle = Math.sin(upperBarAngle);
        double cosLowerPlusUpperAngle = Math.cos(lowerBarAngle + upperBarAngle);
        double sinLowerPlusUpperAngle = Math.sin(lowerBarAngle + upperBarAngle);

        return (lowerBarMass*getLowerBarHorizontalDistanceFromCenterOfMass()*Constants.GRAVITATIONAL_ACCELERATION*cosLowerAngle)
            - (lowerBarMass*getLowerBarVerticalDistanceFromCenterOfMass()*Constants.GRAVITATIONAL_ACCELERATION*sinLowerAngle)
            + (momentOfInertiaLowerBar*lowerBarAngularAcceleration) + upperBarMass*(Constants.GRAVITATIONAL_ACCELERATION*lowerBarLength*cosLowerAngle + Math.pow(lowerBarLength, 2)*lowerBarAngularAcceleration)
            + ((upperBarMass*getUpperBarHorizontalDistanceFromCenterOfMass()) * (Constants.GRAVITATIONAL_ACCELERATION*cosLowerPlusUpperAngle + 2*(lowerBarMass*lowerBarAngularAcceleration*cosUpperAngle) + lowerBarLength*upperBarAngularAcceleration*cosUpperAngle
            - 2*(lowerBarLength*lowerBarAngularVelocity*upperBarAngularVelocity*sinUpperAngle) - lowerBarLength*Math.pow(upperBarAngularVelocity, 2)*sinUpperAngle))
            + ((upperBarMass*getUpperBarVerticalDistanceFromCenterOfMass()) * (-Constants.GRAVITATIONAL_ACCELERATION*sinLowerPlusUpperAngle) - 2*(lowerBarLength*lowerBarAngularAcceleration*sinUpperAngle) - (lowerBarLength*upperBarAngularAcceleration*sinUpperAngle)
            - 2*(lowerBarLength*lowerBarAngularVelocity*upperBarAngularVelocity*cosUpperAngle) - (lowerBarLength*Math.pow(upperBarAngularVelocity, 2)*cosUpperAngle)) + momentOfInertiaUpperBar*(lowerBarAngularAcceleration + upperBarAngularAcceleration);
        // return (lowerBarMass*getLowerBarHorizontalDistanceFromCenterOfMass()*Constants.GRAVITATIONAL_ACCELERATION * cosLowerAngle) -
        //     (lowerBarMass*getLowerBarVerticalDistanceFromCenterOfMass()*Constants.GRAVITATIONAL_ACCELERATION * sinLowerAngle) +
        //     (momentOfInertiaLowerBar*lowerBarAngularAcceleration) 
        //     + (upperBarMass*((Constants.GRAVITATIONAL_ACCELERATION * lowerBarLength * cosLowerAngle) + 
        //     Math.pow(lowerBarLength,2)*lowerBarAngularAcceleration) + ((upperBarMass*getUpperBarHorizontalDistanceFromCenterOfMass())*((Constants.GRAVITATIONAL_ACCELERATION*cosLowerPlusUpperAngle) + 
        //     (2*lowerBarLength*lowerBarAngularAcceleration*cosUpperAngle) + (lowerBarLength*upperBarAngularAcceleration*cosUpperAngle) - (2*lowerBarLength*
        //     lowerBarAngularVelocity*upperBarAngularVelocity*sinUpperAngle) - (lowerBarLength*Math.pow(upperBarAngularVelocity,2)*sinUpperAngle)))) +
        //     ((upperBarMass*getUpperBarVerticalDistanceFromCenterOfMass()) * (-(Constants.GRAVITATIONAL_ACCELERATION*sinLowerPlusUpperAngle) - (2*lowerBarLength*
        //     lowerBarAngularAcceleration*sinUpperAngle) - (lowerBarLength*upperBarAngularAcceleration*sinUpperAngle) - (2*lowerBarLength*lowerBarAngularVelocity*upperBarAngularVelocity*
        //     cosUpperAngle) - (lowerBarLength*Math.pow(upperBarAngularVelocity, 2)*cosUpperAngle))) + (momentOfInertiaUpperBar*(lowerBarAngularAcceleration+upperBarAngularAcceleration));
    }

    public double getUpperJointTorque(double lowerBarAngularVelocity, double upperBarAngularVelocity,
                                    double lowerBarAngularAcceleration, double upperBarAngularAcceleration) {
        double cosLowerAngle = Math.cos(lowerBarAngle);
        double sinLowerAngle = Math.sin(lowerBarAngle);
        double cosUpperAngle = Math.cos(upperBarAngle);
        double sinUpperAngle = Math.sin(upperBarAngle);
        double cosLowerPlusUpperAngle = Math.cos(lowerBarAngle + upperBarAngle);
        double sinLowerPlusUpperAngle = Math.sin(lowerBarAngle + upperBarAngle);
        return upperBarMass * getUpperBarHorizontalDistanceFromCenterOfMass() * (Constants.GRAVITATIONAL_ACCELERATION * 
            cosLowerPlusUpperAngle + lowerBarLength * lowerBarAngularAcceleration * cosUpperAngle + lowerBarLength * 
            lowerBarAngularVelocity * lowerBarAngularVelocity * sinUpperAngle) + upperBarMass * 
            getUpperBarVerticalDistanceFromCenterOfMass() * (-Constants.GRAVITATIONAL_ACCELERATION *
             sinLowerPlusUpperAngle - lowerBarLength * lowerBarAngularAcceleration * sinUpperAngle + lowerBarLength * 
             lowerBarAngularVelocity * lowerBarAngularVelocity * cosUpperAngle) + momentOfInertiaUpperBar *
              (lowerBarAngularAcceleration + upperBarAngularAcceleration);
    }

    public static void main(String... args) {
        DualJointedArmDynamicModel model = new DualJointedArmDynamicModel(10d, 10d, 10d, 10d, 1d, 1d, 0.5d, 0.5d);
    }

    public double getLowerBarHorizontalDistanceFromCenterOfMass() {
        return lowerBarDistanceFromPivotToCenterOfMass * Math.cos(lowerBarAngle);
    }

    public double getLowerBarVerticalDistanceFromCenterOfMass() {
        return lowerBarDistanceFromPivotToCenterOfMass * Math.sin(lowerBarAngle);
    }

    public double getUpperBarHorizontalDistanceFromCenterOfMass() {
        return upperBarDistanceFromPivotToCenterOfMass * Math.cos(upperBarAngle);
    }

    public double getUpperBarVerticalDistanceFromCenterOfMass() {
        return upperBarDistanceFromPivotToCenterOfMass * Math.sin(upperBarAngle);
    }

    public void updateBarAngles(double lowerBarAngle, double upperBarAngle) {
        this.lowerBarAngle = lowerBarAngle;
        //this.upperBarAngle = Math.toRadians(180d) + upperBarAngle - lowerBarAngle;
        this.upperBarAngle = upperBarAngle + lowerBarAngle - Math.toRadians(180d);
    }
}
