package frc.robot.subsystems.ballMechanism;

import org.littletonrobotics.junction.AutoLog;

/*
 * Intake IO interface
 */
public interface ballMechanismIO {
    @AutoLog
    /*
     * Intake inputs class
     */
    public class ballMechanismInputs {
        boolean outtakeBeamBrake;
        boolean intakeBeamBrake;
        double ballMotorPower;
    }

    public void updateInputs(ballMechanismInputs inputs);

    public void setBallMotor(double power);

}
