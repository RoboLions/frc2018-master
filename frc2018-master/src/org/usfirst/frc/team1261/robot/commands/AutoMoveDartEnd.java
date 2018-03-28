package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1261.robot.OI;
import org.usfirst.frc.team1261.robot.Robot;

/**
*
*/

public class AutoMoveDartEnd extends Command {
	
	private static double xe;
	private static double ye;
	
	public static double[] wanted_angles;
	public static final double deadband = 0.2;
	public static final double increment = 0.5;
	public static double wanted_endpoint;

	public AutoMoveDartEnd(double xe, double ye) {
		this.xe = xe;
		this.ye = ye;
		requires(Robot.actuator);
	}

    
	// Called just before this Command runs the first time
    

	protected void initialize() {
    
	}

    
	// Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
		wanted_angles = Robot.actuator.getAngles(xe, ye);
		double boom_angle = wanted_angles[0];
		double vert_angle = wanted_angles[1];
		
		double wanted_boom_enc = Robot.actuator.getBoomEncoderGivenAngle(boom_angle);
		double wanted_vert_enc = Robot.actuator.getVertEncoderGivenAngle(vert_angle);
		
		Robot.actuator.setBoomPosition(wanted_boom_enc);
		Robot.actuator.setVertPosition(wanted_vert_enc);
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
