package frc.robot.subsystems.ballMechanism;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/*
 * Intake class
 */
public class ballMechanism extends SubsystemBase {
    ballMechanismIO io;
    ballMechanismInputsAutoLogged intakeAutoLogged = new ballMechanismInputsAutoLogged();

    /*
     * Constructor
     */
    public ballMechanism(ballMechanismIO io) {
        this.io = io;
        CameraServer.startAutomaticCapture("Intake Camera", 0);
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

    public Command intakeCommand() {
        return Commands.startEnd(() -> {
            setBallMotor(1);
        }, () -> {
            setBallMotor(0);
        }, this).until(() -> getIntakeBeamBreakStatus()).unless(() -> getIntakeBeamBreakStatus());

    }

    public Command outtakeCommand() {
        return Commands.startEnd(() -> {
            setBallMotor(1);
        }, () -> {
            setBallMotor(0);
        }, this).until(() -> getOuttakeBeamBrakeStatus()).withTimeout(1);
    }
};

