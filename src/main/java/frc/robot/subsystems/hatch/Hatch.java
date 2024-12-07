package frc.robot.subsystems.hatch;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/*
 * Hatch Class
 */
public class Hatch extends SubsystemBase {
    public HatchIO io;
    private HatchInputsAutoLogged inputs = new HatchInputsAutoLogged();
    private static final Rotation2d upright = Rotation2d.fromDegrees(100);
    private static final Rotation2d neutral = Rotation2d.fromDegrees(10);

    public Hatch(HatchIO io) {
        this.io = io;
        hatchPidController.setTolerance(5);
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("hatch", inputs);
        double pidValue = hatchPidController.calculate(getHatchAngle().getDegrees());
        io.setHatchPower(pidValue);
    }


    PIDController hatchPidController =
        new PIDController(Constants.PID.Hatch.P, Constants.PID.Hatch.I, Constants.PID.Hatch.D);

    public Boolean atGoal() {
        return hatchPidController.atSetpoint();
    }


    Rotation2d getHatchAngle() {
        return Rotation2d.fromRotations(inputs.armAbsoluteEncRawValue);
    }

    public Command setHatchAngle(Rotation2d angle) {
        return Commands.runOnce(() -> {
            hatchPidController.reset();
            hatchPidController.setSetpoint(angle.getDegrees());
        }).andThen(Commands.waitUntil(() -> atGoal()));
    }

    public Command setHatchUp() {
        return setHatchAngle(upright);
    }

    public Command setHatchNeutral() {
        return setHatchAngle(neutral);
    }
    /*
     * public Command goToPosition(Rotation2d angle) { return Commands.runOnce(() -> {
     * hatchPidController.reset(); hatchPidController.setSetpoint(angle.getDegrees()); pidEnabled =
     * true; }).andThen(Commands.waitUntil(() -> atGoal())) .andThen(Commands.runOnce(() ->
     * pidEnabled = false)); }
     * 
     * public Command hatchUpCommand() { return goToPosition(upright); }
     * 
     * public Command hatchNeutralCommand() { return goToPosition(neutral); }
     */
}
