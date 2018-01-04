package org.usfirst.frc.team6851.robot.utils.input;

import org.usfirst.frc.team6851.robot.utils.Gamepad.GamepadAxis;

import edu.wpi.first.wpilibj.Joystick;

public class DualInputInput extends AxisInputBase {

	AxisInputBase negatifInput;
	AxisInputBase positifInput;
	
	public DualInputInput(AxisInputBase negatifInput, AxisInputBase positifInput) {
		this.negatifInput = negatifInput;
		this.positifInput = positifInput;
	}
	
	public DualInputInput(Joystick joytick, GamepadAxis negatifAxis, GamepadAxis positifAxis, double deadzone) {
		negatifInput = new JoystickInput(joytick, negatifAxis, deadzone);
		positifInput = new JoystickInput(joytick, positifAxis, deadzone);
	}
	
	@Override
	public double getInput() {
		return positifInput.getInput() - negatifInput.getInput();
	}

}
