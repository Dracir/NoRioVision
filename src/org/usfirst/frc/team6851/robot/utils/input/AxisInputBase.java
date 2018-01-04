package org.usfirst.frc.team6851.robot.utils.input;

public abstract class AxisInputBase {

	public abstract double getInput();

	/*
	 * If the magnitude of the value is less than
     * this value, it will be considered a 0.
	 * */
	
	protected static double adjustForDeadZone(double value, double threshold){
		if (Math.abs(value) > threshold) 
			return (value - Math.signum(value) * threshold) / ( 1.0 - threshold);
		else
			return 0;
	}
}
