package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath5 extends CommandGroup {

    public AutoPath5(boolean execute) {
    	if(execute == true) {
    	System.out.println("***AUTO PATH 5***");
    	addSequential(new AutoMove(.8, 0, 5)); //move forward
    	System.out.println("Initiating first turn");
    	addSequential(new AutoPivotHead(90, 3)); //first turn
    	System.out.println("Moving to position next to switch");
    	addSequential(new AutoMove(0.5, 0, 5)); //move forward until parallel with switch deposit area
    	addSequential(new AutoDartMove(85,100,3,100));
    	//addSequential(new AutoVertDartMove(95, 3));
    	//addParallel(new AutoBoomDartMove(90, 3));
    	System.out.println("Initiating second turn");
    	addSequential(new AutoPivotHead(-90, 3)); //second turn
    	System.out.println("Initiate final approach");
    	addSequential(new AutoMove(1.1, 0, 1)); //go to switch
    	addSequential(new ClawRetract());
    	}
    }
}
