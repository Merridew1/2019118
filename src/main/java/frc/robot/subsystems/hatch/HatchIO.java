package frc.robot.subsystems.hatch;

import org.littletonrobotics.junction.AutoLog;

public interface HatchIO {
    @AutoLog
    public static class HatchInputs {
        Boolean touchSensor;
        double armAbsoluteEncRawValue;
    }

    public default void updateInputs(HatchInputs inputs) {}

    public default void setHatchPower(double power) {}
}
