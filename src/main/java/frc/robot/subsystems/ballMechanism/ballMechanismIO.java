package frc.robot.subsystems.ballMechanism;

import org.littletonrobotics.junction.AutoLog;

public interface ballMechanismIO {
    @AutoLog
    public class ballMechanismInputs {
        boolean outtakeBeamBrake;
        boolean intakeBeamBrake;
        double ballMotorPower;
    }

    public void updateInputs(ballMechanismInputs inputs);

    public void setBallMotor(double power);

}
