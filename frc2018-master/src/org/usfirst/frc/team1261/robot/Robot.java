/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1261.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import org.usfirst.frc.team1261.robot.subsystems.Climber;
import org.usfirst.frc.team1261.robot.subsystems.DartActuator;
import org.usfirst.frc.team1261.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1261.robot.commands.Auto180Turn;
import org.usfirst.frc.team1261.robot.commands.AutoAcquire;
import org.usfirst.frc.team1261.robot.commands.AutoClockwiseTurn;
import org.usfirst.frc.team1261.robot.commands.AutoCounterclockwiseTurn;
import org.usfirst.frc.team1261.robot.commands.AutoMove;
import org.usfirst.frc.team1261.robot.commands.AutoTurn;
import org.usfirst.frc.team1261.robot.commands.AutoMoveForward;
import org.usfirst.frc.team1261.robot.commands.AutoPath1;
import org.usfirst.frc.team1261.robot.commands.AutoPath10;
import org.usfirst.frc.team1261.robot.commands.AutoPath10Mirror;
import org.usfirst.frc.team1261.robot.commands.AutoPath11;
import org.usfirst.frc.team1261.robot.commands.AutoPath11Mirror;
import org.usfirst.frc.team1261.robot.commands.AutoPath13;
import org.usfirst.frc.team1261.robot.commands.AutoPath13Mirror;
import org.usfirst.frc.team1261.robot.commands.AutoPath1Mirror;
import org.usfirst.frc.team1261.robot.commands.AutoPath2;
import org.usfirst.frc.team1261.robot.commands.AutoPath2Mirror;
import org.usfirst.frc.team1261.robot.commands.AutoPath3;
import org.usfirst.frc.team1261.robot.commands.AutoPath3Mirror;
import org.usfirst.frc.team1261.robot.commands.AutoPath4;
import org.usfirst.frc.team1261.robot.commands.AutoPath4Mirror;
import org.usfirst.frc.team1261.robot.commands.AutoPath5;
import org.usfirst.frc.team1261.robot.commands.AutoPath5Mirror;
import org.usfirst.frc.team1261.robot.commands.AutoPath6;
import org.usfirst.frc.team1261.robot.commands.AutoPath6Mirror;
import org.usfirst.frc.team1261.robot.commands.AutoPath7;
import org.usfirst.frc.team1261.robot.commands.AutoPath7Mirror;
import org.usfirst.frc.team1261.robot.commands.AutoPath8;
import org.usfirst.frc.team1261.robot.commands.AutoPath8Mirror;
import org.usfirst.frc.team1261.robot.commands.AutoPath9;
import org.usfirst.frc.team1261.robot.commands.AutoPath9Mirror;
import org.usfirst.frc.team1261.robot.commands.AutoSwitch;
import org.usfirst.frc.team1261.robot.commands.Boxy;
import org.usfirst.frc.team1261.robot.commands.EncoderReset;
import org.usfirst.frc.team1261.robot.commands.JoystickDrive;
//import org.usfirst.frc.team1261.robot.commands.AcquireCube;
import org.usfirst.frc.team1261.robot.subsystems.DriveTrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	
	Thread visionThread;
	
	public static String status;
	
	public static final DriveTrain driveTrain = new DriveTrain();
	public static OI m_oi; // M'oi. *tips fedora*
	public static Compressor c;
	
	public static final Climber climber = new Climber();
	public static final DartActuator actuator = new DartActuator();
	public static final Pneumatics manipulator = new Pneumatics();
	public static double limelight_x;
	public static double limelight_y;
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	
	private static int ROBOT_ID = 2;
	
	SendableChooser<Command> limechooser = new SendableChooser<>();
	
	NetworkTable cameraTable;
	NetworkTableEntry tx;
	NetworkTableEntry ty;
	NetworkTableEntry ta;
	
	
	public static int getRobotId() {
    	ROBOT_ID = Preferences.getInstance().getInt("RobotID", 2);
    	SmartDashboard.putNumber("Robot ID: ", ROBOT_ID);
    	return (ROBOT_ID);
    }

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotPeriodic() {
		//double boom = Robot.actuator.getBoomEncoderAngleinDegrees();
		//double vert = Robot.actuator.getVertEncoderAngleinDegrees();
		//Robot.actuator.getEndpoint(boom, vert);
		SmartDashboard.putBoolean("Vert Inverted", Robot.actuator.vertMotor.getInverted());
		SmartDashboard.putBoolean("Boom Inverted", Robot.actuator.boomMotor.getInverted());
		
		Robot.driveTrain.getLeftEncoderPosition();
		Robot.driveTrain.getRightEncoderPosition();
		Robot.driveTrain.getLeftEncoderVelocity();
		Robot.driveTrain.getRightEncoderVelocity();
		
		Robot.driveTrain.getLeftEncoderPositioninMeters();
		Robot.driveTrain.getRightEncoderPositioninMeters();
		Robot.driveTrain.getLeftEncoderVelocityinMetersperSec();
		Robot.driveTrain.getRightEncoderVelocityinMetersperSec();
		
		// These functions populate the SmartDashboard
		
		Robot.driveTrain.getRPH();
		
		Robot.actuator.getVertArmEncoder();
		Robot.actuator.getBoomArmEncoder();
		Robot.actuator.getBottomExtensionLength();
		Robot.actuator.isViolating();
	}
	
	public void robotInit() {
		//Robot.driveTrain.resetEncoders();
		c = new Compressor(10);
		c.setClosedLoopControl(true);
		//c.setClosedLoopControl(false);
		m_oi = new OI();
		m_chooser.addDefault("Forward (all positions)", new AutoMoveForward());
		//m_chooser.addDefault("Clockwise turn", new AutoClockwiseTurn());
		//m_chooser.addDefault("Counterclockwise turn", new AutoCounterclockwiseTurn());
		m_chooser.addObject("Left Start, Left Switch, Middle Side", new AutoPath1(true));
		m_chooser.addObject("Right Start, Right Switch, Middle Side", new AutoPath1Mirror(true));
		m_chooser.addObject("Left Start, Left Switch, Back Side", new AutoPath2(true));
		m_chooser.addObject("Right Start, Right Switch, Back Side", new AutoPath2Mirror(true));
		m_chooser.addObject("Left Start, Right Switch, Back Side", new AutoPath3(true));
		m_chooser.addObject("Right Start, Left Switch, Back Side", new AutoPath3Mirror(true));
		m_chooser.addObject("Left Start, Right Switch, Middle Side", new AutoPath4(true));
		m_chooser.addObject("Right Start, Left Switch, Middle Side", new AutoPath4Mirror(true));
		m_chooser.addObject("Center Start, Left Switch, Front Side", new AutoPath5(true));
		m_chooser.addObject("Center Start, Right Switch, Front Side", new AutoPath5Mirror(true));
		m_chooser.addObject("Center Start, Left Switch, Back Side", new AutoPath6(true));
		m_chooser.addObject("Center Start, Right Switch, Back Side", new AutoPath6Mirror(true));
		m_chooser.addObject("Center Start, Left Switch, Middle Side", new AutoPath7(true));
		m_chooser.addObject("Center Start, Right Switch, Middle Side", new AutoPath7Mirror(true));
		m_chooser.addObject("Left Start, Left Switch, Front Side", new AutoPath8(true));
		m_chooser.addObject("Right Start, Right Switch, Front Side", new AutoPath8Mirror(true));
		m_chooser.addObject("Left Start, Right Switch, Front Side", new AutoPath9(true));
		m_chooser.addObject("Right Start, Left Switch, Front Side", new AutoPath9Mirror(true));
		m_chooser.addObject("Left Start, Left Scale", new AutoPath10(true));
		m_chooser.addObject("Right Start, Right Scale", new AutoPath10Mirror(true));
		m_chooser.addObject("Left Start, Right Scale", new AutoPath11(true));
		m_chooser.addObject("Right Start, Left Scale", new AutoPath11Mirror(true));
		m_chooser.addObject("Left 2-scale", new AutoPath13());
		m_chooser.addObject("Right 2-scale", new AutoPath13Mirror());
		m_chooser.addObject("Box", new Boxy());
		m_chooser.addObject("Reset", new EncoderReset());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		
		//visionThread = new Thread(() -> {
			// Get the UsbCamera from CameraServer
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture("cam0", "/dev/video0");
			// Set the resolution
			camera.setResolution(320,240);
			camera.setFPS(5);

			// Get a CvSink. This will capture Mats from the camera
			//CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			//CvSource outputStream
					//= CameraServer.getInstance().putVideo("Gray", 640, 480);

			
			// Mats are very memory expensive. Lets reuse this Mat.
			//Mat mat = new Mat();
/*
			// This cannot be 'true'. The program will never exit if it is. This
			// lets the robot stop this thread when restarting robot code or
			// deploying.
			while (!Thread.interrupted()) {
				// Tell the CvSink to grab a frame from the camera and put it
				// in the source mat.  If there is an error notify the output.
				 *//*
				if (cvSink.grabFrame(mat) == 0) {
					// Send the output the error.
					outputStream.notifyError(cvSink.getError());
//					// skip the rest of the current iteration
//					continue;
				}
				// Put a rectangle on the image
				Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
						new Scalar(255, 255, 255), 5);
				// Give the output stream a new image to display
				outputStream.putFrame(mat);
				/*
			}
		});
		visionThread.setDaemon(true);
		visionThread.start();
		*/
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//Robot.actuator.boomMotor.setInverted(true);
		//Robot.actuator.reverseSensorPhase();
		Robot.actuator.resetEncoders();
		m_autonomousCommand = m_chooser.getSelected();
		String autoName = m_autonomousCommand.getClass().getName();
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		//gameData = "Left"; //place holder
		//System.out.println(gameData);
		//System.out.println(autoName);
		/*
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("There was an error with the thread and sleeping at line 260 on Robot.java");
		} // 1000
		*/
        if(gameData.length() > 0) {
		  if(gameData.charAt(0) == 'L')
		  {
			  if (autoName.equals(new AutoPath1Mirror(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath4Mirror(true);
			  } else if (autoName.equals(new AutoPath2Mirror(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath3Mirror(true);
			  } else if (autoName.equals(new AutoPath3Mirror(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath2Mirror(true);
			  } else if (autoName.equals(new AutoPath4(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath1(true);
			  } else if (autoName.equals(new AutoPath5Mirror(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath5(true);
			  } else if (autoName.equals(new AutoPath6Mirror(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath6(true);
			  } else if (autoName.equals(new AutoPath7Mirror(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath7(true);
			  } else if (autoName.equals(new AutoPath8Mirror(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath9Mirror(true);
			  } else if (autoName.equals(new AutoPath9Mirror(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath8Mirror(true);
			  }
			//Put left auto code here
		  } else {
			  if (autoName.equals(new AutoPath1(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath4(true);
			  } else if (autoName.equals(new AutoPath2(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath3(true);
			  } else if (autoName.equals(new AutoPath3(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath2(true);
			  } else if (autoName.equals(new AutoPath4Mirror(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath1Mirror(true);
			  } else if (autoName.equals(new AutoPath5(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath5Mirror(true);
			  } else if (autoName.equals(new AutoPath6(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath6Mirror(true);
			  } else if (autoName.equals(new AutoPath7(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath7Mirror(true);
			  } else if (autoName.equals(new AutoPath8(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath9(true);
			  } else if (autoName.equals(new AutoPath9(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath8(true);
			  } 
			//Put right auto code here
		  }
		  if(gameData.charAt(1) == 'L') {
			  if (autoName.equals(new AutoPath10Mirror(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath11Mirror(true);
			  } else if (autoName.equals(new AutoPath11(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath10(true);
			  } else if (autoName.equals(new AutoPath13Mirror().getClass().getName())) {
				  m_autonomousCommand = new AutoPath11Mirror(true);
			  }
		  }
		  else {
			  if (autoName.equals(new AutoPath11Mirror(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath10Mirror(true);
			  } else if (autoName.equals(new AutoPath10(false).getClass().getName())) {
				  m_autonomousCommand = new AutoPath11(true);
			  } else if (autoName.equals(new AutoPath13().getClass().getName())) {
				  m_autonomousCommand = new AutoPath10Mirror(true);
			  }
		  }
       }
        else {
        	m_autonomousCommand = new AutoMoveForward();
       }
      
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();// cast possibly into one line of code for fuctionality
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		// SmartDashboard.putString("status: ", status);
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		//Robot.actuator.boomMotor.setInverted(false);
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		//Robot.actuator.revertSensorPhase();
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {

		 Scheduler.getInstance().run();
		
		 //code for limelight vision and data
		cameraTable = NetworkTableInstance.getDefault().getTable("limelight");
		NetworkTableEntry tx = cameraTable.getEntry("tx");
		NetworkTableEntry ty = cameraTable.getEntry("ty");
		NetworkTableEntry ta = cameraTable.getEntry("tz");
		
		limelight_x = (Double) tx.getDouble(0);
		limelight_y = (Double) ty.getDouble(0);
		//double area = (Double) ta.getDouble(0);
		
		SmartDashboard.putNumber("limelight x", limelight_x);
		SmartDashboard.putNumber("limelight y", limelight_y);
		//SmartDashboard.putNumber("limelight area", area);
		
		cameraTable.getEntry("ledMode").setNumber(1);
		cameraTable.getEntry("camMode").setNumber(0);
		
		
		/*
		limechooser = new SendableChooser();
		limechooser.addObject("Limelight LED Off", new LimelightLedOff());
		limechooser.addObject("Limelight LED On", new LimelightLedOff());
		SmartDashboard.putData("Limelight Chooser", limechooser);
		*/

		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}

