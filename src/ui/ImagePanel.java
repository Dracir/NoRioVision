package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;   

public class ImagePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public BufferedImage image;

    @Override
    public void paint(Graphics g) {
    	g.setColor(Color.GRAY);
    	g.fillRect(0, 0, this.getWidth(), this.getHeight());
    	
    	g.setColor(Color.BLACK);
    	g.drawRect(0, 0, this.getWidth(), this.getHeight());
    	
    	int imageWidth = 320*2;
    	int imageHeight = 240*2;
    	
    	int x = (getWidth() - imageWidth)/2; 
    	int y = (getHeight() - imageHeight)/2; 
        g.drawImage(image, x, y, imageWidth,imageHeight,this);
    }

    public ImagePanel() {
    	
    }

    public ImagePanel(BufferedImage img) {
        image = img;
    }   

    

}