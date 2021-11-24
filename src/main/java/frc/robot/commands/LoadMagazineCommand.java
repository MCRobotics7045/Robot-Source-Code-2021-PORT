/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Indexer;


public class LoadMagazineCommand extends CommandBase {
  private final Intake m_Intake;
  private final Indexer m_indexer;
  private static boolean lastState;
  private static boolean currentState;
  /**
   * Creates a new LoadMagazineCommand.
   */
  public LoadMagazineCommand(final Intake intake, final Indexer indexer) {
    super();
    m_Intake = intake;
    m_indexer = indexer;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
    addRequirements(indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Magazine CMD INIT");
    lastState = m_indexer.swIntake.get();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  
    m_Intake.start();
   
    currentState = m_indexer.swIntake.get();
    if (lastState==true && currentState==false){
      //falling edge detected
      Indexer.balls = Indexer.balls + 1;
      lastState = currentState;
      System.out.print("Fall edge Magazine CMD - Balls=");
      System.out.println(Indexer.balls);

    }
    else{
      lastState = currentState;
    }

    //If 1 or 2 balls and StartIndexer switch is tripped, advance indexer to next switch. Ensure ball not already in shooter.
    //if ((m_indexer.swStart.get()==false) && ((Indexer.balls==1) || (Indexer.balls==2)) && !m_indexer.isShooterPrimed()) {
    //if ((m_indexer.swIntake.get()==false) && ((Indexer.balls==1) || (Indexer.balls==2)) && !m_indexer.isShooterPrimed()) {
    if ((m_indexer.swIntake.get()==false) && (Indexer.balls==1)  && !m_indexer.isShooterPrimed()) {  
        m_indexer.start();
    }
    //Keep Advancing balls until middle switch
    //if (m_indexer.isRunning() && (Indexer.balls<3) && m_indexer.swMidIndex.get()==false){
    if (m_indexer.isRunning() && (Indexer.balls<2) && m_indexer.swMidIndex.get()==false){
      m_indexer.stop(); 
    }
    
    //Advanced 2 balls until ball next to shooter
    //if (m_indexer.swIntake.get()==false && (Indexer.balls>2) ) {
    if (m_indexer.swIntake.get()==false && (Indexer.balls>1) ) {  
      m_indexer.start();
    }

    

    //if ball is next to shooter then STOP indexer
    if (m_indexer.isShooterPrimed()){
      m_indexer.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    m_Intake.stop();
    System.out.print("Magazine - Intake Stopped with Balls=");
    System.out.println(Indexer.balls);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if (m_indexer.isMagFUll()) {
      return true;    }
   else {
      return false;}
  }
}
