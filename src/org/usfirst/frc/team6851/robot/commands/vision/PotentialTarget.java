package org.usfirst.frc.team6851.robot.commands.vision;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;

public class PotentialTarget {

	public MatOfPoint mop;
	public Rect rect;
	public double time;
	
	public PotentialTarget(MatOfPoint mop, Rect rect) {
		this.mop = mop;
		this.rect = rect;
	}

	public boolean similarTo(PotentialTarget other) {
		Rect rectB = other.rect;

		return (similar(rect.x,rectB.x)
				&& similar(rect.y,rectB.y)
				&& similar(rect.width,rectB.width)
				&& similar(rect.height,rectB.height));
	}
	
	boolean similar(double a, double b) {
		return Math.abs(b-a) < 3;
	}

	public void replaceBy(PotentialTarget newTarget) {
		this.mop = newTarget.mop;
		this.rect = newTarget.rect;
		this.time = newTarget.time;
	}
	
	
	
}
