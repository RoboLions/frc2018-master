package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1261.robot.OI;
import org.usfirst.frc.team1261.robot.Robot;

/**
 *
 */
public class AutoDartMove extends Command {
	
	//public static final Joystick JOYSTICK = OI.getManipulatorJoystick();
	//public static final int BOOM_THROTTLE = OI.AXIS_RIGHT_STICK_Y;
	//public static final int VERT_THROTTLE = OI.AXIS_LEFT_STICK_Y;
	
	public static double boomPos;
	public static double vertPos;

    public AutoDartMove(double vcom, double bcom, double to) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.actuator);
    	boomPos = bcom;
    	vertPos = vcom;
    	setTimeout(to);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.actuator.stopAll();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	if(Robot.actuator.isBottomFullyExtended()) {
    		System.out.println("Bottom Dart is already Fully Extended!");
    	} else {
    	*/
    	//boomPower = JOYSTICK.getRawAxis(BOOM_THROTTLE);
    	//Robot.actuator.setBoomPower(boomPower);
    	//vertPower = JOYSTICK.getRawAxis(VERT_THROTTLE);
    	//Robot.actuator.setVertPower(vertPower);
    		//boomPower = JOYSTICK.getRawAxis(BOOM_THROTTLE);
        	
        	//vertPower = JOYSTICK.getRawAxis(VERT_THROTTLE);
        	//Robot.actuator.setVertPower(vertPower);
        	//Robot.actuator.setBoomPower(boomPower);
    	Robot.actuator.setBoomPosition(boomPos);
    	Robot.actuator.setVertPosition(vertPos);
    	
    	}
    //}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.actuator.stopAll();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}