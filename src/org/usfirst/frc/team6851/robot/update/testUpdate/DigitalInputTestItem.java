package org.usfirst.frc.team6851.robot.update.testUpdate;

import edu.wpi.first.wpilibj.DigitalInput;

public class DigitalInputTestItem extends TestItem{

	public DigitalInput di;
	
	DigitalInputTestItem(String name, DigitalInput digitalInput) {
		super(name);
		this.di = digitalInput;
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
