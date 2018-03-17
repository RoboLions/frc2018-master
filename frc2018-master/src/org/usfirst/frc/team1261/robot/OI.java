/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1261.robot;

import org.usfirst.frc.team1261.robot.commands.Auto180Turn;
import org.usfirst.frc.team1261.robot.commands.AutoAcquire;
import org.usfirst.frc.team1261.robot.commands.AutoClockwiseTurn;
import org.usfirst.frc.team1261.robot.commands.AutoCounterclockwiseTurn;
import org.usfirst.frc.team1261.robot.commands.AutoPivotHead;
import org.usfirst.frc.team1261.robot.commands.Climb;
import org.usfirst.frc.team1261.robot.commands.ClawGrab;
import org.usfirst.frc.team1261.robot.commands.ClawRetract;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static final int DRIVER_JOYSTICK = 0;
	public static final int MANIPULATOR_JOYSTICK = 1;
	
	public static final int BUTTON_A = 1;
	public static final int BUTTON_B = 2;
	public static final int BUTTON_X = 3;
	public static final int BUTTON_Y = 4;
	public static final int BUTTON_LEFT_BUMPER = 5;
	public static final int BUTTON_RIGHT_BUMPER = 6;
	public static final int BUTTON_BACK = 7;
	public static final int BUTTON_START = 8;
	public static final int BUTTON_LEFT_STICK = 9;
	public static final int BUTTON_RIGHT_STICK = 10;

	public static final int AXIS_LEFT_STICK_X = 0;
	public static final int AXIS_LEFT_STICK_Y = 1;
	public static final int AXIS_LEFT_TRIGGER = 2;
	public static final int AXIS_RIGHT_TRIGGER = 3;
	public static final int AXIS_RIGHT_STICK_X = 4;
	public static final int AXIS_RIGHT_STICK_Y = 5;
	
	static Joystick driverJoystick = new Joystick(DRIVER_JOYSTICK);
	static Joystick manipulatorJoystick = new Joystick(MANIPULATOR_JOYSTICK);
	
	public final Button quickLeftTurn = new JoystickButton(driverJoystick, BUTTON_LEFT_BUMPER);
	public final Button quickRightTurn = new JoystickButton(driverJoystick, BUTTON_RIGHT_BUMPER);
	public final Button ccwButton = new JoystickButton(driverJoystick, BUTTON_X);
	public final Button cwButton = new JoystickButton(driverJoystick, BUTTON_B);
	public final Button climbButton = new JoystickButton(manipulatorJoystick, BUTTON_START);
	//public final Button ccwButton = new JoystickButton(driverJoystick, BUTTON_X);
	//public final Button cwButton = new JoystickButton(driverJoystick, BUTTON_B);
	public final Button reverseButton = new JoystickButton(driverJoystick, BUTTON_Y);
	//public final Button clearButton = new JoystickButton(manipulatorJoystick, BUTTON_Y);
	/*
	public final Button topDartReleaseButton = new JoystickButton(manipulatorJoystick, BUTTON_Y);
	public final Button topDartCompressButton = new JoystickButton(manipulatorJoystick, BUTTON_A);
	public final Button bottomDartReleaseButton = new JoystickButton(manipulatorJoystick, BUTTON_B);
	public final Button bottomDartCompressButton = new JoystickButton(manipulatorJoystick, BUTTON_X);
	*/
	public final Button clawGrabButton = new JoystickButton(manipulatorJoystick, BUTTON_X);
	public final Button acquireAlignButton = new JoystickButton(manipulatorJoystick, BUTTON_LEFT_BUMPER);
	public final Button holdingCubeButton = new JoystickButton(manipulatorJoystick, BUTTON_RIGHT_BUMPER);
	public final Button clawRetractButton = new JoystickButton(manipulatorJoystick, BUTTON_B);

	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public OI() {
		climbButton.whileHeld(new Climb());
		ccwButton.whenPressed(new AutoCounterclockwiseTurn());
		cwButton.whenPressed(new AutoClockwiseTurn());
		reverseButton.whenPressed(new Auto180Turn());
		clawGrabButton.whenPressed(new ClawGrab()); 
		clawRetractButton.whenPressed(new ClawRetract());
		acquireAlignButton.whenPressed(new AutoAcquire());
		quickLeftTurn.whenPressed(new AutoPivotHead(5,.2));
		quickRightTurn.whenPressed(new AutoPivotHead(-5,.2));
		//holdingCubeButton.whenPressed(new AutoCubeHandle());
	}
	
	public static Joystick getDriverJoystick() {
		return driverJoystick;
	}

	public static Joystick getManipulatorJoystick() {
		return manipulatorJoystick;
	}

}
