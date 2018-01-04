package org.usfirst.frc.team6851.robot.update.testUpdate;

public abstract class TestItem {

	public String	name;

	TestItem(String name) {
		this.name = name;
	}
	
	public abstract void init();
	public abstract void periodic(double value1, double value2);
	public abstract void stop();

}
