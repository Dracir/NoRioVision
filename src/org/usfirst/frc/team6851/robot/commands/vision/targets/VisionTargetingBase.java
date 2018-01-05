package org.usfirst.frc.team6851.robot.commands.vision.targets;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.usfirst.frc.team6851.robot.commands.vision.PotentialTarget;

public abstract class VisionTargetingBase {
	
	public abstract void handle(Mat mat, ArrayList<MatOfPoint> contours, ArrayList<PotentialTarget> potentialTargets);
	public abstract void drawTargetOn(Mat mat, PotentialTarget target, Scalar color);
	public abstract double distanceToTarget();
	
}
