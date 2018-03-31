package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoAcquire extends CommandGroup {

    public AutoAcquire() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
        //addSequential(new AutoBoomDartMove(-420, 0, 1.5));//extend boom
    	//addSequential(new AutoBoomDartMove(-420, 420, 1.5));//extend vert
    	//addSequential(new ClawGrab());
    	//addSequential(new ClawGrab());
    	//extend vert
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
