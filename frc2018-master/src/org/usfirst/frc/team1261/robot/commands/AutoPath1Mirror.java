package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath1Mirror extends CommandGroup {

    public AutoPath1Mirror(boolean execute) {
    	if(execute == true) {
    	System.out.println("***AUTO PATH 1 MIRROR***");
    	addSequential(new AutoMove(3.81, 0.0, 4, 1.0)); //move forward
    	addParallel(new AutoDartMove(95,90,3,100));
    	System.out.println("Now initiating turn");
    	addSequential(new AutoPivotHead(90,4));
    	//addSequential(new AutoVertDartMove(95, 3));
    	//addParallel(new AutoBoomDartMove(90, 3));
    	//addSequential(new AutoPivotHead(-90,4));; //turn
    	System.out.println("Initiate final approach");
    	addSequential(new AutoMove(1, 0.0, 10, 1.0)); //go to switch
    	//addParallel(new AutoBoomDartMove(.5, 0.0, 3)); //extend vert
    	addSequential(new ClawRetract());
    	//addSequential(new ClawRetract()); // release cube into switch
    	}
    }
}
