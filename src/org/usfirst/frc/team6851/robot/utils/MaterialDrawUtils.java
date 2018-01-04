package org.usfirst.frc.team6851.robot.utils;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class MaterialDrawUtils {

	public static void draw(Mat mat, Rect r1, Rect r2, Scalar color, int size) {
		draw(mat, RectUtils.mergeRect(r1, r2), color, size);
	}
	
	public static void draw(Mat mat, Rect r, Scalar color, int size) {
		int x = r.x * 2;
		int y = r.y * 2;
		int width = r.width * 2;
		int height = r.height * 2;
		Point bottomLeft = new Point(x, y);
		Point topRight = new Point(x + width, y + height);
		Imgproc.rectangle(mat, bottomLeft, topRight, color, size);
	}

	public static void draw(Mat mat, MatOfPoint mop, Scalar color, int size) {
		Rect r = Imgproc.boundingRect(mop);
		int x = r.x * 2;
		int y = r.y * 2;
		int width = r.width * 2;
		int height = r.height * 2;
		Point bottomLeft = new Point(x, y);
		Point topRight = new Point(x + width, y + height);
		Imgproc.rectangle(mat, bottomLeft, topRight, color, size);

	}
}
