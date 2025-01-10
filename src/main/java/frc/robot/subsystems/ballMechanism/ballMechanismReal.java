package frc.robot.subsystems.ballMechanism;

import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.DigitalInput;

public class ballMechanismReal implements ballMechanismIO {
    SparkMax neo =
        new SparkMax(frc.robot.Constants.Motors.Intake.INTAKE_MOTOR_ID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
    DigitalInput intakeBeamBrake = new DigitalInput(0);
    DigitalInput outtakeBeamBrake = new DigitalInput(1);

    @Override
    public void updateInputs(ballMechanismInputs inputs) {
        inputs.intakeBeamBrake = !intakeBeamBrake.get(); // true == game piece
        inputs.outtakeBeamBrake = !outtakeBeamBrake.get();
        inputs.ballMotorPower = neo.get();

    }



    @Override
    public void setBallMotor(double power) {
        neo.set(power);
    }

}
