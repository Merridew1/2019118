package frc.robot.subsystems.drive;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
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
    PIDController driveTrainRightPidController = new PIDController(Constants.PID.DriveTrain.P,
        Constants.PID.DriveTrain.I, Constants.PID.DriveTrain.D);
    PIDController driveTrainLeftPIDController = new PIDController(0, 0, 0);
    SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0, 0);
    private DifferentialDriveOdometry odometry =
        new DifferentialDriveOdometry(inputs.gyroYaw, inputs.leftDistance, inputs.rightDistance);

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
        odometry.update(inputs.gyroYaw, inputs.leftDistance, inputs.rightDistance);
    }

    public void drive(Double lPower, double rPower) {
        io.setDrivePower(
            driveTrainLeftPIDController.calculate(inputs.leftVelocity,
                lPower * Constants.PID.DriveTrain.MAX_VELOCITY),
            driveTrainRightPidController.calculate(inputs.rightVelocity,
                rPower * Constants.PID.DriveTrain.MAX_VELOCITY));
        Logger.recordOutput("Drivetrain/leftPower", lPower);
        Logger.recordOutput("Drivetrain/rightPower", rPower);
    }

    public Command driveCommand(CommandXboxController controller) {
        return run(() -> drive(controller.getLeftY(), controller.getRightY()));
    }


}

