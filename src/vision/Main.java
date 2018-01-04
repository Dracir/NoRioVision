package vision;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.usfirst.frc.team6851.robot.commands.vision.VisionFilterConfiguration;
import org.usfirst.frc.team6851.robot.commands.vision.VisionPipeline;

import ui.MainWindow;

public class Main {

	private static VisionFilterConfiguration config = new VisionFilterConfiguration();
	private static MainWindow mainWindow;
	
	private static Mat webcamFrame;
	private static Mat resizedFrame;
	private static Mat hsvFrame;
	private static Mat contourFrame;
	private static Mat filteredContoursFrame;
	
	private static ArrayList<MatOfPoint> contours = new ArrayList<>();
	private static ArrayList<MatOfPoint> contoursFiltered = new ArrayList<>();
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		SetupMainWindow();
		
		webcamFrame = new Mat();
		resizedFrame = new Mat();
		hsvFrame = new Mat();
		contourFrame = new Mat();
		filteredContoursFrame = new Mat();
		
        int width = (int)config.workingImageSize.width;
        int height = (int)config.workingImageSize.height;
        
        mainWindow.webcamImagePanel.image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        mainWindow.hsvImagePanel.image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        mainWindow.Find_ContoursPanel.image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        mainWindow.Filter_ContoursPanel.image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        VideoCapture camera = new VideoCapture(0);
        camera.read(webcamFrame);
        VisionPipeline pipeline = new VisionPipeline(config);

        if(!camera.isOpened()){
            System.out.println("Error");
        }
        else {     
        	while(mainWindow.isShowing()) {
        		tick(camera, pipeline);
        		sleep(10);
        	}
        }
        camera.release();
	}

	private static void tick(VideoCapture camera, VisionPipeline pipeline) {
		camera.read(webcamFrame);

		
		pipeline.resizeImage(webcamFrame, resizedFrame);
		ImageUtils.MatToBufferedImage(resizedFrame, mainWindow.webcamImagePanel.image);
		
		pipeline.hsvThreshold(resizedFrame, hsvFrame);
	    Imgproc.GaussianBlur(hsvFrame, hsvFrame, new Size(3, 3), 2);
	    //Imgproc.cvtColor(hsvFrame, hsvFrame, Imgproc.COLOR_GRAY2RGB);
	    Imgproc.boxFilter(hsvFrame, hsvFrame, -1, new Size(10, 10));
	    //Imgproc.cvtColor(hsvFrame, hsvFrame, Imgproc.COLOR_RGB2GRAY);
		ImageUtils.MatToBufferedImage(hsvFrame, mainWindow.hsvImagePanel.image);

	    
		pipeline.findContours(hsvFrame, contours);
		resizedFrame.assignTo(contourFrame);
	    Imgproc.cvtColor(contourFrame, contourFrame, Imgproc.COLOR_RGB2GRAY);
	    Imgproc.cvtColor(contourFrame, contourFrame, Imgproc.COLOR_GRAY2RGB);
		Imgproc.drawContours(contourFrame, contours, -1, new Scalar(255, 0, 0, 0));
		ImageUtils.MatToBufferedImage(contourFrame, mainWindow.Find_ContoursPanel.image);
		

		pipeline.filterContours(contours, contoursFiltered);
		
		resizedFrame.assignTo(filteredContoursFrame);
	    Imgproc.cvtColor(filteredContoursFrame, filteredContoursFrame, Imgproc.COLOR_RGB2GRAY);
	    Imgproc.cvtColor(filteredContoursFrame, filteredContoursFrame, Imgproc.COLOR_GRAY2RGB);
		Imgproc.drawContours(filteredContoursFrame, contoursFiltered, -1, new Scalar(255, 0, 0, 0));
		ImageUtils.MatToBufferedImage(filteredContoursFrame, mainWindow.Filter_ContoursPanel.image);
		
		mainWindow.refresh();
	}

	private static void SetupMainWindow() {
		mainWindow = new MainWindow(config);
		mainWindow.hueInput.minSpinner.addChangeListener( (c) -> config.hsvMinThreshold.val[0] = (double)mainWindow.hueInput.minSpinner.getValue());
		mainWindow.hueInput.maxSpinner.addChangeListener( (c) -> config.hsvMaxThreshold.val[0] = (double)mainWindow.hueInput.maxSpinner.getValue());

		mainWindow.saturationInput.minSpinner.addChangeListener( (c) -> config.hsvMinThreshold.val[1] = (double)mainWindow.saturationInput.minSpinner.getValue());
		mainWindow.saturationInput.maxSpinner.addChangeListener( (c) -> config.hsvMaxThreshold.val[1] = (double)mainWindow.saturationInput.maxSpinner.getValue());
		
		mainWindow.valueInput.minSpinner.addChangeListener( (c) -> config.hsvMinThreshold.val[2] = (double)mainWindow.valueInput.minSpinner.getValue());
		mainWindow.valueInput.maxSpinner.addChangeListener( (c) -> config.hsvMaxThreshold.val[2] = (double)mainWindow.valueInput.maxSpinner.getValue());
	}


	private static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}