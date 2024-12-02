package frc.robot.subsystems.ballMechanism;

import org.littletonrobotics.junction.AutoLog;

public interface ballMechanismIO {
    @AutoLog
    public class ballMechanismInputs {
        boolean outtakeBeamBrake;
        boolean intakeBeamBrake;
        double ballMotorPower;
    }

    public default void updateInputs(ballMechanismInputs inputs) {}

    public default void setBallMotor(double power) {}

}
