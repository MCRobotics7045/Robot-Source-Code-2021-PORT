/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeRevCommand extends CommandBase {
  private final Intake m_Intake;
  
   // Creates a new IntakeFwdCommand.
  
  public IntakeRevCommand(Intake intake) {
    super();
    m_Intake = intake;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // System.out.println("RIntake-Init");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_Intake.isRunning()){
      m_Intake.stop();
      System.out.println("RIntake-Exec-stop");
    }
    else{
      m_Intake.reverse();
      System.out.println("RIntake-Exec-start");
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //System.out.println("RIntake-Exec-END");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
