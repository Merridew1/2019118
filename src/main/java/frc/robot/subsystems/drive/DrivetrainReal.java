package frc.robot.subsystems.drive;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.Constants;

/**
 * DrivetrainReal
 */
public class DrivetrainReal implements DrivetrainIO {

    TalonFX left1 = new TalonFX(Constants.Motors.DriveTrain.FRONT_LEFT_MOTOR_ID, "CANivore");
    TalonFX left2 = new TalonFX(Constants.Motors.DriveTrain.BACK_LEFT_MOTOR_ID, "CANivore");
    TalonFX right1 = new TalonFX(Constants.Motors.DriveTrain.FRONT_RIGHT_MOTOR_ID, "CANivore");
    TalonFX right2 = new TalonFX(Constants.Motors.DriveTrain.BACK_RIGHT_MOTOR_ID, "CANivore");

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
    @Override
    public void setDrivePower(double lPower, double rPower) {
        left1.set(lPower);
        left2.set(lPower);
        right1.set(rPower);
        right2.set(rPower);
    }

}
