/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
//import frc.robot.commands.IndexerCommand;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
//import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
//import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
//import edu.wpi.cscore.HttpCamera;
//import edu.wpi.cscore.UsbCamera;


import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;

/**
 * Add your docs here.
 */
public class Indexer extends SubsystemBase {
  private final PWMVictorSPX indexerMotor = new PWMVictorSPX(Constants.MOTOR_INDEXER_PORT);
  //private final Encoder encoder = new Encoder(Constants.ENCODER1_DIO_PORT_A, Constants.ENCODER1_DIO_PORT_B);

  //Light Beam Switches are TRUE when no ball is present
  public DigitalInput swShooter = new DigitalInput(Constants.SWITCH_S1_SHOOTER_DIO_PORT);
  public DigitalInput swMidIndex = new DigitalInput(Constants.SWITCH_S2_MID_INDEX_DIO_PORT);
  //public DigitalInput swStartIndex = new DigitalInput(Constants.SWITCH_S3_START_INDEX_DIO_PORT);
  public DigitalInput swIntake = new DigitalInput(Constants.SWITCH_S4_INTAKE_DIO_PORT);

  //public static Counter cellCount = new Counter(); //Robot initiall loaded with 3 power cells.
  public static int balls = Constants.kInitMagazineBalls;

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Indexer(){
    super();

    //LiveWindow
    //addChild("Indexer Motor", indexerMotor);
    Shuffleboard.selectTab("Shooter");
    Shuffleboard.getTab("Shooter").add("Indexer Motor", indexerMotor)
        .withPosition(1,1);
    //Shuffleboard.getTab("Shooter").add("Indexer Encoder", encoder)
    //    .withPosition(1,3);
    Shuffleboard.getTab("Shooter").add("Switch-Shooter", swShooter)
        //.withWidget(BuiltInWidgets.kTextView)
        .withPosition(0,2);
    Shuffleboard.getTab("Shooter").add("Switch-Mid", swMidIndex)
        //.withWidget(BuiltInWidgets.kTextView)
        .withPosition(0,1);//Shuffleboard.getTab("Shooter").add("Switch-Start", swStartIndex);
    Shuffleboard.getTab("Shooter").add("Switch-Intake", swIntake)
        //.withWidget(BuiltInWidgets.kTextView)
        .withPosition(0,0);
    //Shuffleboard.getTab("Shooter").add("Limelight", new HttpCamera("Limelight", "http://10.18.95.11:5800"))
    //.withWidget(BuiltInWidgets.kCameraStream)
    //.withPosition(3, 0)
    //.withSize(3, 3);
   
  }

  public void init(){
  // Configures the encoder's distance-per-pulse
  // There are 27*7 pulses per output shaft rotation
    //encoder.setDistancePerPulse(1/(Constants.kEncGearRatio*Constants.kEncPPR));
    //encoder.reset();
    //System.out.println("IndexerEncoder-INIT");
  }

  //Start the Indexer motor for number of revolutions
  
  /*public void startRevolutions() {
    SmartDashboard.putNumber("Encoder", encoder.getDistance());

    if (encoder.getDistance() < Constants.kEncRevolutions){
      indexerMotor.set(Constants.kIndexerSpeed);
      System.out.print("IndexerEncoder-Run  EncoderDistance: ");
      System.out.println(encoder.getDistance());
    }
    else{
      stop();
    }
  
  }
*/


  //Start the Indexer
  public void start() {
      indexerMotor.set(Constants.kIndexerSpeed);      
  }

 //Reverse the Indexer motor
  public void reverse() {
    indexerMotor.set(-Constants.kIndexerSpeed);
  }

 
 // Stops the Indexer motor

  public void stop() {
    indexerMotor.set(0);
    //System.out.print("IndexerEncoder-STOPPED  EncoderDistance: ");
    //System.out.println(encoder.getDistance());
  }

  /**
   * Return true when the indexer motor is running
   */
  public boolean isRunning() {
    //double speed = indexerMotor.getSpeed();
    //if (Math.abs(speed)> 0){
    //if (encoder.getDistance() < Constants.kEncRevolutions){  
    if (Math.abs(indexerMotor.getSpeed()) > 0.0){  
      return true;}
    else{
      return false;
    }
  }

  public boolean isMagFUll() {
    if (balls==Constants.kMaxMagazineBalls){
      return true;
    }
    else{
      return false;}
    }
    
  public boolean isMagEMPTY() {
    if (balls==0){
      return true;
    }
    else{
      return false;}
    }
  
  public boolean isShooterPrimed(){
    if (swShooter.get()==true){ //if true, there is no ball
      return false;}
    else{
      return true;}
    }
  
}



 