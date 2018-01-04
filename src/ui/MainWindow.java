package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import org.usfirst.frc.team6851.robot.commands.vision.VisionFilterConfiguration;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	
	
	public MinMaxPanel hueInput;
	public MinMaxPanel saturationInput;
	public MinMaxPanel valueInput;
	
	public ImagePanel webcamImagePanel;
	public ImagePanel hsvImagePanel;
	public ImagePanel Find_ContoursPanel;
	public ImagePanel Filter_ContoursPanel;
	
	
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
		SpinnerModel minModel = new SpinnerNumberModel(config.hsvMinThreshold.val[0], 0, 255, 1);
		SpinnerModel maxModel = new SpinnerNumberModel(config.hsvMaxThreshold.val[0], 0, 255, 1);
		
		hueInput = new MinMaxPanel("Hue", minModel, maxModel);
		panel.add(hueInput);
		
		minModel = new SpinnerNumberModel(config.hsvMinThreshold.val[1], 0, 255, 1);
		maxModel = new SpinnerNumberModel(config.hsvMaxThreshold.val[1], 0, 255, 1);
		saturationInput = new MinMaxPanel("Saturation", minModel, maxModel);
		panel.add(saturationInput);

		minModel = new SpinnerNumberModel(config.hsvMinThreshold.val[2], 0, 255, 1);
		maxModel = new SpinnerNumberModel(config.hsvMaxThreshold.val[2], 0, 255, 1);
		valueInput = new MinMaxPanel("Value", minModel, maxModel);
		panel.add(valueInput);

		
		panel.add(new JButton("test"));
		panel.add(new JButton("test2"));
		panel.add(new JButton("test3"));
		
		return panel;
	}

	private JPanel makeImagesPanel() {
		JPanel panel = new JPanel(new GridLayout(0,2));
        
		webcamImagePanel = new ImagePanel();
		hsvImagePanel = new ImagePanel();
		Find_ContoursPanel = new ImagePanel();
		Filter_ContoursPanel = new ImagePanel();
		
		panel.add(webcamImagePanel);
		panel.add(hsvImagePanel);
		panel.add(Find_ContoursPanel);
		panel.add(Filter_ContoursPanel);
		
		return panel;
	}
	
	public void refresh() {
		webcamImagePanel.repaint();
		hsvImagePanel.repaint();
		Find_ContoursPanel.repaint();
		Filter_ContoursPanel.repaint();
	}


}
