package ui;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import org.usfirst.frc.team6851.robot.utils.Range;

public class MinMaxPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	
	public JSpinner minSpinner;
	public JSpinner maxSpinner;
	
	public MinMaxPanel(String name, SpinnerModel minModel, SpinnerModel maxModel) {
		setBorder(BorderFactory.createTitledBorder("asdasdads"));
		add(new JLabel(name));
		
		minSpinner = new JSpinner(minModel);
		add(minSpinner);
		
		maxSpinner = new JSpinner(maxModel);
		add(maxSpinner);
	}

	public MinMaxPanel(String name, Range range, int minValue, int maxValue, double increment) {
		SpinnerNumberModel minModel = new SpinnerNumberModel(range.min, minValue, maxValue, increment);
		SpinnerNumberModel maxModel = new SpinnerNumberModel(range.max, minValue, maxValue, increment);
		
		
		add(new JLabel(name));
		
		minSpinner = new JSpinner(minModel);
		minSpinner.addChangeListener( (c) -> range.min = (double)minSpinner.getValue());
		add(minSpinner);
		
		maxSpinner = new JSpinner(maxModel);
		maxSpinner.addChangeListener( (c) -> range.max = (double)maxSpinner.getValue());
		add(maxSpinner);
		
	}

	public MinMaxPanel(String name, Range range, int minValue, int maxValue, double increment, boolean linkedValues) {
		SpinnerNumberModel minModel = new SpinnerNumberModel(range.min, minValue, maxValue, increment);
		SpinnerNumberModel maxModel = new SpinnerNumberModel(range.min, minValue, maxValue, increment);
		
		
		add(new JLabel(name));
		
		minSpinner = new JSpinner(minModel);
		minSpinner.addChangeListener( (c) -> {
			range.min = (double)minSpinner.getValue();
			if(range.max != range.min) {
				range.max = range.min;
				maxSpinner.setValue(range.min);
			}
		});
		add(minSpinner);
		
		maxSpinner = new JSpinner(maxModel);
		maxSpinner.addChangeListener( (c) -> {
				if(range.min != range.max) {
					range.min = range.max;
					minSpinner.setValue(range.min);
				}
			} );
		add(maxSpinner);
		
	}
}
