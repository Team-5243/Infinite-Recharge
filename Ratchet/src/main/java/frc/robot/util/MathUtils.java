package frc.robot.util;

public class MathUtils {

    /**
     * Returns a given value clamped to a minimum and maximum value
     * 
     * @param val the value to clamp
     * @param min the minimum value that can be returned
     * @param max the maximum value that can be returned
     * @return the clamped value
     */
    public static double clamp(double val, double min, double max) {
        return Math.max(Math.min(max, val), min);
    }

    /**
     * Returns whether a value lies in a given range
     * 
     * @param val       the value to check
     * @param min       the lower bound of the range
     * @param max       the upper bound of the range
     * @param inclusive whether to include the bounds of the range or not
     * @return
     */
    public static boolean inRange(double val, double min, double max, boolean inclusive) {
        return inclusive ? val >= min && val <= max : val > min && val < max;
    }

    /**
     * Returns whether an actual value is within a tolerance of a target value
     * 
     * @param actual    the actual value to check
     * @param target    the target value to check against
     * @param tolerance the range that the actual value can lie within the target
     *                  value
     * @param inclusive whether to include the bounds of the range or not
     * @return
     */
    public static boolean inTolerance(double actual, double target, double tolerance, boolean inclusive) {
        return inRange(actual, target - tolerance, target + tolerance, inclusive);
    }

    /**
     * Uses the equation y = -(2x-1)^2 + 1 to determine speed along a path
     * 
     * @param percent how far along the path the actuator is
     * @return speed at the given point
     */
    public static double velocityCurveQuadratic(double percent) {
        return -Math.pow(2 * percent - 1, 2) + 1;
    }

    /**
     * Uses the equation y = -(2x-1)^4 + 1 to determine speed along a path
     * 
     * @param percent how far along the path the actuator is
     * @return speed at the given point
     */
    public static double velocityCurveQuartic(double percent) {
        return -Math.pow(2 * percent - 1, 4) + 1;
    }

    /**
     * Returns the average of a set of values
     * 
     * @param values infinite array of values
     * @return the average
     */
    public static double average(double... values) {
        double sum = 0d;
        for (double value : values) {
            sum += value;
        }
        return sum / (double) values.length;
    }

}