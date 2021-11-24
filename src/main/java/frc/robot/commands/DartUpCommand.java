/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DartSubsystem;
import frc.robot.Constants;


public class DartUpCommand extends CommandBase {
  private final DartSubsystem m_Dart;
  
   // Creates a new DartFwdCommand.

  public DartUpCommand(DartSubsystem dartvar) {
    super();
    m_Dart = dartvar;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(dartvar);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //System.out.println("FDart-Init");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    m_Dart.up();
    System.out.print("Dart going UP - ");
    System.out.println(m_Dart.dartPot.getVoltage());
/*
    //TOGGLE FUnction with no Limits
    if (m_Dart.isRunning()){
      m_Dart.stop();
      }
    else{
      m_Dart.up();
     }
    */
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Dart.stop();
    //System.out.println("FDart-Exec-END");
  }

  // Returns true when the command should end. True to run once. 
  /*
 * Hall Effect Sensor
 * Sensor is driven low in the presence of a magnetic field, and high impedance
 * when there is no magnet present
 * Use this as a limit switch.
 */
  @Override
  public boolean isFinished() {
    if (m_Dart.dartVoltage() < Constants.kDartOutVoltage){ //limit reached
      return true;
    }
    else{
      return false;
    }
  }
}
