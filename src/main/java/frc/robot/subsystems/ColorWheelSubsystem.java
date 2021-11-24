/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.util.Color;


public class ColorWheelSubsystem extends SubsystemBase {
  //private Spark wheelSpark = new Spark(Constants.MOTOR_COLOR_WHEEL_PORT);
  private PWMVictorSPX wheelSpark = new PWMVictorSPX(Constants.MOTOR_COLOR_WHEEL_PORT);
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  public ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  private final Encoder encoder = new Encoder(Constants.CW_ENCODER1_DIO_PORT_A, Constants.CW_ENCODER1_DIO_PORT_B);
  private Color detectedColor2 = colorSensor.getColor();
 /**
   * Creates a new ColorWheel.
   */
  public ColorWheelSubsystem() {
    super();
    
 //Shuffleboard.getTab("Shooter").add("Color-Blue", detectedColor2.blue);
 //Shuffleboard.getTab("Shooter").add("Color-Green", detectedColor2.green);
 //Shuffleboard.getTab("Shooter").add("Color-Red", detectedColor2.red);

  }

  public void init(){
    // Configures the encoder's distance-per-pulse
    // There are 27*7 pulses per output shaft rotation
      encoder.setDistancePerPulse(1/(Constants.kColorWheelEncGearRatio*Constants.kColorWheelEncPPR));
      encoder.reset();
      System.out.println("ColorWheelEncoder-INIT");
    }
  
  public void wheel(double move) {

    if (Math.abs(move) < 0.10) {				
      move = 0;
    }
    wheelSpark.set(move);
  }
  
   //Start the color wheel motor
   public void start() {
    wheelSpark.set(Constants.kColorWheelSpeed);
    detectedColor2 = colorSensor.getColor();
    SmartDashboard.putNumber("Blue", detectedColor2.blue);
    SmartDashboard.putNumber("Green", detectedColor2.green);
    SmartDashboard.putNumber("Red", detectedColor2.red);

    
  }

 //Reverse the color wheel motor
  public void reverse() {
    wheelSpark.set(-Constants.kColorWheelSpeed);
  }
 //Start the ColorWheel motor for specifiedvnumber of revolutions
 public void startRevolutions() {
  SmartDashboard.putNumber("CW-Encoder", encoder.getDistance());

  if (encoder.getDistance() < Constants.kColorWheelEncRevolutions){
    wheelSpark.set(Constants.kColorWheelSpeed);
    System.out.print("CW-Encoder-Run  EncoderDistance: ");
    System.out.println(encoder.getDistance());
  }
  else{
    stop();
  }

}
  // Stops the color wheel motor

  public void stop() {
    wheelSpark.set(0);
  
  }

  public boolean isRunning() {
    //double speed = indexerMotor.getSpeed();
    //if (Math.abs(speed)> 0){
    //if (encoder.getDistance() < Constants.kEncRevolutions){  
    if (wheelSpark.getSpeed() > 0.0){  
      return true;}
    else{
      return false;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
