package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath7 extends CommandGroup {

    public AutoPath7() {
        addSequential(new AutoMove(3, 0, 2)); //move forward
        System.out.println("Initiating first turn");
        addSequential(new AutoCounterclockwiseTurn()); //first turn
        System.out.println("Moving to edge of switch");
        addSequential(new AutoMove(2.9, 0, 3)); //move to edge of switch
        System.out.println("Initiating second turn");
        addSequential(new AutoClockwiseTurn()); //second turn
        System.out.println("Moving to next to switch deposit area");
        addSequential(new AutoMove(3.81, 0, 2)); //move to parallel with switch deposit zone
        addParallel(new AutoDartMove(95,90,3));
    	//addSequential(new AutoVertDartMove(95, 3));
    	//addParallel(new AutoBoomDartMove(90, 3));
        System.out.println("Initiating final turn");
        addSequential(new AutoClockwiseTurn()); //final turn
        System.out.println("Initiate final approach");
        addSequential(new AutoMove(1, 0, 3)); //go to switch
        addSequential(new ClawRetract());
    }
}
