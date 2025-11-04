package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

public class Intake extends Subsystem {
    private Intake() { }
    public static final Intake INSTANCE = new Intake();

    // USER CODE
    public MotorEx motor;
    public Command resetZero() {
        return new InstantCommand(() -> { motor.resetEncoder(); });
    }


    public String name = "lift_motor";

    @Override
    public void initialize() {
        motor = new MotorEx(name);

    }

}
