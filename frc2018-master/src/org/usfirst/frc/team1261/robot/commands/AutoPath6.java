package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath6 extends CommandGroup {

    public AutoPath6() {
        addSequential(new AutoMove(3, 0, 2)); //move forward
        System.out.println("Initiating first turn");
        addSequential(new AutoClockwiseTurn()); //first turn
        System.out.println("Moving to edge of switch");
        addSequential(new AutoMove(2.9, 0, 3)); //move a bit past the edge of switch
        System.out.println("Initiating second turn");
        addSequential(new AutoCounterclockwiseTurn()); //second turn
        System.out.println("Moving across width of switch");
        addSequential(new AutoMove(3, 0, 2)); //move across width of switch
        addParallel(new AutoDartMove(95,90,3));
    	//addSequential(new AutoVertDartMove(95, 3));
    	//addParallel(new AutoBoomDartMove(90, 3));
        System.out.println("Initiating third turn");
        addSequential(new AutoCounterclockwiseTurn()); //third turn
        System.out.println("Moving to next to the switch deposit area");
        addSequential(new AutoMove(4.9, 0, 5)); //move to parallel with switch deposit area
        System.out.println("Initiating final turn");
        addSequential(new AutoCounterclockwiseTurn()); //final turn
        System.out.println("Initiate final approach");
        addSequential(new AutoMove(.25, 0, .75)); //go to switch
        addSequential(new ClawRetract());
    }
}
