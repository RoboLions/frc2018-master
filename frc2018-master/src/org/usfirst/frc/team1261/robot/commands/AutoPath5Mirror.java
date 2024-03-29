package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath5Mirror extends CommandGroup {

    public AutoPath5Mirror(boolean execute) {
    	if(execute == true) {
    	System.out.println("***AUTO PATH 5 MIRROR***");
    	addSequential(new AutoMove(1.5, 0, 5, 0.5)); //move forward - 1.5
    	System.out.println("Initiating first turn");
    	addSequential(new AutoPivotHead(-90, 3)); //first turn
    	System.out.println("Moving to position next to switch");
    	addSequential(new AutoMove(1.0, 0, 5, 0.5)); //move forward until parallel with switch deposit area - 1.2
        addParallel(new AutoDartMove(85,100,3,100));
    	//addSequential(new AutoVertDartMove(95, 3));
    	//addParallel(new AutoBoomDartMove(90, 3));
    	System.out.println("Initiating second turn");
    	addSequential(new AutoPivotHead(90, 1.5)); //second turn
    	System.out.println("Initiate final approach");
    	addSequential(new AutoMove(1.1, 0, 1, 1)); //go to switch - 1.1
    	addSequential(new ClawRetract());
    	}
    }
}
