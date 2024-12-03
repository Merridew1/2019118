package frc.robot.subsystems.hatch;

import org.littletonrobotics.junction.AutoLog;

public interface HatchIO {
    @AutoLog
    public static class HatchInputs {
        boolean touchSensor;
        double armAbsoluteEncRawValue;
    }

    public void updateInputs(HatchInputs inputs);

    public void setHatchPower(double power);
}
