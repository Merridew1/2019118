package frc.robot.subsystems.drive;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.geometry.Rotation2d;

/**
 * DrivetrainReal
 */
public class DrivetrainReal implements DrivetrainIO {

    TalonFX left1 = new TalonFX(2, "CANivore");
    TalonFX left2 = new TalonFX(0, "CANivore");
    TalonFX right1 = new TalonFX(0, "CANivore");
    TalonFX right2 = new TalonFX(0, "CANivore");

    /**
     * Drivetrain Real
     */
    public DrivetrainReal() {
        right1.setInverted(true);
        right2.setInverted(true);
    }

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {
        inputs.gyroYaw = Rotation2d.fromDegrees(0);
    }

    /**
     * Drive Voltage
     */
    public void setDrivePower(double lPower, double rPower) {
        left1.set(lPower);
        left2.set(lPower);
        right1.set(rPower);
        right2.set(rPower);
    }

}
