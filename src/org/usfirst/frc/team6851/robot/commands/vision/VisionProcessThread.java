package org.usfirst.frc.team6851.robot.commands.vision;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.usfirst.frc.team6851.robot.commands.vision.GripPipeline.OutputSteamShownStep;
import org.usfirst.frc.team6851.robot.utils.MaterialDrawUtils;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class VisionProcessThread extends Thread {


	GripPipeline	gripPipeline;
	UsbCamera camera;
	CvSource outputStream;
	
	VisionFilterConfiguration 	config;
	VisionTargetingBase			visionTargetStrategy;
	
	
	Mat				mat;
	long			nextProcess;
	CvSink			cvSink;
	
	public OutputSteamShownStep 	shownOutput;
	public boolean	showSimpleRects	= true;
	public boolean	showTarget		= true;

	public Rect		target;
	public double	targetDistance;

	public VisionProcessThread(VisionFilterConfiguration config, UsbCamera camera, CvSource outputStream) {
		this.config = config;
		this.gripPipeline = new GripPipeline(config);
		this.camera = camera;
		this.outputStream = outputStream;
		
		mat = new Mat();
		nextProcess = 0;
		
		// Setup a CvSource. This will send images back to the Dashboard
		cvSink = CameraServer.getInstance().getVideo(camera);
	}

	@Override
	public void run() {
		// Get a CvSink. This will capture Mats from the camera
		cvSink = CameraServer.getInstance().getVideo(camera);
		
		while (!Thread.interrupted() || false) {

			if (System.currentTimeMillis() > nextProcess) {
				nextProcess = System.currentTimeMillis() + config.msInterval;

				if (cvSink.grabFrame(mat) == 0) {
					outputStream.notifyError(cvSink.getError());
					continue;
				}

				processImage();
				processTargeting();

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {}
			}

		}
	}

	private void processImage() {
		gripPipeline.process(mat,shownOutput);

		if (showSimpleRects) {
			int i = 0;
			for (MatOfPoint mop : gripPipeline.filterContoursOutput()) {
				int colorIndex = 255 / gripPipeline.filterContoursOutput().size();
				MaterialDrawUtils.draw(gripPipeline.alternativeOutput, mop, new Scalar(255 - i * colorIndex, 0, i * colorIndex), 2);
				i++;
			}
		}
		outputStream.putFrame(gripPipeline.alternativeOutput);

	}

	private void processTargeting() {
		target = null;
		targetDistance = 0;
		if(visionTargetStrategy != null) {
			ArrayList<MatOfPoint> mops = gripPipeline.filterContoursOutput();
			visionTargetStrategy.handle(gripPipeline.alternativeOutput, mops);
		}
		
		if(showTarget)
			visionTargetStrategy.drawTargetOn(gripPipeline.alternativeOutput);
		// Give the output stream a new image to display
		outputStream.putFrame(mat);
	}
}
