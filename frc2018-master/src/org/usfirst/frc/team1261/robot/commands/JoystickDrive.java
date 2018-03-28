package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.OI;
import org.usfirst.frc.team1261.robot.Robot;
import org.usfirst.frc.team1261.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class JoystickDrive extends Command {
	
	public static final Joystick JOYSTICK = OI.getDriverJoystick();
	public static final int THROTTLE = OI.AXIS_LEFT_STICK_Y;
	public static final int ROTATE = OI.AXIS_RIGHT_STICK_X;
	// public static final int ANTI_TURBO_BUTTON = OI.BUTTON_LEFT_BUMPER;
	public static final boolean SQUARED_INPUTS = true;
	public static final double LEFT_ENCODER = Robot.driveTrain.getLeftEncoderPosition();
	public static final double RIGHT_ENCODER = Robot.driveTrain.getRightEncoderPosition(); 
	public static final double YAW_GAIN = 8; // 8
	public static final double YAW_INTEGRAL = .1; // .2
	double yaw_int_term = 0;
	double target_yaw = 0.0; //is not final because if it was we cannot change it.

    public JoystickDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {    	
    	Robot.driveTrain.stop();
    	Robot.driveTrain.resetEncoders();
    	Robot.driveTrain.ZeroYaw();
    	// target_yaw = Robot.driveTrain.getYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    //THIS RUNS UNDER TELEOP
    protected void execute() {
    	double current_yaw = Robot.driveTrain.getRPH()[0];
    	double yaw_error = target_yaw - current_yaw;
    	
    	
    	if((yaw_error) > 0.5) {
    		yaw_int_term = yaw_error + yaw_int_term;
    	}
    	if((yaw_error) < -0.5) {
    		yaw_int_term = yaw_error + yaw_int_term;
    	}
      	if(yaw_error > 15) {
      			yaw_int_term = 0;
      	}
      	if(yaw_error < -15) {
      			yaw_int_term = 0;
      	}
      	
    	//System.out.println("     yaw error: [" + yaw_error + "]");
    	double delta_v = yaw_error * YAW_GAIN + yaw_int_term * YAW_INTEGRAL;
    	//System.out.println("     yaw int: [" + yaw_int_term + "]");
    	//delta_v = 0;
    	
    	double throttle = -JOYSTICK.getRawAxis(THROTTLE);
    	if (Math.abs(throttle) < .2) {
    		throttle = 0;
    	}
		double rotate = JOYSTICK.getRawAxis(ROTATE);
		if (Math.abs(rotate) < .15) {
    		rotate = 0;
		}
		//rotate = 0.0;
		//delta_v = 0.0;
		//System.out.println("THROTTLE","ROTATE");
		
		if (JOYSTICK.getRawButton(OI.BUTTON_A) == true)	{
				target_yaw = -20 * Robot.limelight_x;
		}
		else{
				target_yaw = 0.0;
		}
		
		Robot.driveTrain.pidTuneTeleop(throttle, rotate, target_yaw);
		// SmartDashboard.putNumber("LEnc", LEFT_ENCODER);
		// SmartDashboard.putNumber("REnc", RIGHT_ENCODER);

		//Robot.driveTrain.getRobotDrive().arcadeDrive(throttle, rotate, SQUARED_INPUTS);
    }

    private double absolute(double yaw_error) {
		// TODO Auto-generated method stub
		return 0;
	}

	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    	Robot.driveTrain.resetEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.stop();
    }
}
