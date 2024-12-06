package frc.robot.subsystems.drive;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

/**
 * Drivetrain subsystem.
 */

public class Drivetrain extends SubsystemBase {
    private DrivetrainIO io;
    private DrivetrainIOInputsAutoLogged inputs = new DrivetrainIOInputsAutoLogged();

    /**
     * Create Wrist Intake Subsystem
     */
    public Drivetrain(DrivetrainIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("Drivetrain", inputs);
    }

    public void drive(Double lPower, double rPower) {
        io.setDrivePower(lPower, rPower);
        Logger.recordOutput("Drivetrain/leftPower", lPower);
        Logger.recordOutput("Drivetrain/rightPower", rPower);
    }

    public Command driveCommand(CommandXboxController controller) {
        return run(() -> drive(controller.getLeftY(), controller.getRightY()));
    }

    PIDController driveTrainPidController = new PIDController(Constants.PID.DriveTrain.P,
        Constants.PID.DriveTrain.I, Constants.PID.DriveTrain.D);
}

