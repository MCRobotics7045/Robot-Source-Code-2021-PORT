/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.Constants;

//import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.PWMVictorSPX;

/**
 * Add your docs here.
 */
public class DartSubsystem extends SubsystemBase {
  private final PWMVictorSPX dartMotor = new PWMVictorSPX(Constants.MOTOR_DART_PORT);
  //public DigitalInput upperLimit = new DigitalInput(Constants.DART_UPPER_LIMIT_DIO_PORT);
  //public DigitalInput lowerLimit = new DigitalInput(Constants.DART_LOWER_LIMIT_DIO_PORT);
  public AnalogInput dartPot = new AnalogInput(Constants.DART_POT_PORT);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public DartSubsystem(){
    super();
    
    //LiveWindow
    //addChild("Dart Motor", dartMotor);
    Shuffleboard.selectTab("Shooter");
    Shuffleboard.getTab("Shooter").add("Dart Motor", dartMotor);
  }
public void init(){

}
  //Start the dart motor
  public void up() {
    dartMotor.set(Constants.kIntakeSpeed);
  }

 //Reverse the dart motor
  public void down() {
    dartMotor.set(-Constants.kIntakeSpeed);
  }

 
 // Stops the intake motor

  public void stop() {
    dartMotor.set(0);
  
  }

  public boolean isRunning() {
    double speed = dartMotor.getSpeed();
    if (Math.abs(speed)> 0){
      return true;
    }
    else{
      return false;
    }
  }

  public double dartVoltage(){
    return dartPot.getVoltage();
  }
}



 