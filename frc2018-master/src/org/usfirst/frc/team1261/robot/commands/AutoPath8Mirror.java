package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath8Mirror extends CommandGroup {

    public AutoPath8Mirror() {
    	addSequential(new AutoMove(2, 0, 10)); //move forward
        System.out.println("Initiating first turn");
        addSequential(new AutoPivotHead(90, 3)); //first turn
        System.out.println("Moving to next to front of switch");
        addSequential(new AutoMove(1.5, 0, 10)); //move to parallel with switch deposit zone
        addParallel(new AutoDartMove(0, 250, 3));//extend vert
        System.out.println("Intiating second turn");
        addSequential(new AutoPivotHead(-90, 3)); //second turn
        System.out.println("Initiate final approach");
        addSequential(new AutoMove(.6, 0, 1)); //go to switch
        addSequential(new ClawRetract());
    }
}