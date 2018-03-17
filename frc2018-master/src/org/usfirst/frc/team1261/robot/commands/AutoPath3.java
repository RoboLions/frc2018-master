package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath3 extends CommandGroup {

    public AutoPath3() {
       addSequential(new AutoMove(5.3, 0, 5)); //move forward
       //addSequential(new AutoMove(6, 0, 5)); //move forward
       addParallel(new AutoDartMove(.5, 0, 5));
       System.out.println("Initiating first turn"); 
       addSequential(new AutoPivotHead(-90, 3)); //first turn
       System.out.println("Moving to position at back of switch");
       addSequential(new AutoMove(4.8, 0, 4)); //move to position parallel to switch deposit area
       addParallel(new AutoDartMove(0, 250, 3));//extend vert
       System.out.println("Intiating second turn");
       addSequential(new AutoPivotHead(-90, 3)); //second turn
       System.out.println("Initiating final approach");
       addSequential(new AutoMove(.5, 0, .75)); //go to switch
       addSequential(new ClawRetract());
    }
}
