/*package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1261.robot.Robot;
import org.usfirst.frc.team1261.robot.commands.AutoDartMove;
import org.usfirst.frc.team1261.robot.commands.ClawGrab;
import org.usfirst.frc.team1261.robot.commands.ClawRetract;

 
public class AcquireCube extends Command {
	
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

    private boolean fin;
    public double to = 3; // a global timeout

	public AcquireCube() {
        requires(Robot.actuator);
        requires(Robot.manipulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
        timer.reset();
        timer.start();
    	boolean fin = false;
    	this.fin = fin;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
   	 // new AutoDartMove(v,b,to);
   	 // vert power, boom power, timeout
    	
    	new ClawRetract(); // Claw retract
    	new AutoDartMove(0,2,to); // Move boom up
    	new AutoDartMove(2,0,to); // Move vert Forward
    	new AutoDartMove(0,2,to); // Move Boom Up
    	new AutoDartMove(2,0,to); // Move vert Forward
    	new AutoDartMove(0,-2,to); // Move boom down
    	new ClawGrab(); // Claw Grab
    	new AutoDartMove(0,2,to); // Boom up
    	new AutoDartMove(0,-2,to); // Vert Backward
    	
    	this.fin = timer.get() >= TIMER_MAX;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.fin;
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    }
    
    protected void interrupted() {
    	end();
    }
}
*/