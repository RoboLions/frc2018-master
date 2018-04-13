package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath12 extends CommandGroup {

    public AutoPath12() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

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
    	addSequential(new AutoMove(6.55,0, 4.5));
    	addSequential(new AutoDartMove(130,100,2,300));
    	 //System.out.println("Intiating turn");
    	//addSequential(new AutoVertDartMove(95, 3));
    	//addParallel(new AutoBoomDartMove(90, 3));
    	
    	//addSequential(new AutoPivotHead(-90, 2));
    	/*
    	addSequential(new AutoMove(7,0, 10));
    	addParallel(new AutoDartMove(50,500, 10));
    	*/
    	addSequential(new AutoPivotHead(-90, 1.5));
    	 //System.out.println("final approach");
    	addSequential(new AutoMove(.1, 0, 2));
    	addSequential(new ClawRetract());
    	addSequential(new AutoMoveReverse(-.1, 0, 1));
    	addSequential(new AutoStow());
    	addSequential(new AutoPivotHead(60, 1.5));
    	addSequential(new AutoMove(2,0,1.5));
    	addSequential(new AutoMove(.5,30,1));
    	addSequential(new AutoAcquire());
    	addSequential(new ClawGrab());
    	addSequential(new AutoMoveReverse(-.1, 0, .5));
    	addSequential(new AutoSwitch());
    	addSequential(new AutoMoveReverse(.1, 0, .5));
    	addSequential(new ClawRetract());
    }
}
