package frc.robot.subsystems.hatch;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
/*
 * Hatch Real class
 */

public class HatchReal implements HatchIO {

    /*
     * hardware
     */
    CANSparkMax neo = new CANSparkMax(Constants.Motors.Hatch.HATCH_MOTOR_ID, MotorType.kBrushless);
    DigitalInput touch = new DigitalInput(3);
    CANcoder CANCoder = new CANcoder(15);

    @Override
    public void updateInputs(HatchInputs inputs) {
        inputs.armAbsoluteEncRawValue = CANCoder.getPosition().getValueAsDouble();
    }

    @Override
    public void setHatchPower(double power) {
        neo.set(power);
    }

}
