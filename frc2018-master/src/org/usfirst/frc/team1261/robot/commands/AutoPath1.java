package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath1 extends CommandGroup {

    public AutoPath1() {
    //	addSequential(new AutoMove(0.5, 0.0, 3.0)); //move forward
    	//addSequential(new AutoPivotHead(-90,3)); //turn
    //	addSequential(new AutoPivotHead(90,3)); //turn
    //	addSequential(new AutoMove(0.5, 0.0, 3.0)); //move forward
   // 	addSequential(new AutoPivotHead(-90,3)); //turn
   // 	addSequential(new AutoPivotHead(90,3)); //turn
    	addSequential(new AutoMove(3.81, 0.0, 3)); //move forward
    	//addSequential(new AutoDartMove(0, 2.0, 3)); //extend vert
    	System.out.println("Now initiating turn");
    	addSequential(new AutoPivotHead(-90,3)); //turn
    	addParallel(new AutoDartMove(-5, 200, 3));//extend vert
    	System.out.println("Initiate final approach");
    	addSequential(new AutoMove(1, 0.0, 3)); //go to switch
    	addSequential(new ClawRetract());
    	//addSequential(new ClawRetract()); // release cube into switch
    	//(POSITION,HEADING,TIMEOUT)
    	 
    }
}
