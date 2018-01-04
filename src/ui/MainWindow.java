package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import org.usfirst.frc.team6851.robot.commands.vision.VisionFilterConfiguration;
import org.usfirst.frc.team6851.robot.utils.Range;

import javafx.scene.control.Separator;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	
	
	/*public MinMaxPanel hueInput;
	public MinMaxPanel saturationInput;
	public MinMaxPanel valueInput;
	
	public MinMaxPanel targetWidth;
	public MinMaxPanel targetHeight;
	public MinMaxPanel targetPerimeter;
	public MinMaxPanel targetArea;
	public MinMaxPanel targetSolidity;
	public MinMaxPanel targetVertexCount;
	public MinMaxPanel targetRatio;*/
	
	public ImagePanel webcamImagePanel = new ImagePanel();
	public ImagePanel hsvImagePanel = new ImagePanel();
	public ImagePanel blurImagePanel = new ImagePanel();
	public ImagePanel Find_ContoursPanel = new ImagePanel();
	public ImagePanel Filter_ContoursPanel = new ImagePanel();
	
	
	public MainWindow(VisionFilterConfiguration config) {
		makeContentPanels(config);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("No Rio Vision");
        setSize(1920,1000);
        //setLocation(null);
        setVisible(true);
	}

	private void makeContentPanels(VisionFilterConfiguration config) {
		setLayout(new BorderLayout());
		
		add( makeImagesPanel(),BorderLayout.CENTER);
		add( makeButtonsPanel(config),BorderLayout.WEST);
		
	}
	
	private JPanel makeButtonsPanel(VisionFilterConfiguration config) {
		JPanel panel = new JPanel(new GridLayout(0,1));
		
		panel.add(new MinMaxPanel("Hue", config.hueRange, 0, 255,1));
		panel.add(new MinMaxPanel("Saturation", config.saturationRange, 0, 255,1));
		panel.add(new MinMaxPanel("Value", config.valueRange, 0, 255,1));

		panel.add(new JSeparator());
		
		panel.add(new MinMaxPanel("Blur", config.blur, 0, 100,1),true);
		
		panel.add(new JSeparator());
		
		panel.add(new MinMaxPanel("Width", config.targetWidth, 0, Integer.MAX_VALUE,1));
		panel.add(new MinMaxPanel("Height", config.targetHeight, 0, Integer.MAX_VALUE,1));
		panel.add(new MinMaxPanel("Perimeter", config.targetPerimeter, 0, Integer.MAX_VALUE,1));
		panel.add(new MinMaxPanel("Area", config.targetArea, 0, Integer.MAX_VALUE,1));
		panel.add(new MinMaxPanel("Solidity", config.targetSolidity, 0, Integer.MAX_VALUE,1));
		panel.add(new MinMaxPanel("Vertex Count", config.targetVertexCount, 0, Integer.MAX_VALUE,1));
		panel.add(new MinMaxPanel("Ratio", config.targetRatio, 0, 1,0.05));
		
		return panel;
	}

	private JPanel makeImagesPanel() {
		JPanel panel = new JPanel(new GridLayout(0,3));
		
		panel.add(webcamImagePanel);
		panel.add(hsvImagePanel);
		panel.add(blurImagePanel);
		panel.add(Find_ContoursPanel);
		panel.add(Filter_ContoursPanel);
		
		return panel;
	}
	
	public void refresh() {
		webcamImagePanel.repaint();
		hsvImagePanel.repaint();
		blurImagePanel.repaint();
		Find_ContoursPanel.repaint();
		Filter_ContoursPanel.repaint();
	}


}
