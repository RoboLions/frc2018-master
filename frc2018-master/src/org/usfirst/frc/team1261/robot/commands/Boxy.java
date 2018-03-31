package org.usfirst.frc.team1261.robot.commands;
import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Boxy extends CommandGroup {

    public Boxy() {
    	//Robot.actuator.resetBoomEncoder();
    	//addSequential(new AutoClockwiseTurn()); //first turn
    	//addSequential(new AutoPivotHead(90,4));
    	//addSequential(new AutoPivotHead(0.0, 5.0));
    	//addSequential(new AutoMove(0.5, 0.0, 10)); //move forward
    	//addSequential(new AutoPivotHead(90,2));
    	//addSequential(new AutoPivotHead(-90,2))0
    	addSequential(new AutoMove(3, 0, 3));
    	addParallel(new AutoVertDartMove(95, 3));
    	addParallel(new AutoBoomDartMove(90, 3));//move into switch scoring pos
    	addSequential(new ClawRetract());//drop
    	//addSequential(new AutoClockwiseTurn()); //first turn
    	//addSequential(new AutoPivotHead(-90,4));
    	//addSequential(new AutoPivotHead(90,4));

    	//addSequential(new AutoMove(10.0, 0.0, 12)); //move forward
    	//addSequential(new AutoMove(3, 0.0, 10)); //move forward
    	//addSequential(new AutoClockwiseTurn()); //first turn
    	//addSequential(new AutoPivotHead(180,4));
    	//addSequential(new AutoMove(3, 0.0, 10)); //move forward
    	//addSequential(new AutoClockwiseTurn()); //first turn
    	//addSequential(new AutoPivotHead(180,4));
    	//addSequential(new AutoMoveDartEnd(1.03, 0.55));
    	
    	
    	
    	//addSequential(new AutoMove(0.5, 0.0, 5)); //move forward
    	
    	
    	
    	
    	
    	
    	/*
    	addSequential(new AutoMove(2, 0.0, 2)); //move forward
    	System.out.println("Initiating first turn");
    	addSequential(new AutoClockwiseTurn()); //first turn
    	System.out.println("Moving to position at back of switch");
    	addSequential(new AutoMove(2, 0.0, 2)); //move forward
    	System.out.println("Initiating first turn");
    	addSequential(new AutoClockwiseTurn()); //first turn
    	System.out.println("Moving to position at back of switch");
    	addSequential(new AutoMove(2, 0.0, 2)); //move forward
    	System.out.println("Initiating first turn");
    	addSequential(new AutoClockwiseTurn()); //first turn
    	System.out.println("Moving to position at back of switch");
    	addSequential(new AutoMove(2, 0.0, 2)); //move forward
    	System.out.println("Initiating first turn");
    	addSequential(new AutoClockwiseTurn()); //first turn
    	System.out.println("Moving to position at back of switch");
    	addSequential(new AutoMove(2, 0.0, 2)); //move forward
    	System.out.println("Initiating first turn");
    	addSequential(new AutoClockwiseTurn()); //first turn
    	System.out.println("Moving to position at back of switch");
 	*/
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.actuator.boomMotor.setInverted(true);
    	//Robot.actuator.vertMotor.setInverted(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.actuator.boomMotor.setInverted(false);
    	//Robot.actuator.vertMotor.setInverted(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
