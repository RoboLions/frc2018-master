package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath11 extends CommandGroup {

    public AutoPath11() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new AutoMove(5.3, 0, 10)); //move forward
        addSequential(new AutoPivotHead(90, 3)); //first turn
        addSequential(new AutoMove(5.9, 0, 10)); //move forward to past the switch
        //addSequential(new AutoPivotHead(90, 3)); //first turn
        addParallel(new AutoDartMove(50,500, 10));
        addSequential(new AutoPivotHead(-90, 3)); //first turn
        addSequential(new AutoMove(.5, 0, 3));
    	addSequential(new ClawRetract());
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
