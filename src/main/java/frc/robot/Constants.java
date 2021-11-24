/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
//PWM Ports
	public static final int MOTOR_LEFT_FRONT_PORT = 0;     //C&B
    public static final int MOTOR_LEFT_REAR_PORT = 1;
    public static final int MOTOR_RIGHT_FRONT_PORT = 2;    //E&F
    public static final int MOTOR_RIGHT_REAR_PORT = 3;
    public static final int MOTOR_INTAKE_PORT = 4;         //D
    public static final int MOTOR_INDEXER_PORT = 5;        //H
    public static final int MOTOR_SHOOTER_PORT = 6;        //A
    public static final int MOTOR_COLOR_WHEEL_PORT = 7;
    public static final int MOTOR_DART_PORT = 8;

    // ANalog INput
    public static final int DART_POT_PORT = 3;
    public static final double kDartOutVoltage = 2.92; // Aprox 9-in extension
    public static final double kDartInVoltage = 3.5; //Approx 13-in extension
//Joystick Buttons 
    public static final int JOYSTICK_BUTTON_SHOOT = 1;
	public static final int JOYSTICK_BUTTON_AIM = 2;
	public static final int JOYSTICK_BUTTON_INTAKE_FWD = 7;
    public static final int JOYSTICK_BUTTON_INTAKE_REV = 8;
    public static final int JOYSTICK_BUTTON_INDEXER_ADV = 3;
    public static final int JOYSTICK_BUTTON_INDEXER_REV = 5;
    public static final int JOYSTICK_BUTTON_LOAD_MAGAZINE = 4;
    public static final int JOYSTICK_BUTTON_SHOOTER_MOTOR = 11;
    public static final int JOYSTICK_BUTTON_COLOR_WHEEL_AUTO = 12;
    public static final int JOYSTICK_BUTTON_DART_UP = 9;
    public static final int JOYSTICK_BUTTON_DART_DOWN = 10;

 //XBOX Buttons
    public static final int joystickRedButton = 2;
    public static final int joystickYellowButton = 4;
    public static final int joystickGreenButton = 1;
    public static final int joystickBlueButton = 3;
    public static final int joystickLBButton = 5;
    public static final int joystickRBButton = 6;   

//DIO Ports
   //public static final int ENCODER1_DIO_PORT_A = 0;
   //public static final int ENCODER1_DIO_PORT_B = 1;
   // public static final int DART_UPPER_LIMIT_DIO_PORT = 1;
   //public static final int DART_LOWER_LIMIT_DIO_PORT = 0;

   public static final int SHOOTER_ENCODER_DIO_PORT_A = 2;
   public static final int SHOOTER_ENCODER_DIO_PORT_B = 3;
   public static final int CW_ENCODER1_DIO_PORT_A = 4;
   public static final int CW_ENCODER1_DIO_PORT_B = 5;

   public static final int SWITCH_S1_SHOOTER_DIO_PORT = 9;
   public static final int SWITCH_S2_MID_INDEX_DIO_PORT = 8;
   public static final int SWITCH_S3_START_INDEX_DIO_PORT = 7;
   public static final int SWITCH_S4_INTAKE_DIO_PORT = 6;

//USB Ports
    public static final int JOYSTICK_PORT = 0;
    public static final int XBOX_CONTROLLER_PORT = 1;
    
//Drive Motor Related Constants
    public static final double kMoveLimit = 1.0; //Maximum Move Overide Limit
    public static final double kTurnLimit = 1.0; //Maximum Turn Overide Limit

    public static final double kTurnScaling = 0.8;  //Scale value for turns

    public static final double kMoveMinThreshold = 0.1; 
    public static final double kTurnMinThreshold = 0.1;
//Targeting Settings
    public static final double kTargetMove = 0.05; //scaling constant
    public static final double kTargetMoveMax = 0.6;
    public static final double kTargetMoveMinThreshold = 0.4; 

    public static final double kTargetTurn = 0.03; //scaling constant
    public static final double kTargetTurnMax = 0.4; //Squared result(~.16) Max turn speed during target acquisition
    public static final double kTargetTurnMinThreshold = 0.3;
    

    public static final double kMinXTargetOffset = 2.5; //Min offset angle to be on target
    public static final double kMinYTargetOffset = 0.5; //Min offset angle to be on target
    public static final boolean kDebug = true;
    
//Intake Motor Settings
    public static final double kIntakeSpeed = 0.5; //Speed of Intake Motor

//Indexer  Encoder

    public static final double kIndexerSpeed = 0.4; //Speed of the Indexer Motor
    public static final double kEncRevolutions = 1.0-kIndexerSpeed/10.0; //# of revolution
	public static final double kEncPPR = 7.0; //ENcoder pulses per revolution
    public static final double kEncGearRatio = 27.0; //kEnc*number of revolutions
    
    public static final double kMaxMagazineBalls = 4;
    public static final int kInitMagazineBalls = 3;

//Shooter Constants
   //PID Values
   public static final double kShooter_P = .1;
   public static final double kShooter_I = 0;
   public static final double kShooter_D = 0.;

   public static final double kShooterTargetRPS = 4500/60; //convert RPM to RPS to set Target
   public static final double kShooterToleranceRPS = 200/60;
   public static final int kShooterEncoderPPR = 20; //20 pulses per channel per revolution (CIM Encoder)
   public static final double kShooterEncoderDistancePerPulse =   1.0 / (double) kShooterEncoderPPR;       // Distance units will be rotations
   public static final double kShooterFreeRPS = 5310/60; //Maximum free spin
   public static final double kSVolts = .05;
   public static final double kVVoltSecondsPerRotation = 1.3 * 12.0 / kShooterFreeRPS; // Should have value 12V at free speed...
   public static final double kShooterMotorRampDown=1.0;//number of seconds to ramp down after last ball

 //Color Wheel Constants
 public static final double kColorWheelSpeed = 1.0; //Speed of the Color WHeel Motor
 public static final double kColorWheelDiameter = 3.0; //Diameter of compliant wheel installed on Color WHeel motor
 public static final double kFRCColorWheelDiameter = 32.0; //Diameter of FRC color wheel at competition

 public static final double kColorWheelEncRevolutions = 3.5*(kFRCColorWheelDiameter/kColorWheelDiameter); //# of revolution of CW Motor to spin FRC wheel  times.
 //public static final double kColorWheelEncRevolutions = 4.25; //TESTING, replace with above
 public static final double kColorWheelEncPPR = 7.0; //ENcoder pulses per revolution
 public static final double kColorWheelEncGearRatio = 27.0; //kEnc*number of revolutions
 

    



	
	
	
    
}
