/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;


public class FireCommand extends CommandBase {
  private final Shooter m_shooter;
  private final Indexer m_indexer;
  private static boolean lastState;
  private static boolean currentState;
  private static boolean rampDownShooter;
  private static boolean nextBallReady;
  private double timeStamp;
  /**
   * Creates a new FireCommand.
   */
  public FireCommand(Indexer indexer, Shooter shooter) { 
       super();
    m_shooter = shooter;
    m_indexer = indexer;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
    addRequirements(indexer); 
   }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    lastState = m_indexer.swShooter.get();
    rampDownShooter = false;
    nextBallReady=false;
    System.out.print("Fire Power Cell Init CMD - Balls=");
    System.out.println(Indexer.balls);
}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    //m_shooter.enable();    //For use with PID    
   // m_shooter.start();
   // new WaitCommand(2);
    
    //FIX SETPOINT INSTEAD OF iSRUNNING
    //Starter Indexer if Shooter at speed, indexer not currently curring, and Ball at shooter
    if (m_shooter.isRunning() && m_indexer.swShooter.get()==false && m_indexer.isRunning()==false){
      m_indexer.start(); //start indexer to advance balls for shot
      System.out.print("Advance Ball for shot - Primed: ");
      System.out.println(m_indexer.swShooter.get());
    }

    //check for rising edge
    currentState = m_indexer.swShooter.get();
    if (lastState==false && currentState==true){
      //rising edge detected as ball was shot
      Indexer.balls = Indexer.balls - 1;
      //lastState = currentState;
      System.out.print("Rise edge Shooter CMD - Balls=");
      System.out.println(Indexer.balls);
      if (Indexer.balls==0) {
        timeStamp = Timer.getFPGATimestamp();
      }

    }
    
    //check for falling edge as ball comes up to shooter
    //currentState = m_indexer.swShooter.get();
    if (lastState==true && currentState==false){
      System.out.println("Falling Edge");
      //falling edge detected is new ball is primed
      if (m_indexer.isRunning())  {
        m_indexer.stop(); //stop Indexer once ball is primed for shot
        System.out.println("Ball Ready - Stop Indexer");
        nextBallReady = true;
      }
    }
    lastState = currentState;
 
    //After last ball enters shooter, keep shooter motor on for number of seconds.
    
    if (Indexer.balls==0 && (Timer.getFPGATimestamp() - timeStamp > Constants.kShooterMotorRampDown)){
        rampDownShooter=true;
    }

    
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (m_indexer.isMagEMPTY()) {
      m_shooter.disable();
      m_indexer.stop();
    }

    nextBallReady=false;
    rampDownShooter=false;
  
    System.out.println("Fire Command End");
  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //if (m_indexer.isMagEMPTY() || nextBallReady==true) {
    if (rampDownShooter==true || nextBallReady==true) {
      return true;    }
   else {
      return false;}
  }
}
