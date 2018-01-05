package org.usfirst.frc.team6851.robot.commands.vision;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;

public class PotentialTarget {

	public MatOfPoint mop;
	public double time;
	
	public PotentialTarget(MatOfPoint mop, Rect rect) {
		this.mop = mop;
		this.rect = rect;
	}
	
	public Rect rect;
	
	
	
}
