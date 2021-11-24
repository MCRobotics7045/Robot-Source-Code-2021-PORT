/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;



import frc.robot.Constants;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
//import com.ctre.phoenix.motorcontrol.can.VictorSPX;

/**
 * Add your docs here.
 */
public class Shooter extends PIDSubsystem {
  private final PWMVictorSPX shooterMotor = new PWMVictorSPX(Constants.MOTOR_SHOOTER_PORT);
  private final Encoder shooterEncoder = new Encoder(Constants.SHOOTER_ENCODER_DIO_PORT_A, Constants.SHOOTER_ENCODER_DIO_PORT_B);
  private final SimpleMotorFeedforward shooterFeedforward =
                      new SimpleMotorFeedforward(Constants.kSVolts,
                                                Constants.kVVoltSecondsPerRotation);

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Shooter(){
    //super();
    super(new PIDController(Constants.kShooter_P, Constants.kShooter_I, Constants.kShooter_D));
    getController().setTolerance(Constants.kShooterToleranceRPS);
    shooterEncoder.setDistancePerPulse(Constants.kShooterEncoderDistancePerPulse);
    setSetpoint(Constants.kShooterTargetRPS);

    //LiveWindow
    Shuffleboard.selectTab("Shooter");
    Shuffleboard.getTab("Shooter").add("Shooter Motor", shooterMotor)
        .withPosition(1,0);
    //Shuffleboard.getTab("Shooter").add("Shooter Encoder", shooterEncoder)
    //    .withPosition(1,1);
    //Shuffleboard.getTab("Shooter").add("Controller", m_controller)
    //    .withPosition(0,3);

  }

   //Start the intake motor //TEST
   public void start() {
    shooterMotor.set(1);
  }
  @Override
  public void useOutput(double output, double setpoint) {
    shooterMotor.setVoltage(output + shooterFeedforward.calculate(setpoint));

    //System.out.printf("Output: %.3f   newset: %.3f     getRate: %.2f    AtSet: %b", output, output + shooterFeedforward.calculate(setpoint), shooterEncoder.getRate(), m_controller.atSetpoint());
    //System.out.println();
    
  }
  @Override
  public double getMeasurement() {
   
    return shooterEncoder.getRate();
  }

  public boolean atSetpoint() {

    
    return m_controller.atSetpoint();
  }

  
 // Stops the Shooter motor

  public void stop() {
    shooterMotor.set(0);
  }

  /**
   * Return true when the shooter motor is running
   */
  public boolean isRunning() {
    
    if (Math.abs(shooterMotor.getSpeed()) > 0.0){  
      return true;
    }
    else{
      return false;
    }
  }
}



 