package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath2Mirror extends CommandGroup {

    public AutoPath2Mirror(boolean execute) {
    	if(execute == true) {
    	System.out.println("***AUTO PATH 2 MIRROR***");
    	addSequential(new AutoMove(5.2, 0.0, 5)); //move forward
    	//addParallel(new AutoBoomDartMove(.5, 0, 5));
    	System.out.println("Initiating first turn");
    	addSequential(new AutoPivotHead(90, 3)); //first turn
    	System.out.println("Moving to position at back of switch");
    	addSequential(new AutoMove(1.6, 0, 3)); //move forward to postition parellel with switch deposit
    	addParallel(new AutoDartMove(95,90,3,100));
    	//addSequential(new AutoVertDartMove(95, 3));
    	//addParallel(new AutoBoomDartMove(90, 3));
    	System.out.println("Initiating Second Turn");
    	addSequential(new AutoPivotHead(90, 3)); //second turn
    	System.out.println("Initiate final approach");
    	addSequential(new AutoMove(0.25, 0, .75)); //go to switch
    	//addSequential(new AutoMove(.25, 0, .75)); //go to switch
    	addSequential(new ClawRetract());
    	}
    }
}
