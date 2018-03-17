package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath1Mirror extends CommandGroup {

    public AutoPath1Mirror() {
    	addSequential(new AutoMove(3.81, 0.0, 5)); //move forward
    	System.out.println("Now initiating turn");
    	addSequential(new AutoPivotHead(90,4));
    	addParallel(new AutoDartMove(0, 250, 3));//extend vert
    	//addSequential(new AutoPivotHead(-90,4));; //turn
    	System.out.println("Initiate final approach");
    	addSequential(new AutoMove(1, 0.0, 10)); //go to switch
    	addParallel(new AutoDartMove(.5, 0.0, 3)); //extend vert
    	addSequential(new ClawRetract());
    	//addSequential(new ClawRetract()); // release cube into switch
    }
}