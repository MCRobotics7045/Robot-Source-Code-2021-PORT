/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

//Refer to post by "gixxy" at https://www.chiefdelphi.com/t/help-creating-a-simple-arcade-drive-in-the-new-2020-command-based-framework-java/373084/7
//for setting up and using DoubleSupplier and lambda expressions
//Also: https://www.chiefdelphi.com/t/need-help-with-running-commands/373670/3  - Vision Discussion

public class DriveCommand extends CommandBase {
  private final DriveTrain m_driveTrain;
  private final DoubleSupplier m_move;
  private final DoubleSupplier m_turn;
  private final DoubleSupplier m_throttle;
  /**
   * Creates a new DriveCommand.
   */
  public DriveCommand(DriveTrain driveTrain, DoubleSupplier move, DoubleSupplier turn, DoubleSupplier throttle){
    // Use addRequirements() here to declare subsystem dependencies.
    m_move = move;
    m_turn = turn;
    m_throttle = throttle;
    m_driveTrain = driveTrain;
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.manualDrive(m_move.getAsDouble(), m_turn.getAsDouble(),m_throttle.getAsDouble());
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end. False will continue to get scheduled and run.
  @Override
  public boolean isFinished() {
    return false;
  }
}
