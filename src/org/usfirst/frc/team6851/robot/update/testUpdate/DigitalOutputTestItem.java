package org.usfirst.frc.team6851.robot.update.testUpdate;

import edu.wpi.first.wpilibj.DigitalOutput;

public class DigitalOutputTestItem  extends TestItem{

	public DigitalOutput dout;
	
	DigitalOutputTestItem (String name, DigitalOutput digitalOutput) {
		super(name);
		this.dout = digitalOutput;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void periodic(double value1, double value2) {
		
	}

	@Override
	public void stop() {
	}

}
