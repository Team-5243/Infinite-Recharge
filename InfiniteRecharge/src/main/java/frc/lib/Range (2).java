package frc.lib;

public class Range {
    public static double clip(double value, double min, double max) {
        return value < min ? min : value > max ? max : value;
    }
}