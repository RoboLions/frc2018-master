package org.usfirst.frc.team1261.robot.subsystems;

import java.awt.Robot;

import org.usfirst.frc.team1261.robot.OI;
import org.usfirst.frc.team1261.robot.RobotMap;
import org.usfirst.frc.team1261.robot.commands.AutoPivotHead;
import org.usfirst.frc.team1261.robot.commands.JoystickDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem { 
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	StringBuilder sb = new StringBuilder();
	public double effective_rate_command = 0.0; // m/s
	public static final double DEFAULT_VOLTAGE_RAMP_RATE = 8.0;
	// public static final int MOTOR_ENCODER_CODES_PER_REV = 1440; 2017
	public static final int MOTOR_ENCODER_CODES_PER_REV = 4096;
	//public static final int TIMEOUT_MS = 20; //20
	//public static final double MOTOR_ENCODER_CODES_PER_REV = 1440; // 2017
	//public static final int MOTOR_ENCODER_CODES_PER_REV = 4096;
	public static final int TIMEOUT_MS = 10; //20
	public static final double WHEEL_DIAMETER_2017 = 0.10;
	public static final double DIAMETER_INCHES = 10;// 2018 10
	public static final double IN_TO_M = .0254;//.0254
	public static final double WHEEL_DIAMETER_2018 = DIAMETER_INCHES * IN_TO_M; // in meters
	//public static final double WHEEL_DIAMETER_2018 = ((DIAMETER_INCHES * .94) * IN_TO_M); // in meters
    public static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER_2018 * Math.PI;//3.14159;//(Math.PI;)
	//public static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER_2017 * Math.PI;//3.14159;//(Math.PI;)
	public static final double TICKS_PER_METER = (double)MOTOR_ENCODER_CODES_PER_REV * (double)(1/WHEEL_CIRCUMFERENCE);
			//1.0/0.59208918;
//			//1.6889348;
	public static final double METERS_PER_TICKS = 1 / TICKS_PER_METER; 
	
	
	
	// Ticks per meter should stay like this
	
	// public static final double MOTOR_NOMINAL_OUTPUT_VOLTAGE = 0.0;
	// public static final double MOTOR_PEAK_OUTPUT_VOLTAGE = 12.0;
	public static final int MOTOR_PIDF_PROFILE = 0;
	public static final double MOTOR_GAIN_F = .6;
	public static final double MOTOR_GAIN_P = 1.2; // 1.2
	public static final double MOTOR_GAIN_I = 0.0;	
	public static final double MOTOR_GAIN_D = 0.3;
	//public static final double MOTOR_RAMPRATE = 0.5; //1.25
	public static final double MOTOR_RAMPRATE = 1; //1.25

	WPI_TalonSRX leftMotorFront = RobotMap.leftDriveMotorFront;
	WPI_TalonSRX leftMotorBack = RobotMap.leftDriveMotorBack;
	WPI_TalonSRX rightMotorFront = RobotMap.rightDriveMotorFront;
	WPI_TalonSRX rightMotorBack = RobotMap.rightDriveMotorBack;
	PigeonIMU imu = new PigeonIMU(leftMotorBack);
	// code borrowed from standard wpi lib library       2/10/2018     M.F.
	DifferentialDrive driveTrain = RobotMap.robotDrive;
	public Encoder leftDriveEncoder = RobotMap.leftDriveEncoder;
	public Encoder rightDriveEncoder = RobotMap.rightDriveEncoder;
	
	//public final double MOTOR_DISTANCE_PER_TICK = leftDriveEncoder.getDistancePerPulse();
	
	public DriveTrain() {
		double motorOutputLeft = leftMotorFront.getMotorOutputPercent();
		double motorOutputRight = rightMotorFront.getMotorOutputPercent();
		
		 leftMotorFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, TIMEOUT_MS); //old encoder
		 rightMotorFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, TIMEOUT_MS); //old encoder
		// leftMotorFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, TIMEOUT_MS); //old encoder
		// rightMotorFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, TIMEOUT_MS); //old encoder
		 
		 leftMotorFront.set(ControlMode.Velocity, 0);
		 rightMotorFront.set(ControlMode.Velocity, 0);
		 
		 leftMotorFront.setSensorPhase(true);
		 rightMotorFront.setSensorPhase(true);
		 
		 leftMotorFront.setInverted(false);
		 rightMotorFront.setInverted(true);
		 
		 rightMotorBack.setInverted(true);
		 leftMotorBack.setInverted(false);

		 leftMotorBack.set(ControlMode.Follower, leftMotorFront.getDeviceID());
		 rightMotorBack.set(ControlMode.Follower, rightMotorFront.getDeviceID());
		 
		 leftMotorFront.configNominalOutputForward(0,TIMEOUT_MS);
		 leftMotorFront.configNominalOutputReverse(0,TIMEOUT_MS);
		 leftMotorFront.configPeakOutputForward(1,TIMEOUT_MS);
		 leftMotorFront.configPeakOutputReverse(-1,TIMEOUT_MS);
		 
		 rightMotorFront.configNominalOutputForward(0,TIMEOUT_MS);
		 rightMotorFront.configNominalOutputReverse(0,TIMEOUT_MS);
		 rightMotorFront.configPeakOutputForward(1,TIMEOUT_MS);
		 rightMotorFront.configPeakOutputReverse(-1,TIMEOUT_MS);
		 
		 leftMotorFront.configNeutralDeadband(0.001, TIMEOUT_MS);
		 leftMotorBack.configNeutralDeadband(0.001, TIMEOUT_MS);
		 rightMotorFront.configNeutralDeadband(0.001, TIMEOUT_MS);
		 rightMotorBack.configNeutralDeadband(0.001, TIMEOUT_MS);
		 
		 leftMotorFront.config_kF(0,MOTOR_GAIN_F,TIMEOUT_MS);
		 leftMotorFront.config_kP(0,MOTOR_GAIN_P,TIMEOUT_MS);
		 leftMotorFront.config_kI(0,MOTOR_GAIN_I,TIMEOUT_MS);
		 leftMotorFront.config_kD(0,MOTOR_GAIN_D,TIMEOUT_MS);
		 leftMotorFront.configClosedloopRamp(MOTOR_RAMPRATE, TIMEOUT_MS);
		 
		 rightMotorFront.config_kF(0,MOTOR_GAIN_F,TIMEOUT_MS);
		 rightMotorFront.config_kP(0,MOTOR_GAIN_P,TIMEOUT_MS);
		 rightMotorFront.config_kI(0,MOTOR_GAIN_I,TIMEOUT_MS);
		 rightMotorFront.config_kD(0,MOTOR_GAIN_D,TIMEOUT_MS);
		 rightMotorFront.configClosedloopRamp(MOTOR_RAMPRATE, TIMEOUT_MS);
		 
		 leftMotorBack.config_kF(0,MOTOR_GAIN_F,TIMEOUT_MS);
		 leftMotorBack.config_kP(0,MOTOR_GAIN_P,TIMEOUT_MS);
		 leftMotorBack.config_kI(0,MOTOR_GAIN_I,TIMEOUT_MS);
		 leftMotorBack.config_kD(0,MOTOR_GAIN_D,TIMEOUT_MS);
		 leftMotorBack.configClosedloopRamp(MOTOR_RAMPRATE, TIMEOUT_MS);
		 
		 rightMotorBack.config_kF(0,MOTOR_GAIN_F,TIMEOUT_MS);
		 rightMotorBack.config_kP(0,MOTOR_GAIN_P,TIMEOUT_MS);
		 rightMotorBack.config_kI(0,MOTOR_GAIN_I,TIMEOUT_MS);
		 rightMotorBack.config_kD(0,MOTOR_GAIN_D,TIMEOUT_MS);
		 rightMotorBack.configClosedloopRamp(MOTOR_RAMPRATE, TIMEOUT_MS);
		 
		
		 sb.append("\toutleft:");
		 sb.append(motorOutputLeft);
		 sb.append("\toutright:");
		 sb.append(motorOutputRight);
		 sb.append("\tspdleft:");
		 sb.append(leftMotorFront.getSelectedSensorVelocity(0));
		 sb.append("\tspdright:");
		 sb.append(rightMotorFront.getSelectedSensorVelocity(0));
		 
		 //double targetVelocity_UnitsPer100ms = OI.getManipulatorJoystick().getRawAxis(OI.AXIS_LEFT_STICK_Y) * 4096 * 500.0 / 600;
		 /* 1500 RPM in either direction */
		 leftMotorFront.set(ControlMode.Velocity, 0);
		 rightMotorFront.set(ControlMode.Velocity, 0);
		 /* append more signals to print when in speed mode. */
		 sb.append("\terrleft:");
		 sb.append(leftMotorFront.getClosedLoopError(0));
		 sb.append("\terrright:");
		 sb.append(rightMotorFront.getClosedLoopError(0));
		 sb.append("\ttrg:");
		 //sb.append(targetVelocity_UnitsPer100ms); 
		 /* Percent output mode */
		 //System.out.println(sb.toString()); 
		 sb.setLength(0);
		 
		 //System.out.println("Initialization of drivetrain complete");
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new JoystickDrive());
    }
    
    public double getYaw() {
    	double[] ypr = new double[3];
    	imu.getYawPitchRoll(ypr);
    	SmartDashboard.putNumber("IMU Yaw", ypr[0]);
    	//System.out.println("yaw:" + ypr[0]);
    	return ypr[0];
    }
    
    public double getPitch() {
    	double[] ypr = new double[3];
    	imu.getYawPitchRoll(ypr);
    	SmartDashboard.putNumber("IMU Pitch", ypr[1]);
    	//System.out.println("pitch:" + ypr[1]);
    	return ypr[1];
    }
    
    public double getRoll() {
    	double[] ypr = new double[3];
    	imu.getYawPitchRoll(ypr);
    	SmartDashboard.putNumber("IMU Roll", ypr[2]);
    	//System.out.println("roll:" + ypr[2]);
    	return ypr[2];
    }

    
    public double[] getRPH() {
    	double[] ypr = new double[3];
    	imu.getYawPitchRoll(ypr);
    	SmartDashboard.putNumber("IMU Yaw", ypr[0]);
    	SmartDashboard.putNumber("IMU Pitch", ypr[1]);
    	SmartDashboard.putNumber("IMU Roll", ypr[2]);
    	return(ypr);
    }
    
    double yaw = getRPH()[0];
    double pitch = getRPH()[1];
    double roll = getRPH()[2];
    
    public void stop() {
		// controller.disable();
		//setVoltageRampRate(0.0);
		driveTrain.stopMotor();
	}
    
    public void ZeroYaw() {
    	imu.setYaw(0, TIMEOUT_MS);
    	imu.setFusedHeading(0, TIMEOUT_MS);
    }

	public DifferentialDrive getRobotDrive() {
		return driveTrain;
	}

	public WPI_TalonSRX getLeftMotorFront() {
		return leftMotorFront;
	}

	public WPI_TalonSRX getLeftMotorBack() {
		return leftMotorBack;
	}

	public WPI_TalonSRX getRightMotorFront() {
		return rightMotorFront;
	}

	public WPI_TalonSRX getRightMotorBack() {
		return rightMotorBack;
	}
	
	public Encoder getLeftEncoder() {
		return leftDriveEncoder;
	}
	
	public Encoder getRightEncoder() {
		return rightDriveEncoder;
	}

	/**
	public int getRightEncoderPosition() {
		System.out.println("Getting Right Position");
		return rightDriveEncoder.get();
	}
	public int getLeftEncoderPosition() {
		System.out.println("Getting Left Position");
		return leftDriveEncoder.get();
	}
	
	public void resetEncoders() {
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
	}
	
	*/
	
	public double distanceTravelled() {
		return (getLeftEncoderPosition() + getRightEncoderPosition()) / 2;
	}

	public double getLeftEncoderPosition() {
		// System.out.println(leftMotorFront.getSensorCollection().getQuadraturePosition());
		SmartDashboard.putNumber("left position - ticks", leftMotorFront.getSensorCollection().getQuadraturePosition());
		return leftMotorFront.getSensorCollection().getQuadraturePosition();
	}

	public double getRightEncoderPosition() {
		// System.out.println(rightMotorFront.getSensorCollection().getQuadraturePosition());
		SmartDashboard.putNumber("right position - ticks", rightMotorFront.getSensorCollection().getQuadraturePosition());
		return rightMotorFront.getSensorCollection().getQuadraturePosition();
	}
	
	public double getLeftEncoderVelocity() {
		// System.out.println(leftMotorFront.getSensorCollection().getQuadraturePosition());
	    SmartDashboard.putNumber("left velocity - ticks", leftMotorFront.getSensorCollection().getQuadratureVelocity());
		//SmartDashboard.putNumber("left velocity - ticks/sec", leftMotorFront.getSelectedSensorVelocity(0));
		return leftMotorFront.getSensorCollection().getQuadratureVelocity();
	}

	public double getRightEncoderVelocity() {
		// System.out.println(rightMotorFront.getSensorCollection().getQuadraturePosition());
		SmartDashboard.putNumber("right velocity - ticks", rightMotorFront.getSensorCollection().getQuadratureVelocity());
		//SmartDashboard.putNumber("right velocity - ticks/sec", rightMotorFront.getSelectedSensorVelocity(0));
		return rightMotorFront.getSensorCollection().getQuadratureVelocity();
	}
	
	// New library added to return speed in meters and meters per second
	

	public double distanceTravelledinMeters() {
		//double value = ((-1 * getLeftEncoderPosition() + getRightEncoderPosition()) / 2.0) / TICKS_PER_METER;
		double value = -(0.5*((double)leftMotorFront.getSensorCollection().getQuadraturePosition() * METERS_PER_TICKS + 
				                    -(double)rightMotorFront.getSensorCollection().getQuadraturePosition() * METERS_PER_TICKS  )) ;
		SmartDashboard.putString("Distance Traveled - meters", Double.toString(value));
		return value;
	}

	public double getLeftEncoderPositioninMeters() {
		// System.out.println(leftMotorFront.getSensorCollection().getQuadraturePosition());
		double value = leftMotorFront.getSensorCollection().getQuadraturePosition() * METERS_PER_TICKS;
		SmartDashboard.putNumber("left position - meters", value);
		return value;
	}

	public double getRightEncoderPositioninMeters() {
		double value = rightMotorFront.getSensorCollection().getQuadraturePosition() * METERS_PER_TICKS;
		SmartDashboard.putNumber("right position - meters", value);
		return value;
	}
	
	public double getLeftEncoderVelocityinMetersperSec() {
		double value = -(leftMotorFront.getSensorCollection().getQuadratureVelocity()) * METERS_PER_TICKS;
		SmartDashboard.putNumber("left velocity - meters", -(leftMotorFront.getSensorCollection().getQuadratureVelocity()) * METERS_PER_TICKS);
		return value;
	}

	public double getRightEncoderVelocityinMetersperSec() {
		double value = (rightMotorFront.getSensorCollection().getQuadratureVelocity()) * METERS_PER_TICKS;
		SmartDashboard.putNumber("right velocity - meters", (rightMotorFront.getSensorCollection().getQuadratureVelocity()) * METERS_PER_TICKS);
		return value;
	}
	
	public double VelocityinMeters() {
		double value = (-(leftMotorFront.getSensorCollection().getQuadratureVelocity()) + rightMotorFront.getSensorCollection().getQuadratureVelocity()) * METERS_PER_TICKS;
		// SmartDashboard.putString("Distance Traveled - meters", Double.toString(value));
		return value;
	}
	/*
	public double adjustMotors(double leftEncoder, double rightEncoder) {
		return -(leftEncoder/rightEncoder);
	}
	*/


	public Sendable returnEncoderData(String direction) {
		if (direction == "left") {
			return leftDriveEncoder;
		} else {
			return rightDriveEncoder;
		}
	}
	
	public void resetEncoders() {
		leftMotorFront.getSensorCollection().setQuadraturePosition(0, TIMEOUT_MS);
		rightMotorFront.getSensorCollection().setQuadraturePosition(0, TIMEOUT_MS);
	}
	
	// CURRENTLY RUNNING UNDER AUTONOMOUS
	
	public void pidTuneAuto(double th,//velocity throttle 
			                double ro, //rotation from joystick
			                double off) {//heading offset
		
		// 2018 test code to perfect motion control
		// Uses Pigeon IMU and Closed Loop Velocity
		// Checkable in web based configuration
		// Web address - 10.12.61.2
		

		double motorOutputLeft = leftMotorFront.getMotorOutputPercent();
		double motorOutputRight = rightMotorFront.getMotorOutputPercent();
		
		/*
		sb.append("\toutleft:");
		sb.append(motorOutputLeft);
		sb.append("\toutright:");
		sb.append(motorOutputRight);
		sb.append("\tspdleft:");
		sb.append(leftMotorFront.getSelectedSensorVelocity(0));
		sb.append("\tspdright:");
		sb.append(rightMotorFront.getSelectedSensorVelocity(0));
       */

		double throttle_scaler = 1;
		
		//th = th * 1077;
		
		th = throttle_scaler * th; // to meters per second
		
		// take input throttle from joystick and transform 
		// meters per second --> encoder counts per 100 milliseconds
		
		double speed_limit = 2; // 1
		
		if (th > speed_limit) {
			th = speed_limit;
		}
		if (th < -speed_limit) {
			th = -speed_limit;
		}
		/*
		if (ro > speed_limit) {
			ro = speed_limit;
		}
		if (ro < -speed_limit) {
			ro = -speed_limit;
		}
		*/
		// circumference of wheel = 4 * pi
		// circumference of COMPETITION wheel = 8 * pi
			double mps_to_encp100ms = TICKS_PER_METER / 10; //METERS_PER_TICKS * 0.01;
			// transforms meter per second to ticks per 100 milliseconds
			double Kpivot = 400;
			double ThetaCMD = mps_to_encp100ms * th;
	
			//double target_speed = TICKS_PER_METER / 10;
			//double Kthrottle = 1.0;
			//double Kpivot = 500.0;
			//double ThetaCMD = Kthrottle * th * target_speed;	
			double Thetaheading = (Kpivot * ro) - off;
			//System.out.println(off);
			double Koffset = 1; 
			double Thetaoffset = Koffset * Thetaheading;
			double ThetaLeftMotor = ThetaCMD + Thetaoffset;
			double ThetaRightMotor = ThetaCMD - Thetaoffset;
		 
		// 1500 RPM in either direction 
		//System.out.println("throttle: " + th);
		leftMotorFront.set(ControlMode.Velocity, ThetaLeftMotor);
		//System.out.println("left velocity: " + leftMotorFront.getSelectedSensorVelocity(0));
		rightMotorFront.set(ControlMode.Velocity, ThetaRightMotor);
		//System.out.println("right velocity: " + rightMotorFront.getSelectedSensorVelocity(0));
	}
	
	// THIS RUNS UNDER TELEOP
	
	public void pidTuneTeleop(double th, double ro, double yc) {
		double motorOutputLeft = leftMotorFront.getMotorOutputPercent();
		double motorOutputRight = rightMotorFront.getMotorOutputPercent();
		
		/*
		sb.append("\toutleft:");
		sb.append(motorOutputLeft);
		sb.append("\toutright:");
		sb.append(motorOutputRight);
		sb.append("\tspdleft:");
		sb.append(leftMotorFront.getSelectedSensorVelocity(0));
		sb.append("\tspdright:");
		sb.append(rightMotorFront.getSelectedSensorVelocity(0));
       */

		double throttle_scaler = 1;
		
		//th = th * 1077;
		
		th = throttle_scaler * th; // to meters per second
		
		// take input throttle from joystick and transform 
		// meters per second --> encoder counts per 100 milliseconds
		
		//double speed_limit = 5; // speed limit one meter per second
		double speed_limit = 1.5; // speed limit one meter per second
		double regular_speed_limit = 1.5;
		double acceleration_limit = .25;
		double rio_update = 0.05;
		double safe_mode_speed = 0.25;
		/*
		if (OI.getDriverJoystick().getRawButton(1)) {
			speed_limit = safe_mode_speed;
			System.out.println("BEE BEE BUMBLE BEE");
		}
		else {
			speed_limit = regular_speed_limit;
		}
		*/
		if (OI.getDriverJoystick().getRawAxis(2) >= 0.5) {
			speed_limit = safe_mode_speed;
			System.out.println();
		}
		else {
			speed_limit = regular_speed_limit;
		}
		throttle_scaler = speed_limit;
		th = throttle_scaler * th; // to meters per second
		
		if (th == 0) {
			effective_rate_command = 0;
		/*
			if (effective_rate_command > 0) {
				effective_rate_command -= (2 * acceleration_limit * rio_update);
			}
			else if (effective_rate_command < 0) {
				effective_rate_command += (2 * acceleration_limit * rio_update);
			}
			if (Math.abs(effective_rate_command) < 2*acceleration_limit*rio_update){
			}*/
		}
		
		 if (th > 0) {
			if(th > effective_rate_command){
				  effective_rate_command = effective_rate_command + 
						  (acceleration_limit *rio_update);
		/*
			} else if (th < effective_rate_command) {
				  effective_rate_command = effective_rate_command - 
						  (acceleration_limit *rio_update);
				  */
			} else {
				effective_rate_command = th;
			}
		}
		
		if(th < 0) {
			if (th < effective_rate_command){
				effective_rate_command = effective_rate_command -
				(acceleration_limit *rio_update);
			}
			/*
			} else if (th > effective_rate_command) {
				  effective_rate_command = effective_rate_command + 
						(acceleration_limit *rio_update);
			*/
			 else {
				effective_rate_command = th;
			}
		}
		//effective_rate_command = th;
		
		if (effective_rate_command > speed_limit) {
			effective_rate_command = speed_limit;
		}
		
		if (effective_rate_command < -speed_limit) {
			effective_rate_command = -speed_limit;
		}
		
		
		// ro is being dampened by the speed limit, even though it should be unbounded
		if (ro > speed_limit) {
			ro = speed_limit;
		}
		if (ro < -speed_limit) {
			ro = -speed_limit;
		}
		
		// circumference of wheel = 4 * pi
		// circumference of COMPETITION wheel = 8 * pi
			double mps_to_encp100ms = TICKS_PER_METER / 10; //METERS_PER_TICKS * 0.01;
			// transforms meter per second to ticks per 100 milliseconds
			double Kpivot = 525;
			//double ThetaCMD = mps_to_encp100ms * th;
			double ThetaCMD = mps_to_encp100ms * effective_rate_command;
			//double target_speed = TICKS_PER_METER / 10;
			//double Kthrottle = 1.0;
			//double Kpivot = 500.0;
			//double ThetaCMD = Kthrottle * th * target_speed;	
			double Thetaheading = (Kpivot * ro) - yc;
			double Koffset = 1; 
			double Thetaoffset = Koffset * Thetaheading;
			double ThetaLeftMotor = ThetaCMD + Thetaoffset;
			double ThetaRightMotor = ThetaCMD - Thetaoffset;
		 
		// 1500 RPM in either direction 
		//System.out.println("throttle: " + th);
		leftMotorFront.set(ControlMode.Velocity, ThetaLeftMotor);
		//System.out.println("left velocity: " + leftMotorFront.getSelectedSensorVelocity(0));
		rightMotorFront.set(ControlMode.Velocity, ThetaRightMotor);
		//System.out.println("right velocity: " + rightMotorFront.getSelectedSensorVelocity(0));
		}
		// append more signals to print when in speed mode. 
		/*
		sb.append("\terrleft:");
		sb.append(leftMotorFront.getClosedLoopError(0));
		sb.append("\terrright:");
		sb.append(rightMotorFront.getClosedLoopError(0));
		sb.append("\ttrg:");
		sb.append("Th");
		sb.append(th); 
		sb.append("ro");
		// sb.append(Thetaoffset);
		// Percent output mode 
		System.out.println(sb.toString()); 
		sb.setLength(0);
		*/
	/**
	public int distanceTraveled() {
		return (getLeftEncoderPosition() + getRightEncoderPosition()) / 2;
	}
	*/
}

