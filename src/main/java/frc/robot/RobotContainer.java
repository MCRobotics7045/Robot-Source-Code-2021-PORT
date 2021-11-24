/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.Autonomous;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.FireCommand;
import frc.robot.commands.LimelightAutoAlign;
import frc.robot.commands.LoadMagazineCommand;
import frc.robot.commands.IntakeFwdCommand;
import frc.robot.commands.IntakeRevCommand;
import frc.robot.commands.IndexerCommand;
import frc.robot.commands.IndexerRevCommand;
import frc.robot.commands.ShooterToggleCommand;
import frc.robot.commands.ColorWheelCommands.*; //CW from Kat
import frc.robot.commands.DartDownCommand;
import frc.robot.commands.DartUpCommand;
import frc.robot.subsystems.ColorWheelSubsystem;
import frc.robot.subsystems.DartSubsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.wpilibj2.command.button.POVButton;

import frc.robot.Constants;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain driveTrain = new DriveTrain();
  private final Joystick joystick = new Joystick(Constants.JOYSTICK_PORT);
  private final XboxController xbox = new XboxController(Constants.XBOX_CONTROLLER_PORT);
  private final LimeLight limeLight = new LimeLight(false);
  private final Intake intake = new Intake();
  private final Indexer indexer = new Indexer();
  private final Shooter m_shooter = new Shooter();
  private final ColorWheelSubsystem  m_color_wheel = new ColorWheelSubsystem();
  private final DartSubsystem m_dart = new DartSubsystem();
  private final CommandBase m_autonomousCommand = new Autonomous(driveTrain, limeLight, indexer, m_shooter, intake);
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    SmartDashboard.putData(driveTrain);
    SmartDashboard.putData(limeLight);
    SmartDashboard.putData(indexer);
    //SmartDashboard.putData(intake);
    //  SmartDashboard.putData(m_shooter);



    driveTrain.setDefaultCommand(new DriveCommand(driveTrain,() -> -joystick.getY(), () -> joystick.getX(), () -> -joystick.getThrottle()));
    
        // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    final JoystickButton aim = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_AIM);
    final JoystickButton intakeFwd = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_INTAKE_FWD);
    final JoystickButton intakeRev = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_INTAKE_REV);
    final JoystickButton indexAdv = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_INDEXER_ADV);
    final JoystickButton indexRev = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_INDEXER_REV);
    final JoystickButton shooterMotorControl = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_SHOOTER_MOTOR);
    final JoystickButton loadMagazine = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_LOAD_MAGAZINE);
    final JoystickButton firePowerCell = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_SHOOT);
    //final POVButton dartUp = new POVButton(joystick, 0);
    //final POVButton dartDown = new POVButton(joystick, 180);
    final JoystickButton dartUp = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_DART_UP);
    final JoystickButton dartDown = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_DART_DOWN);
    final JoystickButton autoRotateColorWHeel = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_COLOR_WHEEL_AUTO);

    //Xbox Bindings
    
    final JoystickButton buttonRED = new JoystickButton(xbox, Constants.joystickRedButton);
    final JoystickButton buttonYELLOW = new JoystickButton(xbox, Constants.joystickYellowButton);
    final JoystickButton buttonGREEN = new JoystickButton(xbox, Constants.joystickGreenButton);
    final JoystickButton buttonBLUE = new JoystickButton(xbox, Constants.joystickBlueButton);
    final JoystickButton buttonLB = new JoystickButton(xbox, Constants.joystickLBButton);
    final JoystickButton buttonRB = new JoystickButton(xbox, Constants.joystickRBButton);
  

    //Joystick COmmands
    aim.whileHeld(new LimelightAutoAlign(driveTrain, limeLight, () -> -joystick.getY(), () -> -joystick.getThrottle()));
    intakeFwd.toggleWhenPressed(new IntakeFwdCommand(intake));
    intakeRev.toggleWhenPressed(new IntakeRevCommand(intake));
    indexAdv.toggleWhenPressed(new IndexerCommand(indexer));
    indexRev.toggleWhenPressed(new IndexerRevCommand(indexer));
    shooterMotorControl.toggleWhenPressed(new ShooterToggleCommand(m_shooter));
    loadMagazine.whenPressed(new LoadMagazineCommand(intake, indexer));
    firePowerCell.whenPressed(new FireCommand(indexer, m_shooter));
    autoRotateColorWHeel.whenPressed(new RotateColorWheelCommand(m_color_wheel));
    //dartUp.whenPressed(new DartUpCommand(m_dart)); //POV
    //dartDown.whenPressed(new DartDownCommand(m_dart));  //POV
    dartUp.toggleWhenPressed(new DartUpCommand(m_dart));
    dartDown.toggleWhenPressed(new DartDownCommand(m_dart));

    //Xbox Color WHeel Commands
    
    buttonRED.whileHeld(new RedCommand(m_color_wheel));
    buttonYELLOW.whileHeld(new YellowCommand(m_color_wheel));
    buttonGREEN.whileHeld(new GreenCommand(m_color_wheel));
    buttonBLUE.whileHeld(new BlueCommand(m_color_wheel));

    buttonLB.whileHeld(new ColorWheelCommand(m_color_wheel));
    buttonLB.whenReleased(new ColorWheelStopCommand(m_color_wheel));
    buttonRB.whenReleased(new ColorWheelStopCommand(m_color_wheel));
    buttonRB.whileHeld(new ColorWheelCommand(m_color_wheel));

  } 


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
  // An ExampleCommand will run in autonomous
    return m_autonomousCommand;
  }

  public void turnOffLimelightLED(){
    limeLight.setLedMode(1); //1 =OFF 3=ON
  }

  public void stopAllMotors() {
    driveTrain.stop();
    intake.stop();
    m_shooter.stop();
    indexer.stop();
  }
}
