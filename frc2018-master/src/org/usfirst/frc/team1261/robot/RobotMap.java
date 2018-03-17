/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1261.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	
	
	public static final int LEFT_FRONT_DRIVE_PORT = 12;
	public static final int LEFT_BACK_DRIVE_PORT = 3;
	public static final int RIGHT_FRONT_DRIVE_PORT = 15;
	public static final int RIGHT_BACK_DRIVE_PORT = 0;

	
	/*
	public static final int LEFT_FRONT_DRIVE_PORT = 42;
	public static final int LEFT_BACK_DRIVE_PORT = 43;
	public static final int RIGHT_FRONT_DRIVE_PORT = 40;
	public static final int RIGHT_BACK_DRIVE_PORT = 41;
	
	/*
	public static final int LEFT_FRONT_DRIVE_PORT = 15;
	public static final int LEFT_BACK_DRIVE_PORT = 0;
	public static final int RIGHT_FRONT_DRIVE_PORT = 12;
	public static final int RIGHT_BACK_DRIVE_PORT = 3;
	*/

	public static final int CLIMBER_PORT = 46; // TODO Not sure on this one, double check it
	//public static final int RACK_PINION_PORT = 45; // TODO I REALLY DON'T KNOW IF THIS PORT IS RIGHT DOUBLE CHECK

	public static final int VERT_DART_ACTUATOR_PORT = 13;
	public static final int BOOM_DART_ACTUATOR_PORT = 2;
	public static final int CLAW_SOLENOID_LEFT = 0;
	public static final int CLAW_SOLENOID_RIGHT = 1;


	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static WPI_TalonSRX leftDriveMotorFront = new WPI_TalonSRX(LEFT_FRONT_DRIVE_PORT);
	public static WPI_TalonSRX leftDriveMotorBack = new WPI_TalonSRX(LEFT_BACK_DRIVE_PORT);
	public static WPI_TalonSRX rightDriveMotorFront = new WPI_TalonSRX(RIGHT_FRONT_DRIVE_PORT);
	public static WPI_TalonSRX rightDriveMotorBack = new WPI_TalonSRX(RIGHT_BACK_DRIVE_PORT);
	public static WPI_TalonSRX climbMotor = new WPI_TalonSRX(CLIMBER_PORT);
	//public static WPI_TalonSRX rackpinionMotor = new WPI_TalonSRX(RACK_PINION_PORT);
	public static WPI_TalonSRX vertDartMotor = new WPI_TalonSRX(VERT_DART_ACTUATOR_PORT);
	public static WPI_TalonSRX boomDartMotor = new WPI_TalonSRX(BOOM_DART_ACTUATOR_PORT);
	
	//these are for quadture encoders that are directly connected to the roborio and not for the talon srx????????
	public static Encoder leftDriveEncoder = new Encoder(15, 14);
	public static Encoder rightDriveEncoder = new Encoder(0, 1);
		
	public static AnalogPotentiometer toppot = new AnalogPotentiometer(0, 6, 0); // TODO make these actually reference the port of the Potentiometer
	public static AnalogPotentiometer botpot = new AnalogPotentiometer(1, 6, 0);
/*
	 public static DoubleSolenoid piston1 = new DoubleSolenoid(10,3,4);
	 public static DoubleSolenoid piston2 = new DoubleSolenoid(10,1,2);
	*/

	 public static DoubleSolenoid piston1 = new DoubleSolenoid(10,2,3);// 0 4 5 // 0 0 1
	 public static DoubleSolenoid piston2 = new DoubleSolenoid(10,0,1);// 0 2 1 // 1 1 0
	
	/*
	 * Parameters:
	 * moduleNumber The module number of the solenoid module to use.
	 * forwardChannel The forward channel on the module to control (0..7).
	 * reverseChannel The reverse channel on the module to control (0..7).
	 */

	/*
	 * AnalogPotentiometer class Parameters:
	 * 		channel: The analog channel this potentiometer is plugged into.
	 * 		fullRange: The scaling to multiply the fraction by to get a meaningful unit.
	 * 		offset: The offset to add to the scaled value for controlling the zero value
	 */
	
	//public static SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftDriveMotorFront, leftDriveMotorBack);
	//public static SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightDriveMotorFront, rightDriveMotorBack);
	//    2/10/2018   not sure what it does M.F
	public static DifferentialDrive robotDrive = new DifferentialDrive(leftDriveMotorFront, rightDriveMotorFront);
}
