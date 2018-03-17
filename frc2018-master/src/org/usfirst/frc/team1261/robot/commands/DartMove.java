package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1261.robot.OI;
import org.usfirst.frc.team1261.robot.Robot;

/**
 *
 */

public class DartMove extends Command {
	
	public static final Joystick JOYSTICK = OI.getManipulatorJoystick();
	public static final int BOOM_THROTTLE = OI.AXIS_RIGHT_STICK_Y;
	public static final int VERT_THROTTLE = OI.AXIS_LEFT_STICK_Y;
	
	public static double boomPower = 0.5;
	public static double vertPower = 0.5;
	public static double boomPos = boomPower * 200;
	public static double vertPos = vertPower * 200;
	public static double xe = 0;
	public static double ye = 0;
	public static double theta_bar = 0;
	public static double phi_bar = 0;

    public DartMove() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.actuator);
    }
    /*
    public DartMove(double xe, double ye, double theta_bar, double phi_bar) {
    	requires(Robot.actuator);
    	this.xe = xe;
    	this.ye = ye;
    	this.theta_bar = theta_bar;
    	this.phi_bar = phi_bar;
    }
    */

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.actuator.stopAll();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    		vertPower = JOYSTICK.getRawAxis(VERT_THROTTLE); // li
    		boomPower = -JOYSTICK.getRawAxis(BOOM_THROTTLE);
    		//boolean help_me = JOYSTICK.getRawButton(OI.BUTTON_Y);
    		/*
    		if (help_me == true) {
    			Robot.actuator.setBoomPower(boomPower);
        		Robot.actuator.setVertPower(vertPower);
        		Robot.actuator.resetEncoders();
        		
    		}
    		else {
    		if (boomPower < -0.5) {
    			boomPos = -200;
    		}
    		else if (boomPower > 0.5) {
    			boomPos = 200;
    		}
    		*/
    		/*
    		if (Math.abs(boomPower) < .2) {
        		boomPower = 0;
        	}
        	*/
        	//vertPower = -JOYSTICK.getRawAxis(VERT_THROTTLE);
    		//vertPos = -JOYSTICK.getRawAxis(VERT_THROTTLE)*500; 
    		
        	/*
        	if (xe != 0 || ye != 0 || theta_bar != 0 || phi_bar != 0)	{
        		Robot.actuator.PIDTuneDart(xe, ye, theta_bar, phi_bar);
        	}
        	*/	
    		
    		if (Math.abs(vertPower) < .2) {
        		vertPower = 0;
        	}
        	if (Math.abs(boomPower) < .2) {
        		boomPower = 0;
        	}
    		
        	boolean violating = Robot.actuator.status;
        	//Robot.actuator.isViolating();
        	
        	double x_distance = Robot.actuator.xe;
        	
        	if(x_distance > 1.0) {
        		vertPower *= 0.5;
        		boomPower *= 0.5;
        	}
        	
        	if (violating) {
        		double angle_compare = 180.0-Robot.actuator.getVertEncoderAngleinDegrees();
        		double boom_angle = Robot.actuator.getBoomEncoderAngleinDegrees();
        		if (boom_angle > angle_compare) {
        			System.out.println("my names jeff");
        			//Robot.actuator.setBoomPower(Math.abs(boomPower));
        			if (boomPower < 0) {
        				boomPower = 0;
        			}
        		} else {
        			if(boomPower > 0) {
        				boomPower = 0;
        			}
        		} 
        		
        		if(vertPower < 0) {
        			vertPower = 0;
        		}
        	}
        	
        	Robot.actuator.setBoomPower(boomPower);
        	Robot.actuator.setVertPower(vertPower);
        	
        	//Robot.actuator.setBoomSpeed(1000 * boomPower);
        	//Robot.actuator.setVertSpeed(1000 * vertPower);
        	
        	/*
        	if(violating && boomPower > 0) {
        		System.out.println("You've extended farther than 16 inches!");
        		Robot.actuator.setBoomPower(0);
        	} else {
        		Robot.actuator.setBoomPower(boomPower);
        	}
        	
        	if(violating && vertPower < 0) {
        		System.out.println("You've extended farther than 16 inches!");
        		Robot.actuator.setVertPower(0);
        	} else {
        		Robot.actuator.setVertPower(vertPower);
        	}
        		*/ 
        		
            	//System.out.println(boomPos);
            	//System.out.println(vertPos);
       }
        	//Robot.actuator.setBoomPower(boomPower);
        	//Robot.actuator.setVertPower(vertPower);

        	//System.out.println(boomPos);
        	//System.out.println(vertPos);
        	//Robot.actuator.setBoomPosition(boomPos);
        	//Robot.actuator.setVertPosition(vertPos);

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.actuator.stopAll();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}