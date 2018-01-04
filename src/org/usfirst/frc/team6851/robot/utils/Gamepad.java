package org.usfirst.frc.team6851.robot.utils;


import edu.wpi.first.wpilibj.DriverStation;

public class Gamepad {
	public enum GamepadButton {
		A(1),
		B(2),
		X(3),
		Y(4),
		LB(5),
		RB(6),
		Back(7),
		Start(8),
		LStick(9),
		RStick(10);
		
		private byte value;
		GamepadButton(int value) {
			this.value = (byte) value;
		}
		
		public byte value() {
			return this.value;
		}
	}
	
	public enum GamepadAxis {
		LeftX(0),
		LeftY(1),
		LeftTrigger(2),
		RightTrigger(3),
		RightX(4),
		RightY(5);
		
		private byte value;
		GamepadAxis(int value) {
			this.value = (byte) value;
		}
		
		public byte value() {
			return this.value;
		}
	}
	
	private int port;
	private DriverStation driverStation;
	
	Gamepad(int port) {
		this.port = port;
		driverStation = DriverStation.getInstance();
	}
	
	public double getAxis(GamepadAxis axis) {
		return driverStation.getStickAxis(port, axis.value());
	}

	public boolean getButton(GamepadButton button) {
		return driverStation.getStickButton(port,  button.value());
	}
}