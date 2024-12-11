package frc.robot.subsystems.hatch;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
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

    /*
     * PID
     */
    ProfiledPIDController hatchPidController = new ProfiledPIDController(Constants.PID.Hatch.P,
        Constants.PID.Hatch.I, Constants.PID.Hatch.D, new TrapezoidProfile.Constraints(
            Constants.PID.Hatch.HATCH_MAX_VELOCITY, Constants.PID.Hatch.HATCH_MAX_ACCELERATION));

    public Boolean atGoal() {
        return hatchPidController.atGoal();
    }


    Rotation2d getHatchAngle() {
        return Rotation2d.fromRotations(inputs.armAbsoluteEncRawValue);
    }

    public Command setHatchAngle(Rotation2d angle) {
        return Commands.runOnce(() -> {
            hatchPidController.reset(inputs.armAbsoluteEncRawValue);
            hatchPidController.setGoal(angle.getDegrees());
        }).andThen(Commands.waitUntil(() -> atGoal()));
    }

    public Command setHatchUp() {
        return setHatchAngle(upright);
    }

    public Command setHatchNeutral() {
        return setHatchAngle(neutral);
    }
}
