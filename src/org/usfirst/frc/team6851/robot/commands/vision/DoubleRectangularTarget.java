package org.usfirst.frc.team6851.robot.commands.vision;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team6851.robot.utils.MaterialDrawUtils;
import org.usfirst.frc.team6851.robot.utils.MathUtils;
import org.usfirst.frc.team6851.robot.utils.RectUtils;


public class DoubleRectangularTarget extends VisionTargetingBase{

	
	private Rect target;
	private double distanceToTarget;
	
	@Override
	public void handle(Mat material, ArrayList<MatOfPoint> mops) {
		System.out.println(mops.size());
		for (int i = 0; i < mops.size() - 1; i++) {
			for (int j = i + 1; j < mops.size(); j++) {
				Rect recti = Imgproc.boundingRect(mops.get(i));
				Rect rectj = Imgproc.boundingRect(mops.get(j));
				if (MathUtils.equalEpsilon(recti.x, rectj.x, 15)
						&& MathUtils.equalEpsilon(recti.width, rectj.width, 15)) {

					Rect merged = RectUtils.mergeRect(recti, rectj);
					
					if (MathUtils.equalEpsilon(1.0 * merged.width / merged.height, 1, 0.4)) {
						target = merged;
						distanceToTarget = target.height  * 28.0 / 2 * 66;
					}
				}
			}
		}
	}

	@Override
	public void drawTargetOn(Mat material) {
		MaterialDrawUtils.draw(material, target, new Scalar(255, 255, 255), 3);
	}

	@Override
	public double distanceToTarget() {
		return distanceToTarget;
	}



}

