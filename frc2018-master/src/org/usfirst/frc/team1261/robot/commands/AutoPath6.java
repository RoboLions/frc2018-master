package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath6 extends CommandGroup {

    public AutoPath6(boolean execute) {
    	if(execute == true) {
    	System.out.println("***AUTO PATH 6***");
        addSequential(new AutoMove(3, 0, 4, 1.0)); //move forward
        System.out.println("Initiating first turn");
        addSequential(new AutoClockwiseTurn()); //first turn
        System.out.println("Moving to edge of switch");
        addSequential(new AutoMove(2.9, 0, 3, 1.0)); //move a bit past the edge of switch
        System.out.println("Initiating second turn");
        addSequential(new AutoCounterclockwiseTurn()); //second turn
        System.out.println("Moving across width of switch");
        addSequential(new AutoMove(3, 0, 3.5, 1.0)); //move across width of switch
        addParallel(new AutoDartMove(95,90,3,100));
    	//addSequential(new AutoVertDartMove(95, 3));
    	//addParallel(new AutoBoomDartMove(90, 3));
        System.out.println("Initiating third turn");
        addSequential(new AutoCounterclockwiseTurn()); //third turn
        System.out.println("Moving to next to the switch deposit area");
        addSequential(new AutoMove(4.9, 0, 5, 1.0)); //move to parallel with switch deposit area
        System.out.println("Initiating final turn");
        addSequential(new AutoCounterclockwiseTurn()); //final turn
        System.out.println("Initiate final approach");
        addSequential(new AutoMove(.25, 0, .75, 1.0)); //go to switch
        addSequential(new ClawRetract());
    	}
    }
}
