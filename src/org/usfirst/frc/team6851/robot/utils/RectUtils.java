package org.usfirst.frc.team6851.robot.utils;

import org.opencv.core.Rect;

public class RectUtils {

	public static Rect mergeRect(Rect r1, Rect r2) {
		int x = Math.min(r1.x, r2.x);
		int y = Math.min(r1.y, r2.y);
		int width = Math.max(r1.x + r1.width, r2.x + r1.width) - x;
		int height = Math.max(r1.y + r1.height, r2.y + r1.height) - y;
		return new Rect(x, y, width, height);
	}
}
