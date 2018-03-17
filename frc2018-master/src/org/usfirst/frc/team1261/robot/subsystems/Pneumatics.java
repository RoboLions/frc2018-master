package org.usfirst.frc.team1261.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1261.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
//import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import org.usfirst.frc.team1261.robot.RobotMap; 

/**
 * 
 */

public class Pneumatics extends Subsystem {
	
	// All methods operate both pistons simultaneously
	
	//AnalogInput transducer = new AnalogInput(0);

	
	//AnalogInput transducer = new AnalogInput(0);
	
	DoubleSolenoid piston1 = RobotMap.piston1;
	DoubleSolenoid piston2 = RobotMap.piston2;
	
	//exampleDouble.set(DoubleSolenoid.Value.kOff);
	//exampleDouble.set(DoubleSolenoid.Value.kForward);
	//exampleDouble.set(DoubleSolenoid.Value.kReverse);
		
	private static final double minimum_psi = 20.0; // The pressure isn't allowed to go below this level
	private static final double maximum_psi = 40.0; // The pressure isn't allowed to go above this level
	
	public double getPressure() {
		return (0.0);
		//return (0.0); //transducer.getVoltage() * 30.0; // Pressure = Voltage * 30
	}
	
    public void initDefaultCommand() {
    	for(int x = 0; x>100; x++) {
    		System.out.println("bang,bang,bang,bang,bang,bang,bang,bang,bang,bang,bang");
    	}
    }
    
	public void pistonOut() {
		RobotMap.piston1.set(DoubleSolenoid.Value.kForward);
		RobotMap.piston2.set(DoubleSolenoid.Value.kForward);
		System.out.println("Pistons Out!");
	}
    
	public void pistonIn() {
		RobotMap.piston1.set(DoubleSolenoid.Value.kReverse);
		RobotMap.piston2.set(DoubleSolenoid.Value.kReverse);
		//System.out.println("Pistons In!");
	}
    
	public void pistonOff() {
		RobotMap.piston1.set(DoubleSolenoid.Value.kOff);
		RobotMap.piston2.set(DoubleSolenoid.Value.kOff);
		
	}
	
	
	public boolean checkPressure() {
		boolean islowenough = getPressure() <= maximum_psi ;
		boolean ishighenough = getPressure() >= minimum_psi ;
		return(islowenough && ishighenough);
	}
}

