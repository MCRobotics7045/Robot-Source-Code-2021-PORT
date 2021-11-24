/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimeLight;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class LimelightAutoAlign extends CommandBase {
  DriveTrain m_driveTrain;
  LimeLight m_limeLight;
  DoubleSupplier m_move;
  DoubleSupplier m_throttle;
  /**
   * Creates a new LimelightAutoAlign.
   */
  public LimelightAutoAlign(DriveTrain driveTrain, LimeLight limeLight, DoubleSupplier move, DoubleSupplier throttle) {
    
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveTrain = driveTrain;
    m_limeLight = limeLight;
    m_move = move;
    m_throttle = throttle;
    addRequirements(driveTrain);
    addRequirements(limeLight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //m_driveTrain.manualDrive(0, 0, 1);
    m_limeLight.setPipeline(0);
    m_limeLight.setCameraMode(0);
    m_limeLight.setLedMode(3); //turn on=3 LED
    //System.out.println("LL-Initialize");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    boolean isTarget = m_limeLight.isTarget();
    
    SmartDashboard.putBoolean("Camera has target", isTarget);

    //isTarget = true; //TESTING
    if (isTarget){
      double tx = m_limeLight.getTx();
      double ty = m_limeLight.getTy();
      double ta = m_limeLight.getTa();


      SmartDashboard.putNumber("Camera tx", tx);
      SmartDashboard.putNumber("Camera ty", ty);
      SmartDashboard.putNumber("Camera ta", ta);
      //tx=5.0; //TESTING

      double turnAlign = tx * Constants.kTargetTurn;

      //Does it exceed Maximum Turn Speed?
      if (Math.abs(turnAlign)> Constants.kTargetTurnMax) {
        turnAlign = Constants.kTargetTurnMax * Math.signum(tx);
      }

       //Set minimum turn speed if turnAlign < threshold and offset to target still exists
      if ((Math.abs(turnAlign) < Constants.kTargetTurnMinThreshold) && (Math.abs(tx)> Constants.kMinXTargetOffset)){
        turnAlign = Constants.kTargetTurnMinThreshold * Math.signum(tx);
      }
      if ((Math.abs(tx)<Constants.kMinXTargetOffset)){
        turnAlign=0.0;
      }

      double moveAlign = -ty * Constants.kTargetMove;

      if (Math.abs(moveAlign)> Constants.kTargetMoveMax) {
        moveAlign = Constants.kTargetMoveMax * Math.signum(-ty);
      }

      if ((Math.abs(moveAlign) < Constants.kTargetMoveMinThreshold) && (Math.abs(ty)> Constants.kMinYTargetOffset)){
        //Set minimum turn speed if turnAlign < threshold and off to target still exists
        moveAlign = Constants.kTargetMoveMinThreshold * Math.signum(-ty);
      }
      if ((Math.abs(ty)<Constants.kMinYTargetOffset)){
        moveAlign=0.0;
      }
      System.out.print("Turn =");
      System.out.print(turnAlign);
      System.out.print("   tX =");
      System.out.print(tx);
      System.out.print("   Move  =");
      System.out.print(moveAlign);
      System.out.print("   tY =");
      System.out.println(ty);
     // m_driveTrain.targetDrive(m_move.getAsDouble(), turnAlign, m_throttle.getAsDouble());
      m_driveTrain.targetDrive(moveAlign, turnAlign, m_throttle.getAsDouble());
      //System.out.println("LL-Execute");

    } //isTarget

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.manualDrive(0,0,1);
    m_limeLight.setLedMode(1); //LED OFF=1 ON=3
    //System.out.println("LL-End/Interupted");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //System.out.println("LL-Finished");
    
    //return false;

    //Stop command if target acquired and X and Y offsets are less than kMinTagetOffset e.g. 0.25 degrees
    if (m_limeLight.isTarget() && ((Math.abs(m_limeLight.getTx())) < Constants.kMinXTargetOffset) && ((Math.abs(m_limeLight.getTy())) < Constants.kMinYTargetOffset)){
      System.out.println("LimeLight End");
      
      return true;
    }
    else{
      return false;
    }
  }
}
