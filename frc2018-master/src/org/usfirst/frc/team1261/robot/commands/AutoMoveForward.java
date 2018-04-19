package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoMoveForward extends Command {
	
	public static final double POWER = .4;
	public static final double YAW_GAIN = 9;
	public static final double YAW_INTEGRAL = .3;
	public static final double POS_GAIN = 8;
	public static final double POS_INTEGRAL = .025;
	public static final double POS_DERIVATIVE = 0;
	public static final double POS_CMD = 3.5; //3 meters is goals
	public static final double HEAD_CMD = 0;
	double yaw_int_term = 0;
	double pos_int_term = 0;
	
	double target_yaw = HEAD_CMD; //is not final because if it was we cannot change it.
    
    public AutoMoveForward() {
    	requires(Robot.driveTrain);
    	setTimeout(8.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.ZeroYaw();
    	Robot.driveTrain.stop();
    	Robot.driveTrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    //THIS RUNS UNDER AUTO
    protected void execute() {
    	// System.out.println("Hello");
    	double current_yaw = Robot.driveTrain.getRPH()[0];
    	System.out.println(Robot.driveTrain.getRPH()[0]);
    	double yaw_error = target_yaw - current_yaw;
    	// do not change
    	if (yaw_error > 0.1) {
    		yaw_int_term += yaw_error;
    	}
    	if (yaw_error < -0.1) {
    		yaw_int_term += yaw_error;
    	}
    	if (yaw_error > 15.0) {
    		yaw_int_term = 0;
    	}
    	if (yaw_error < -15.0) {
    		yaw_int_term = 0;
    	}
    	
    	// this is a test 
    	
    	System.out.println(yaw_error);
    	double delta_v = yaw_error * YAW_GAIN + yaw_int_term * YAW_INTEGRAL;
    	
    	double th = 0;	// th is a velocity command
    	double enc_pos = Robot.driveTrain.distanceTravelledinMeters();
    	double pos_err = POS_CMD - enc_pos;
    	double enc_vel = Robot.driveTrain.VelocityinMeters();
    	if (pos_err > 0.02) {
    		pos_int_term += pos_err;
    	}
    	if (pos_err < -0.02) { //no integral gain within 2 cm
    		pos_int_term += pos_err;
    	}
    	if (pos_err > 0.1) {
    		pos_int_term = 0;
    	}
    	if (pos_err < -0.1) {
    		pos_int_term = 0;
    	}
    	th = pos_err*POS_GAIN + pos_int_term*POS_INTEGRAL - enc_vel*POS_DERIVATIVE;
    	
    	//posn_err = pos_cmd-enc_posn
    	//th = posn_err*kp + posn_err_int*ki - enc_vel*kd
    	double ro = 0;
    	Robot.driveTrain.pidTuneAuto(th, ro, delta_v, 1.0);
    	
    	//Robot.driveTrain.getRobotDrive().tankDrive(POWER, -POWER);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.driveTrain.distanceTravelledinMeters() > POS_CMD) || isTimedOut();
    	//return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.stop();
    }
    
}
