package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath4 extends CommandGroup {

    public AutoPath4() {
        addSequential(new AutoMove(5.3, 0, 10)); //move forward
        System.out.println("Initiating first turn");
        addSequential(new AutoPivotHead(-90, 3)); //first turn
        System.out.println("Moving across the switch");
        addSequential(new AutoMove(5.7, 0, 5)); //move forward to past the switch
        System.out.println("Initiating second turn");
        addSequential(new AutoPivotHead(-90, 3)); //second turn
        System.out.println("Moving to position next to the switch");
        addSequential(new AutoMove(1.6, 0, 3)); //move forward to parallel to switch deposit
        addParallel(new AutoDartMove(0, 250, 3));//extend vert
        System.out.println("Initiating final turn");
        addSequential(new AutoPivotHead(-90, 2.5)); //final turn
        System.out.println("Initiate final approach");
        addSequential(new AutoMove(0.5, 0, 3)); //go to switch
        addSequential(new ClawRetract());
        
    }
}
