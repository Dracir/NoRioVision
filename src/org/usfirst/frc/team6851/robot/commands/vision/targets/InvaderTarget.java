package org.usfirst.frc.team6851.robot.commands.vision.targets;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team6851.robot.commands.vision.PotentialTarget;

public class InvaderTarget extends VisionTargetingBase{
	
	@Override
	public void handle(Mat mat, ArrayList<MatOfPoint> contours, ArrayList<PotentialTarget> potentialTargets) {
		// TODO Auto-generated method stub
		
		for (MatOfPoint contour : contours) {
			final double area = Imgproc.contourArea(contour);
			Rect rect = Imgproc.boundingRect(contour);
			final double fillRatio = area / rect.area();
			
			double fillRatioEpsilon = 0.07;
			double InvaderFillRatio = (46+3.5)/(8*11);
			
			if(Math.abs(fillRatio - InvaderFillRatio)< fillRatioEpsilon && rect.width > rect.height ) {
				potentialTargets.add(new PotentialTarget(contour, rect));
			}
		}
	}

	@Override
	public void drawTargetOn(Mat mat, PotentialTarget target, Scalar color) {
		Imgproc.rectangle(mat, target.rect.tl(), target.rect.br(), color);
	}

	@Override
	public double distanceToTarget() {

		return 0;
	}

}
