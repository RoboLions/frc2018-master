package org.usfirst.frc.team1261.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team1261.robot.OI;
import org.usfirst.frc.team1261.robot.RobotMap;

import org.usfirst.frc.team1261.robot.commands.DartMove;

/**
 *
 */


// I know it says "DartActuator" singular, but this subsystem operates both actuators (Top and Bottom) separately at the same time

public class DartActuator extends Subsystem {
	
	public static final Joystick JOYSTICK = OI.getManipulatorJoystick();
	public static final int BOOM_THROTTLE = OI.AXIS_RIGHT_STICK_Y;
	public static final int VERT_THROTTLE = OI.AXIS_LEFT_STICK_Y;
	
	private static final int TIMEOUT_MS = 10;
	
	public static WPI_TalonSRX vertMotor = RobotMap.vertDartMotor;
	public static WPI_TalonSRX boomMotor = RobotMap.boomDartMotor;
	
	public boolean status = false;
	
	public static final int VERT_PIDF_PROFILE = 0;
	public static final double VERT_GAIN_F = 1; //1
	public static final double VERT_GAIN_P = 5; // 1.2 //10
	//public static final double VERT_GAIN_F = 1;
	//public static final double VERT_GAIN_P = 8; // 1.2
	public static final double VERT_GAIN_I = 0;	
	public static final double VERT_GAIN_D = 0;
	public static final double VERT_RAMPRATE = 1;
	public static final int BOOM_PIDF_PROFILE = 0;
	public static final double BOOM_GAIN_F = 1;
	public static final double BOOM_GAIN_P = 5; // 1.2 // 10
	public static final double BOOM_GAIN_I = 0;	
	public static final double BOOM_GAIN_D = 0;
	public static final double BOOM_RAMPRATE = 1;//0.5;
	public static final double MAX_ENC_BOOM = 1501; 
	public static final double MAX_ENC_VERT = 700; // these are for testing purposes

	public static final double BOOM_PROP_GAIN = 8;
	public static final double BOOM_INT_GAIN = 0.3;
	double boom_int_term = 0;
	
	public static final double VERT_PROP_GAIN = 8;
	public static final double VERT_INT_GAIN = .3;
	double vert_int_term = 0;
	
	//public static final double MAX_ENC_COUNTS = 5461; //8192;
	public static final double MAX_ENC_COUNTS = 4096; //8192;
	
	public static final double VERT_LENGTH = 0;
	public static final double BOOM_LENGTH = 0;
	
	public static final double BOOM_OFFSET = 37;
	public static final double VERT_OFFSET = 90; //50 or 40 // may change it from 90 to mess with angles 10 degree ooffset
	
	public static double VERT_ZERO = 0;
	public static double BOOM_ZERO = 0;

	public static double xe = 0;
	public static double ye = 0;
	
	public double boom_angle = 0;
	public double vert_angle = 0;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public DartActuator() {
		//System.out.println("trololololollololllololololololololollol");
		
		vertMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, TIMEOUT_MS);
		boomMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, TIMEOUT_MS);
		
		vertMotor.set(ControlMode.Position, 0);
		boomMotor.set(ControlMode.Position, 0);
		
		vertMotor.setSensorPhase(true);
		boomMotor.setSensorPhase(false);
		
		 //vertMotor.setInverted(false);
		 //boomMotor.setInverted(true);
		 
		 vertMotor.configNominalOutputForward(0,TIMEOUT_MS);
		 vertMotor.configNominalOutputReverse(0,TIMEOUT_MS);
		 vertMotor.configPeakOutputForward(1,TIMEOUT_MS);
		 vertMotor.configPeakOutputReverse(-1,TIMEOUT_MS);
		 
		 boomMotor.configNominalOutputForward(0,TIMEOUT_MS);
		 boomMotor.configNominalOutputReverse(0,TIMEOUT_MS);
		 boomMotor.configPeakOutputForward(1,TIMEOUT_MS);
		 boomMotor.configPeakOutputReverse(-1,TIMEOUT_MS);
		 
		 vertMotor.configNeutralDeadband(0.001, TIMEOUT_MS);
		 boomMotor.configNeutralDeadband(0.001, TIMEOUT_MS);

		 vertMotor.config_kF(0,VERT_GAIN_F,TIMEOUT_MS);
		 vertMotor.config_kP(0,VERT_GAIN_P,TIMEOUT_MS);
		 vertMotor.config_kI(0,VERT_GAIN_I,TIMEOUT_MS);
		 vertMotor.config_kD(0,VERT_GAIN_D,TIMEOUT_MS);
		 vertMotor.configClosedloopRamp(VERT_RAMPRATE, TIMEOUT_MS);
		 
		 boomMotor.config_kF(0,BOOM_GAIN_F,TIMEOUT_MS);
		 boomMotor.config_kP(0,BOOM_GAIN_P,TIMEOUT_MS);
		 boomMotor.config_kI(0,BOOM_GAIN_I,TIMEOUT_MS);
		 boomMotor.config_kD(0,BOOM_GAIN_D,TIMEOUT_MS);
		 boomMotor.configClosedloopRamp(BOOM_RAMPRATE, TIMEOUT_MS);
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DartMove());
    }
    
    public void reverseSensorPhase() { //for use only in autonomous
    	vertMotor.setSensorPhase(false);
		boomMotor.setSensorPhase(true);
    }
    
    public void revertSensorPhase() { //for use in teleop
    	vertMotor.setSensorPhase(true);
		boomMotor.setSensorPhase(false);
    }
    /*
    public boolean isMoving() {
    	return (JOYSTICK.getRawAxis(BOOM_THROTTLE) > 0.2) || (JOYSTICK.getRawAxis(VERT_THROTTLE));
    }
    /*
    public void invertActuators() { //for use in teleop
    	vertMotor.setInverted(false);
		 boomMotor.setInverted(true);
    }
    */
    
    public static double getBoomEncoder() {
    	return(boomMotor.getSensorCollection().getQuadraturePosition());
    }
    
    public static double getVertEncoder() {
    	return(vertMotor.getSensorCollection().getQuadraturePosition());
    }
    
    public static double getBoomEncoderGivenAngle(double boom_angle) {
    	double new_angle = boom_angle - BOOM_OFFSET;
    	double boom_enc = (new_angle * MAX_ENC_COUNTS) / 360;
    	return(boom_enc);
    }
    
    public static double getVertEncoderGivenAngle(double vert_angle) {
    	double new_angle = vert_angle - VERT_OFFSET;
    	double vert_enc = (new_angle * MAX_ENC_COUNTS) / 360;
    	return(vert_enc);
    }
    
    // This func returns encoder angle in degrees of the boom arm
    public static double getBoomEncoderAngleinDegrees() {
    	double angle_offset = 37;
    // This func returns encoder angle in degrees of the boom arm
    	double boom_enc = Math.abs(getBoomEncoder());
    	double angle = (boom_enc *360) / MAX_ENC_COUNTS;
    	SmartDashboard.putNumber("boom angle", angle + angle_offset);
    	return(angle + angle_offset); 
    }
    
    // This func returns encoder angle in degrees of the vertical arm
    public static double getVertEncoderAngleinDegrees() {
    	double angle_offset = 90; //50 or 40
    	double vert_enc = getVertEncoder();
    	double angle = (vert_enc *360) / MAX_ENC_COUNTS;
    	SmartDashboard.putNumber("vert angle", angle + angle_offset);
    	return(angle + angle_offset);
    }
    
	public static double[] getEndpoint(double boom_angle, double vert_angle) {
		// boom_angle and vert_angle need to be in degrees
		// boom_angle = phi
		// vert_angle = gamma
		double a = 1.011; // in Meters //40.5 - in inches // vert arm length
		double b = 1.13; // in Meters //45 - in inches // boom arm length
		//double a = 1.0287; // in Meters //40.5 - in inches // vert arm length
		//double b = 1.143; // in Meters //45 - in inches // boom arm length
		double c = Math.sqrt( // whatever is left in order to form a right triangle
				    (a * a) +//Math.pow(a, 2) + 
				    (b * b) -//Math.pow(b, 2) - 
				    (2 * a * b) * Math.cos(boom_angle * (Math.PI / 180.0))
				   );
		double theta = Math.acos(
				        //(Math.pow(a, 2) + Math.pow(c, 2) - Math.pow(b, 2))
				        ((a*a) + (c*c) - (b*b))
				        / (2 * a * c)) * (180.0/Math.PI);
		SmartDashboard.putNumber("vert angle ting", vert_angle);
		double epsilon = (vert_angle - theta);
		SmartDashboard.putNumber("c", c);
		SmartDashboard.putNumber("theta", theta);
		SmartDashboard.putNumber("epsilon", epsilon);
		xe = c * Math.cos(epsilon * (Math.PI / 180.0));
		ye = c * Math.sin(epsilon * (Math.PI / 180.0));
		double[] xeye = new double[2];
		xeye[0] = xe;
		xeye[1] = ye;
		SmartDashboard.putNumber("xe", xe);
		SmartDashboard.putNumber("ye", ye);
		return(xeye);
	}
	
	public static double[] getAngles(double xe, double ye) {
		double a = 1.011; // in meters
		double b = 1.13; // in meters
		double c = Math.sqrt((a * a) + (b * b));
		double epsilon = Math.atan(ye / xe);
		double phi = Math.acos(((a*a) + (b*b) - (c*c)) / (2 * a * b));
		double theta = Math.acos(((a*a) + (c*c) - (b*b)) / (2 * a * c));
		double vert_angle = theta + epsilon;
		double boom_angle = phi;
		
		double[] BoomVert = new double[2];
		BoomVert[0] = boom_angle;
		BoomVert[1] = vert_angle;
		return(BoomVert);
	}
    
    public boolean isViolating() {
    	double boom_enc = getBoomEncoder();
    	double vert_enc = getVertEncoder();
    	    	
    	 boom_angle = getBoomEncoderAngleinDegrees();
    	 vert_angle = getVertEncoderAngleinDegrees();
    	
    	// get encoder counts to degrees
    	
    	//double xe = getEndpoint(boom_enc, vert_enc)[0];
    	//double ye = getEndpoint(boom_enc, vert_enc)[1];
    	
    	double[] endpoint = getEndpoint(boom_angle, vert_angle);
    	
    	xe = endpoint[0];
    	ye = endpoint[1];
    	/*
    	System.out.println("Boom Enc: " + Double.toString(boom_enc));
    	System.out.println("Vert Enc: " + Double.toString(vert_enc));
    	System.out.println("");
    	System.out.println("Boom Angle: " + Double.toString(boom_angle));
    	System.out.println("Vert Angle: " + Double.toString(vert_angle));
    	System.out.println("");
    	System.out.println("xe: " + Double.toString(xe));
    	System.out.println("ye: " + Double.toString(ye));
<<<<<<< HEAD
    	*/
    	if  (xe >= 1.02) { // 1.092 // 1.1 //1.1697 = new // fred: 1.0668 //comp : 1.114
    //	if  (xe >= 1.16) { // 1.092 // 1.1 //1.1697 = new
    		status = true;
    	} else {
    		status = false;
    	}
    	SmartDashboard.putBoolean("Is Violating?", status);
    	return status;
    }
    
    public void setVertPower(double power) {
        vertMotor.set(power);
    }
    
    public void setBoomPower(double power) {
        boomMotor.set(power);
    }
    
    
    public void setVertPosition(double pos) {
    	if (isViolating()) {
    		System.out.println("You've extended farther than 16 inches!");
    	} else {
        	vertMotor.set(ControlMode.Position, pos);
    	}
    }
    
    public void setBoomPosition(double pos) {
    	if (isViolating()) {
    		System.out.println("You've extended farther than 16 inches!");
    	} else {
        	boomMotor.set(ControlMode.Position, pos);  
    	}
    }
    
    public void setVertPositionTheSequel(double pos, double speed_limit) {
    	if (isViolating()) {
    		//System.out.println("You've extended farther than 16 inches!");
    	} else {
    		double vangle = getVertEncoderAngleinDegrees();
        	//vertMotor.set(ControlMode.Position, pos);
    		System.out.println("pos command: " + pos);
    		System.out.println("vangle: " + vangle);
        	//boomMotor.set(ControlMode.Position, pos);
    		double error = pos - vangle;
    		
    		if (Math.abs(error) < 2) {
        		vert_int_term += error;
        	}
        	if (Math.abs(error) > 5) {
        		vert_int_term = 0;
        	}

        	//System.out.println("Error: " + error);
        	double vert_pos_comm = ((error * VERT_PROP_GAIN) + (vert_int_term * VERT_INT_GAIN));
        	//System.out.println("vert: " + vert_pos_comm);
        	if (Math.abs(error) < 0.5) {
        		vert_pos_comm = 0;
        	}
        	if(vert_pos_comm > speed_limit) {
        		vert_pos_comm = speed_limit;
    	}
    	else if(vert_pos_comm < -speed_limit) {
    			vert_pos_comm = -speed_limit;
    	}
        	setVertSpeed(vert_pos_comm);
    	}
    }
    
    public void setBoomPositionTheSequel(double pos,double speed_limit) {
    	if (isViolating()) {
    		//System.out.println("You've extended farther than 16 inches!");
    	} else {
    		double bangle = getBoomEncoderAngleinDegrees();
    		System.out.println("pos command: " + pos);
    		System.out.println("bangle: " + bangle);
        	//boomMotor.set(ControlMode.Position, pos);
    		double error = pos - bangle;
    		
    		if (Math.abs(error) < 2) {
        		boom_int_term += error;
        	}
        	if (Math.abs(error) > 5) {
        		boom_int_term = 0;
        	}
        	
        	System.out.println("Error: " + error);
        	double boom_pos_comm = ((error * BOOM_PROP_GAIN) + (boom_int_term * BOOM_INT_GAIN));
        	System.out.println("boom: " + boom_pos_comm);
        	if (Math.abs(error) < 0.5) {
        		boom_pos_comm = 0;
        	}
        	if(boom_pos_comm > speed_limit) {
        			boom_pos_comm = speed_limit;
        	}
        	else if(boom_pos_comm < -speed_limit) {
        			boom_pos_comm = -speed_limit;
        	}
        	setBoomSpeed(boom_pos_comm);
    	}
    }
    
    public void setBoomRamp(double ramprate) {
		 boomMotor.configClosedloopRamp(ramprate, TIMEOUT_MS);
    }
    
    public void setVertRamp(double ramprate) {
    	vertMotor.configClosedloopRamp(ramprate, TIMEOUT_MS);
    }
    public void setBoomSpeed(double speed) {
    	boomMotor.set(ControlMode.Velocity, speed);
    }
    
    public void setVertSpeed(double speed) {
    	vertMotor.set(ControlMode.Velocity, speed);
    }
    
    public void stopAll() {
    	vertMotor.set(0);
    	boomMotor.set(0);
    	//vertMotor.setPower(0);
    	//boomMotor.setPower(0);
    }
    
    public void resetVertEncoder() {
		vertMotor.getSensorCollection().setQuadraturePosition(0, TIMEOUT_MS);
	}
    
    public void resetBoomEncoder() {
		boomMotor.getSensorCollection().setQuadraturePosition(0, TIMEOUT_MS);
	}
    
    public void resetEncoders() {
    	resetVertEncoder();
    	resetBoomEncoder();
    }
    
    public WPI_TalonSRX getVertMotor() {
    	return vertMotor;
    }
    
    public WPI_TalonSRX getBoomMotor() {
    	return boomMotor;
    }
    
    public double getVertArmEncoder() {
    	double vert = vertMotor.getSensorCollection().getQuadraturePosition();
    	SmartDashboard.putNumber("Vert Encoder", vert);
    	return vert;
    }
    
    public double getBoomArmEncoder() {
    	double boom = boomMotor.getSensorCollection().getQuadraturePosition();
    	SmartDashboard.putNumber("Boom Encoder", boom);
    	return boom;
    }
    
    public double getVertEndpointToAngle(double xe, double ye, double theta_bar) {
    	double c = Math.sqrt(Math.pow(xe, 2) + Math.pow(ye, 2));
    	double theta_junior = Math.acos((Math.pow(VERT_LENGTH, 2)-Math.pow(BOOM_LENGTH, 2)+(c * c))/(2*VERT_LENGTH*c));
    	double epsilon = Math.sin(ye/c);
    	double theta = theta_junior + epsilon;
    	return theta;
    }
    
    public double getBoomEndpointToAngle(double xe, double ye, double phi_bar) {
    	double c = Math.sqrt(Math.pow(xe, 2) + Math.pow(ye, 2));
    	double phi = Math.acos((Math.pow(VERT_LENGTH, 2)+Math.pow(BOOM_LENGTH, 2)-(c * c))/(2*VERT_LENGTH*BOOM_LENGTH));
    	return phi;
    }
    
    
    public void PIDTuneDart(double xe, double ye, double theta_bar, double phi_bar) {
    	double theta = getVertEndpointToAngle(xe, ye, theta_bar);
    	double phi = getBoomEndpointToAngle(xe, ye, phi_bar);
    	vertMotor.set(ControlMode.Position, theta);
    	boomMotor.set(ControlMode.Position, phi);
    }

    public double getTopExtensionLength() {
    	double rotations = RobotMap.toppot.get();
    	double in_per_rotation = 12/8.76; // 12 inches / 8.76 rotations
		double inches_extended = rotations * in_per_rotation;
		SmartDashboard.putNumber("Boom Dart extension", inches_extended);
    	return(inches_extended);
    }
    
    public double getBottomExtensionLength() {
    	double rotations = RobotMap.botpot.get();
    	double in_per_rotation = 12/8.76;
    	double inches_extended = rotations * in_per_rotation;
    	SmartDashboard.putNumber("Vert Dart extension", inches_extended);
    	return(inches_extended);
    }
    
    public boolean isTopFullyExtended() {
    	if(getTopExtensionLength() >= 12) {
    		return(true);
    	} else {
    		return(false);
    	}
    }
    
    public boolean isBottomFullyExtended() {
    	if(getBottomExtensionLength() >= 12) {
    		return(true);
    	} else {
    		return(false);
    	}
    }
}
