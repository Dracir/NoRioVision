package org.usfirst.frc.team6851.robot.utils;

public class MathUtils {
	
	public static double clamp(double value, double min, double max) {
		if (value > max) {
			return max;
		} else if (value < min) {
			return min;
		} else {
			return value;
		}
	}
	
	public static double scale(double value, double min, double max, double newMin, double newMax) {
		return ((value-min) / (max-min)  * (newMax-newMin)) + newMin;
	}
	
	public static boolean equalEpsilon(int a, int b, int epsilon) {
		return Math.abs(a - b) < epsilon;
	}

	public static boolean equalEpsilon(double a, double b, double epsilon) {
		return Math.abs(a - b) < epsilon;
	}

	public static String formatInch(double inchs) {
		return String.format("%d' %d\"", (int) (inchs / 12), (int) (inchs % 12));
	}
	
}
