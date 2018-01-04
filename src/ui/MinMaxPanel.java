package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;

public class MinMaxPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	
	public JSpinner minSpinner;
	public JSpinner maxSpinner;
	
	public MinMaxPanel(String name, SpinnerModel minModel, SpinnerModel maxModel) {

		add(new JLabel(name));
		
		minSpinner = new JSpinner(minModel);
		add(minSpinner);
		
		maxSpinner = new JSpinner(maxModel);
		add(maxSpinner);
	}
}
