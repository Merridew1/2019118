package frc.robot.subsystems.hatch;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
/*
 * Hatch Real class
 */

public class HatchReal implements HatchIO {

    /*
     * hardware
     */
    SparkMax neo = new SparkMax(Constants.Motors.Hatch.HATCH_MOTOR_ID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
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
