package frc.robot.subsystems.ballMechanism;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.LEDs;

public class ballMechanism extends SubsystemBase {
    ballMechanismIO io;
    ballMechanismInputsAutoLogged intakeAutoLogged = new ballMechanismInputsAutoLogged();
    LEDs lights = new LEDs(9, 100);

    public ballMechanism(ballMechanismIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(intakeAutoLogged);
        Logger.processInputs("ballMechanism", intakeAutoLogged);


    }

    public void setBallMotor(double power) {
        io.setBallMotor(power);
    }

    public boolean getIntakeBeamBreakStatus() {
        return intakeAutoLogged.intakeBeamBrake;
    }

    public boolean getOuttakeBeamBrakeStatus() {
        return intakeAutoLogged.outtakeBeamBrake;
    }

    public Command intakeCommand(Color color, Color altColor) {
        return Commands.startEnd(() -> {
            setBallMotor(1);
        }, () -> {
            setBallMotor(0);
        }, this).until(() -> getIntakeBeamBreakStatus()).unless(() -> getIntakeBeamBreakStatus())
            .andThen(() -> lights.flash(color, altColor)).andThen();

    }

    public Command outtakeCommand() {
        return Commands.startEnd(() -> {
            setBallMotor(1);
        }, () -> {
            setBallMotor(0);
        }, this).until(() -> getOuttakeBeamBrakeStatus()).withTimeout(1);
    }
};

