package vision;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;

public class ImageUtils {
	//Load an image
    public static BufferedImage loadImage(String file) {
        BufferedImage img;

        try {
            File input = new File(file);
            img = ImageIO.read(input);

            return img;
        } catch (Exception e) {
            System.out.println("erro");
        }

        return null;
    }

    //Save an image
    public static void saveImage(BufferedImage img) {        
        try {
            File outputfile = new File("Images/new.png");
            ImageIO.write(img, "png", outputfile);
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    //Grayscale filter
    public static void grayscale(BufferedImage img, BufferedImage output) {
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color c = new Color(img.getRGB(j, i));

                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);

                Color newColor =
                        new Color(
                        red + green + blue,
                        red + green + blue,
                        red + green + blue);

                output.setRGB(j, i, newColor.getRGB());
            }
        }

    }
    
    public static BufferedImage MakeBufferedImage(Mat frame) {
    	int type = 0;
	    if (frame.channels() == 1) {
	        type = BufferedImage.TYPE_BYTE_GRAY;
	    } else if (frame.channels() == 3) {
	        type = BufferedImage.TYPE_3BYTE_BGR;
	    }
	    BufferedImage image = new BufferedImage(frame.width(), frame.height(), type);
	    
	    return image;
    }
    
    public static BufferedImage MakeBufferedImage(Mat frame, int type) {
	    BufferedImage image = new BufferedImage(frame.width(), frame.height(), type);
	    
	    return image;
    }

    public static void MatToBufferedImage(Mat frame, BufferedImage image) {
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        frame.get(0, 0, data);
    }
}
