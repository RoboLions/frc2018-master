package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPath10Mirror extends CommandGroup {

    public AutoPath10Mirror(boolean execute) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	if(execute == true) {
    	System.out.println("***AUTO PATH 10 MIRROR***");
    	System.out.println("***AUTO PATH 10 MIRROR***");
    	addSequential(new AutoMove(6.55,0, 5,1.8));
    	addSequential(new AutoDartMove(130,100,2.5,500));
    	 System.out.println("Intiating turn");
    	//Robot.driveTrain.resetEncoders();
    	addSequential(new AutoPivotHead(90, 1.5));
    	//Robot.driveTrain.resetEncoders();
    	 System.out.println("final approach");
    	addSequential(new AutoMove(.3, 0, 3, 0.5));
    	addSequential(new AutoPivotHead(-20,1));
    	addSequential(new ClawRetract());
    	//addSequential(new AutoVertDartMove(95, 3));
    	//addParallel(new AutoBoomDartMove(90, 3));
    	//addSequential(new AutoPivotHead(-90, 2));
    	/*
    	addSequential(new AutoMove(7,0, 10));
    	addParallel(new AutoDartMove(50,500, 10));
    	*/
    	
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
