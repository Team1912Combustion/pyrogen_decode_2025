package org.firstinspires.ftc.teamcode.subsystems;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;
public class Shooting extends Subsystem {

    public static final Shooting INSTANCE = new Shooting();

    private Shooting() { }
    private MotorEx motor;
    public PIDFController controller = new PIDFController(0.005,0.0,0.0);


}
