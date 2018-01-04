package org.usfirst.frc.team6851.robot.update.testUpdate;

import edu.wpi.first.wpilibj.Servo;

public class ServoTestItem extends TestItem{

	private Servo servo;
	double constantValue;
	
	ServoTestItem(String name, Servo servo, double constantValue) {
		super(name);
		this.servo = servo;
	}

	@Override
	public void init() {
		servo.set(constantValue);
	}

	@Override
	public void periodic(double value1, double value2) {
		servo.set(value1);
	}

	@Override
	public void stop() {
		servo.setDisabled();
	}

}
