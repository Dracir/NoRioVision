package org.usfirst.frc.team6851.robot.commands.vision;

import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

public class VisionPipeline {
	
	VisionFilterConfiguration config;
	
	
	public VisionPipeline(VisionFilterConfiguration config) {
		this.config = config;
	}

	
	public void resizeImage(Mat input, Mat output) {
		Imgproc.resize(input, output, config.workingImageSize, 0.0, 0.0, Imgproc.INTER_CUBIC);
	}

	
	public void hsvThreshold(Mat input, Mat out) {		
		Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2HSV);
		Core.inRange(out, config.hsvMinThreshold, config.hsvMaxThreshold, out);
	}
	
	
	public void findContours(Mat input, List<MatOfPoint> outputContours) {
		Mat hierarchy = new Mat();
		outputContours.clear();
		int mode;
		if (config.externalContourOnly) {
			mode = Imgproc.RETR_EXTERNAL;
		} else {
			mode = Imgproc.RETR_LIST;
		}
		int method = Imgproc.CHAIN_APPROX_SIMPLE;
		Imgproc.findContours(input, outputContours, hierarchy, mode, method);
	}
	
	public void filterContours(List<MatOfPoint> inputContours, List<MatOfPoint> output) {
		final MatOfInt hull = new MatOfInt();
		output.clear();
		// operation
		for (int i = 0; i < inputContours.size(); i++) {
			final MatOfPoint contour = inputContours.get(i);
			final Rect bb = Imgproc.boundingRect(contour);
			
			if (!config.targetWidth.isInside(bb.width) || ! config.targetHeight.isInside(bb.height))
				continue;
			
			final double area = Imgproc.contourArea(contour);
			if(!config.targetArea.isInside(area))
				continue;
			final double perimeter = Imgproc.arcLength(new MatOfPoint2f(contour.toArray()), true);
			if (!config.targetPerimeter.isInside(perimeter))
				continue;
			
			Imgproc.convexHull(contour, hull);
			MatOfPoint mopHull = new MatOfPoint();
			mopHull.create((int) hull.size().height, 1, CvType.CV_32SC2);
			for (int j = 0; j < hull.size().height; j++) {
				int index = (int) hull.get(j, 0)[0];
				double[] point = new double[] { contour.get(index, 0)[0], contour.get(index, 0)[1] };
				mopHull.put(j, 0, point);
			}
			
			final double solid = 100 * area / Imgproc.contourArea(mopHull);
			if (!config.targetSolidity.isInside(solid))
				continue;
			if (!config.targetVertexCount.isInside(contour.rows()))
				continue;
			
			final double ratio = bb.width / (double) bb.height;
			if ( !config.targetRatio.isInside(ratio))
				continue;
			
			output.add(contour);
		}
	}
}