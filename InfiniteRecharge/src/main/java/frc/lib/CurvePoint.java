package frc.lib;

public class CurvePoint{
    private double x, y;
    private double lookAheadDistance;

    CurvePoint(double x, double y, double r) {
        this.x = x;
        this.y = y;
        lookAheadDistance = r;
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