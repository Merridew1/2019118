package frc.robot.subsystems.hatch;

public interface HatchIO {
    public static class HatchInputs {
        Boolean touchSensor;
        double armAbsoluteEncRawValue;
    }

    public default void updateInputs(HatchInputs inputs) {}

    public default void setHatchPower(double power) {}
}
