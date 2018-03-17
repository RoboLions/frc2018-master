package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team1261.robot.Robot;

/**
 *
 */

public class ClawGrab extends Command {
	
	Timer timer = new Timer();
	
	static double TIMER_MAX = 3.0;
	
	static {
    	switch (Robot.getRobotId()) {
    	case 1:
    		TIMER_MAX = 0.2;
    		break;
    	case 2:
    	default:
    		TIMER_MAX = 0.2;
    		break;
    	}
	}

    public ClawGrab() {
        requires(Robot.manipulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.manipulator.pistonOff();    	
        timer.reset();
        timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.manipulator.pistonIn();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (timer.get() >= TIMER_MAX);
    }

    protected void end() {
    	Robot.manipulator.pistonOff();
    	timer.stop();
    }

    protected void interrupted() {
    	end();
    }
}

