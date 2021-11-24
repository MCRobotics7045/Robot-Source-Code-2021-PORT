/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.Constants;

//import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.PWMVictorSPX;

/**
 * Add your docs here.
 */
public class Intake extends SubsystemBase {
  private final PWMVictorSPX intakeMotor = new PWMVictorSPX(Constants.MOTOR_INTAKE_PORT);
 
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Intake(){
    super();

    //LiveWindow
    //addChild("Intake Motor", intakeMotor);
    Shuffleboard.selectTab("Shooter");
    Shuffleboard.getTab("Shooter").add("Intake Motor", intakeMotor)
        .withPosition(1,2);
  }

  //Start the intake motor
  public void start() {
    intakeMotor.set(Constants.kIntakeSpeed);
  }

 //Reverse the intake motor
  public void reverse() {
    intakeMotor.set(-Constants.kIntakeSpeed);
  }

 
 // Stops the intake motor

  public void stop() {
    intakeMotor.set(0);
  
  }

  public boolean isRunning() {
    double speed = intakeMotor.getSpeed();
    if (Math.abs(speed)> 0){
      return true;
    }
    else{
      return false;
    }
  }
}



 