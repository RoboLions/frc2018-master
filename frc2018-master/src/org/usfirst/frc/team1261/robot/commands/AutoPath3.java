package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath3 extends CommandGroup {

    public AutoPath3(boolean execute) {
    	if(execute == true) {
    	System.out.println("***AUTO PATH 3***");
       addSequential(new AutoMove(5.3, 0, 6, 1.0)); //move forward
       //addSequential(new AutoMove(6, 0, 5)); //move forward
       //addParallel(new AutoDartMove(.5, 0, 5));
       System.out.println("Initiating first turn"); 
       addSequential(new AutoPivotHead(-90, 3)); //first turn
       System.out.println("Moving to position at back of switch");
       addSequential(new AutoMove(4.8, 0, 5, 1.0)); //move to position parallel to switch deposit area
       addParallel(new AutoDartMove(95,90,3,100));
   	//addSequential(new AutoVertDartMove(95, 3));
   	//addParallel(new AutoBoomDartMove(90, 3));
       System.out.println("Intiating second turn");
       addSequential(new AutoPivotHead(-90, 3)); //second turn
       System.out.println("Initiating final approach");
       addSequential(new AutoMove(.5, 0, .75, 1.0)); //go to switch
       addSequential(new ClawRetract());
    	}
    }
}
