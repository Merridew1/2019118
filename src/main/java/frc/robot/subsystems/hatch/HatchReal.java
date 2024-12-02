package frc.robot.subsystems.hatch;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;

public class HatchReal implements HatchIO {

    /*
     * hardware
     */
    CANSparkMax neo = new CANSparkMax(10, MotorType.kBrushless);
    DigitalInput touch = new DigitalInput(3);
    CANcoder CANCoder = new CANcoder(15);

    public HatchReal() {}

    public void updateInputs(HatchInputs inputs) {
        inputs.armAbsoluteEncRawValue = CANCoder.getPosition().getValueAsDouble();
    }

    public void setHatchPower(double power) {
        neo.set(power);
    }

}
