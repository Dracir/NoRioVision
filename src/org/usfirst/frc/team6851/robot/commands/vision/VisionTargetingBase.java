package org.usfirst.frc.team6851.robot.commands.vision;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;

public abstract class VisionTargetingBase {
	
	public abstract void handle(Mat material, ArrayList<MatOfPoint> matOfPoints);
	public abstract void drawTargetOn(Mat material);
	public abstract double distanceToTarget();
	
}
