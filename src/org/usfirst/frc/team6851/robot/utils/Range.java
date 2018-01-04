package org.usfirst.frc.team6851.robot.utils;

public class Range {

	public double min;
	public double max;
	
	public Range(double min, double max) {
		this.min = min;
		this.max = max;
	}
	
	public boolean isInside(double value) {
		return value > min && value < max;
	}
}
