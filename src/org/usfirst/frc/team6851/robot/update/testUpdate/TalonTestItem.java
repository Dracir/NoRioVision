package org.usfirst.frc.team6851.robot.update.testUpdate;

import edu.wpi.first.wpilibj.Talon;

public class TalonTestItem extends TestItem{

	public Talon talon;
	double constantValue;
	
	public TalonTestItem(String name, Talon talon, double constantValue) {
		super(name);
		this.talon = talon;
		this.constantValue = constantValue;
		
	}
	
	@Override
	public void init() {
		talon.set(constantValue);
	}
	@Override
	public void periodic(double value1, double value2) {
		talon.set(value1);
	}

	@Override
	public void stop() {
		talon.set(0);
	}

}
