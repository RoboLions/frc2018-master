package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath5Mirror extends CommandGroup {

    public AutoPath5Mirror() {
    	addSequential(new AutoMove(1.5, 0, 5)); //move forward
    	System.out.println("Initiating first turn");
    	addSequential(new AutoPivotHead(-90, 3)); //first turn
    	System.out.println("Moving to position next to switch");
    	addSequential(new AutoMove(1.4, 0, 5)); //move forward until parallel with switch deposit area
    	addParallel(new AutoDartMove(0, 250, 3));//extend vert
    	System.out.println("Initiating second turn");
    	addSequential(new AutoPivotHead(90, 3)); //second turn
    	System.out.println("Initiate final approach");
    	addSequential(new AutoMove(1.1, 0, 1)); //go to switch
    	addSequential(new ClawRetract());
    }
}
