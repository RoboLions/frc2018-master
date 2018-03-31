package org.usfirst.frc.team1261.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc.team1261.robot.RobotMap;
import org.usfirst.frc.team1261.robot.commands.Climb;

/**
 *
 */
public class Climber extends Subsystem {
	
	//public WPI_TalonSRX climbmotor = RobotMap.climbMotor;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new Climb());
    }
    
    public void setClimbPower(double power) {
    	//climbmotor.set(-1*Math.abs(power)); // makes it so that power is negative no matter what
    }
    
    public void stop() {
    	setClimbPower(0);
    }
    /*
    public WPI_TalonSRX getClimbMotor() {
    	return climbmotor;
    }
    */
}

