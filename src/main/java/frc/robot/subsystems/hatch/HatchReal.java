package frc.robot.subsystems.hatch;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;

public class HatchReal implements HatchIO {

    /*
     * hardware
     */
    CANSparkMax neo = new CANSparkMax(10, MotorType.kBrushless);
    DigitalInput touch = new DigitalInput(3);
    AbsoluteEncoder encoder = neo.getAbsoluteEncoder();

    public HatchReal() {}

    public void updateInputs(HatchInputs inputs) {
        inputs.armAbsoluteEncRawValue = encoder.getPosition();
    }

    public void setHatchPower(double power) {
        neo.set(power);
    }

}
