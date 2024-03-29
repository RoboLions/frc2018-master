package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath9Mirror extends CommandGroup {

    public AutoPath9Mirror(boolean execute) {
    	if(execute == true) {
    	System.out.println("***AUTO PATH 9 MIRROR***");
    	addSequential(new AutoMove(2, 0, 4, 1.0)); //move forward
        System.out.println("Initiating first turn");
        addSequential(new AutoPivotHead(90, 3)); //first turn
        System.out.println("Moving to next to switch deposit area");
        addSequential(new AutoMove(4.4, 0, 7, 1.0)); //move to parallel with switch deposit zone
        addParallel(new AutoDartMove(95,90,3,100));
    	//addSequential(new AutoVertDartMove(95, 3));
    	//addParallel(new AutoBoomDartMove(90, 3));
        System.out.println("Initiating second turn");
        addSequential(new AutoPivotHead(-90, 3)); //second turn
        System.out.println("Initiate final approach");
        addSequential(new AutoMove(.6, 0, 1.5, 1.0)); //go to switch
        addSequential(new ClawRetract());
    	}
    }
}

