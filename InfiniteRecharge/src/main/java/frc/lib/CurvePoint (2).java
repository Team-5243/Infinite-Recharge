package frc.lib;

import edu.wpi.first.wpilibj.geometry.Translation2d;

public class CurvePoint {
    private double x, y;
    private double lookAheadDistance;

    public CurvePoint(double x, double y, double r) {
        this.x = x;
        this.y = y;
        lookAheadDistance = r;
    }

    public CurvePoint(Translation2d translation) {
        x = translation.getX();
        y = translation.getY();
    }

    public double getXCoordinate() {
        return x;
    }

    public double getYCoordinate() {
        return y;
    }

    public double getLookAheadDistance() {
        return lookAheadDistance;
    }

    public double getDistanceFromCenter(CurvePoint point) {
        return Math.sqrt(Math.pow((x-point.getXCoordinate()), 2) + (Math.pow ((y-point.getYCoordinate()), 2)));
    }

    public double getDistanceFromLookAhead(CurvePoint point) {
        return getDistanceFromCenter(point) - lookAheadDistance;
    }
}