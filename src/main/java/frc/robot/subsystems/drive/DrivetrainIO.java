package frc.robot.subsystems.drive;

import org.littletonrobotics.junction.AutoLog;
import edu.wpi.first.math.geometry.Rotation2d;

/**
 * DrivetrainIO interface
 */
public interface DrivetrainIO {
    /**
     * Drivetrain IO
     */
    @AutoLog
    public static class DrivetrainIOInputs {
        public Rotation2d gyroYaw = new Rotation2d();
        public double rightVelocity;
        public double leftVelocity;
        public double rightDistance;
        public double leftDistance;
    }

    /** Updates the set of loggable inputs. */
    public void updateInputs(DrivetrainIOInputs inputs);

    /** Run the motor at double power. */
    public void setDrivePower(double lPower, double rPower);


}
