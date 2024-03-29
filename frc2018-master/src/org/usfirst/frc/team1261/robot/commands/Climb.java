package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1261.robot.Robot;

/**
 *
 */
public class Climb extends Command {
	
	public static final double POWER = 1.0;

    public Climb() {
       requires(Robot.climber); // Subsystem Dependency
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.climber.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.climber.setClimbPower(POWER);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.climber.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
