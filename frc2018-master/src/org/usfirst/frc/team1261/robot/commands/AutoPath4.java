package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath4 extends CommandGroup {

    public AutoPath4(boolean execute) {
    	if(execute == true) {
    	System.out.println("***AUTO PATH 4***");
        addSequential(new AutoMove(5.3, 0, 10, 1.0)); //move forward
        System.out.println("Initiating first turn");
        addSequential(new AutoPivotHead(-90, 3)); //first turn
        System.out.println("Moving across the switch");
        addSequential(new AutoMove(5.9, 0, 6.5, 1.0)); //move forward to past the switch
        System.out.println("Initiating second turn");
        addSequential(new AutoPivotHead(-90, 3)); //second turn
        System.out.println("Moving to position next to the switch");
        addSequential(new AutoMove(1.6, 0, 3, 1.0)); //move forward to parallel to switch deposit
        addParallel(new AutoDartMove(95,90,3,100));
    	//addSequential(new AutoVertDartMove(95, 3));
    	//addParallel(new AutoBoomDartMove(90, 3));
        System.out.println("Initiating final turn");
        addSequential(new AutoPivotHead(-90, 2.5)); //final turn
        System.out.println("Initiate final approach");
        addSequential(new AutoMove(0.5, 0, 3, 1.0)); //go to switch
        addSequential(new ClawRetract());
    	}
    }
}
