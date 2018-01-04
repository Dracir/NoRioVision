package org.usfirst.frc.team6851.robot.commands.vision;

import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.usfirst.frc.team6851.robot.utils.Range;

public class VisionFilterConfiguration {

	//The size to work on, the smaller the faster but also less precise
	public Size workingImageSize  = new Size(320,240);
	
	//HSV
	public Range hueRange = new Range(0,12);
	public Range saturationRange = new Range(80,255);
	public Range valueRange = new Range(50,255);
	
	//blur
	public Range blur = new Range(2, 2);
	
	
	public boolean externalContourOnly = true;
	
	//filter contours
	public Range targetWidth = new Range(60,1000);
	public Range targetHeight = new Range(10,1000);
	public Range targetPerimeter = new Range(0,1000);
	public Range targetArea = new Range(0,10000);
	public Range targetSolidity = new Range(0,10000);
	public Range targetVertexCount = new Range(4, 100000);
	public Range targetRatio = new Range(0, 1);
	
	
	public double[] hueThreshold;
	public double[] saturationThreshold;
	public double[] valueThreshold;
	
	
	public long msInterval; // 4 times per second
	
	public VisionFilterConfiguration setHSVThreshold(double[] hueThreshold, double[] saturationThreshold,
			double[] valueThreshold) {
		
		this.hueThreshold = hueThreshold;
		this.saturationThreshold = saturationThreshold;
		this.valueThreshold = valueThreshold;
		return this;
	}
	
	public VisionFilterConfiguration setMsInterval(long msInterval) {
		this.msInterval = msInterval;
		return this;
	}
	
	
}
