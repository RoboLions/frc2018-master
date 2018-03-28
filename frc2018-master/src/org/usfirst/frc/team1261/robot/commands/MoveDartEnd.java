package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1261.robot.OI;
import org.usfirst.frc.team1261.robot.Robot;

/**
*
*/

public class MoveDartEnd extends Command {
	
	public static final Joystick JOYSTICK = OI.getManipulatorJoystick();
	public static final int X_THROTTLE = OI.AXIS_RIGHT_STICK_Y;
	public static final double deadband = 0.2;
	public static final double increment = 0.5;
	public static double x_joy_axis = 0.5;
	public static double wanted_endpoint;

	public MoveDartEnd() {
		requires(Robot.actuator);
	}

    
	// Called just before this Command runs the first time
    

	protected void initialize() {
    
	}

    
	// Called repeatedly when this Command is scheduled to run
    
	
	protected void execute() {
		
		x_joy_axis = JOYSTICK.getRawAxis(X_THROTTLE);
    	
		double[] cur_end = Robot.actuator.getEndpoint(Robot.actuator.getBoomEncoderAngleinDegrees(), Robot.actuator.getVertEncoderAngleinDegrees());
    	
		double current_xe = cur_end[0];
    	
		if(x_joy_axis > -deadband && current_xe < deadband) {
			wanted_endpoint = current_xe;
		} else if (x_joy_axis > deadband) {
			wanted_endpoint = current_xe + increment;
		} else if (x_joy_axis < -deadband) {
			wanted_endpoint = current_xe - increment;
		}
    
	}

    
	// Make this return true when this Command no longer needs to run execute()
    
	
	protected boolean isFinished() { 
		return false;
	}

    
	// Called once after isFinished returns true
    

	protected void end() {
    
	}

    
	// Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    

	protected void interrupted() {
	
	}

}
