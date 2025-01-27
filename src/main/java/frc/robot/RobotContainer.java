package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Robot.RobotRunType;
import frc.robot.subsystems.LEDs;
import frc.robot.subsystems.ballMechanism.ballMechanism;
import frc.robot.subsystems.ballMechanism.ballMechanismIO;
import frc.robot.subsystems.ballMechanism.ballMechanismReal;
import frc.robot.subsystems.drive.Drivetrain;
import frc.robot.subsystems.drive.DrivetrainIO;
import frc.robot.subsystems.drive.DrivetrainReal;
import frc.robot.subsystems.hatch.Hatch;
import frc.robot.subsystems.hatch.HatchIO;
import frc.robot.subsystems.hatch.HatchReal;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final CommandXboxController driver = new CommandXboxController(Constants.driverID);
    // Initialize AutoChooser Sendable
    private final SendableChooser<String> autoChooser = new SendableChooser<>();

    /* Subsystems */
    private Drivetrain drivetrain;
    private Hatch hatch;
    private ballMechanism intake;
    private LEDs leds;

    /* Trigger events */
    Trigger ballAtIntake = new Trigger(() -> intake.getIntakeBeamBreakStatus());
    Trigger ballAtOuttake = new Trigger(() -> intake.getOuttakeBeamBrakeStatus());

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer(RobotRunType runtimeType) {
        SmartDashboard.putData("Choose Auto: ", autoChooser);
        autoChooser.setDefaultOption("Wait 1 Second", "wait");
        switch (runtimeType) {
            case kReal:
                drivetrain = new Drivetrain(new DrivetrainReal());
                hatch = new Hatch(new HatchReal());
                intake = new ballMechanism(new ballMechanismReal());
                break;
            case kSimulation:
                // drivetrain = new Drivetrain(new DrivetrainSim() {});
                break;
            default:
                drivetrain = new Drivetrain(new DrivetrainIO() {

                    @Override
                    public void updateInputs(DrivetrainIOInputs inputs) {}

                    @Override
                    public void setDrivePower(double lPower, double rPower) {}
                });
                hatch = new Hatch(new HatchIO() {

                    @Override
                    public void updateInputs(HatchInputs inputs) {}

                    @Override
                    public void setHatchPower(double power) {}
                });
                intake = new ballMechanism(new ballMechanismIO() {

                    @Override
                    public void updateInputs(ballMechanismInputs inputs) {}

                    @Override
                    public void setBallMotor(double power) {}
                });
        }
        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        ballAtIntake.onTrue(leds.flashCommand(Color.kPink, Color.kBrown));
        ballAtOuttake.onTrue(leds.flashCommand(Color.kBlack, Color.kRed));
        drivetrain.setDefaultCommand(drivetrain.driveCommand(driver));
        driver.a().whileTrue(hatch.setHatchUp().andThen(Commands.startEnd(() -> {
            driver.getHID().setRumble(RumbleType.kBothRumble, 1);
        }, () -> {
            driver.getHID().setRumble(RumbleType.kBothRumble, 0);
        })));
        driver.b().whileTrue(hatch.setHatchNeutral());
        driver.leftTrigger().whileTrue(intake.intakeCommand());
        driver.rightTrigger().whileTrue(intake.outtakeCommand());
    }

    /**
     * Gets the user's selected autonomous command.
     *
     * @return Returns autonomous command selected.
     */
    public Command getAutonomousCommand() {
        Command autocommand;
        String stuff = autoChooser.getSelected();
        switch (stuff) {
            case "wait":
                autocommand = new WaitCommand(1.0);
                break;
            default:
                autocommand = new InstantCommand();
        }
        return autocommand;
    }
}
