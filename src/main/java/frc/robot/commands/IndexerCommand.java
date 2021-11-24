/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;


public class IndexerCommand extends CommandBase {
  private final Indexer m_indexer;

   // Creates a new IndexerCommand.

  public IndexerCommand(Indexer indexer) {
    super();
    m_indexer = indexer;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Indexer-Init");
    m_indexer.init(); //initial encoder to 0 each time
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    if (m_indexer.isRunning() || Indexer.balls==5 || m_indexer.swShooter.get()==false){  //either Mag full or ball next to shooter
      m_indexer.stop();
      System.out.println("Indexer-Exec-stop");
    }
    else{
      m_indexer.start();
      System.out.println("Indexer-Exec-start");
    }
      
      
    }
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("FIndexer-Exec-END");
    //m_indexer.stop();
  }

  // Returns true when the command should end. True to run once. 
  @Override
  public boolean isFinished() {
    //While the # of revolutions is < kEncReveolutions, return false to continue command
    //if (m_indexer.isShooterPrimed() || m_indexer.isMagFUll()) {
    //   return true;}
    //else {
    //   return false;}

    return true;
  }
}
