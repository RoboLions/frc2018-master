package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath11Mirror extends CommandGroup {

    public AutoPath11Mirror(boolean execute) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
    	if(execute == true) {
    	System.out.println("***AUTO PATH 11 MIRROR***");
    	addSequential(new AutoMove(4.7, 0, 10)); //move forward
        addSequential(new AutoPivotHead(90, 2.5)); //first turn
        addSequential(new AutoMove(3.475, 0, 10)); //5move forward to past the switch
        //addSequential(new AutoPivotHead(90, 3)); //first turn
        addSequential(new AutoDartMove(120,100,2.5,300));
    	//addSequential(new AutoVertDartMove(95, 3));
    	//addParallel(new AutoBoomDartMove(90, 3));
        addSequential(new AutoPivotHead(-100, 1.8)); //first urn
        addSequential(new AutoMove(.36, 0, 3));
        addSequential(new AutoMove(0,0,.5));
    	//addSequential(new ClawRetract());
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	}
    }
}
