package org.firstinspires.ftc.teamcode.subsystems;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;
public class Intake extends Subsystem {

    public static final Intake Instance = new Intake();

    private Intake() { }

    private MotorEx motor;

    public PIDFController controller = new PIDFController(0.005,0.0,0.0);

    public Command resetZero()  {
        return new InstantCommand(() -> { motor.resetEncoder(); });}
    public String name = "Intake_Motor";

    public Command toLow() {
        return new RunToPosition(motor,0.0,controller,this);

    }
    public Command toMiddle() {
        return new RunToPosition(motor,0.0,controller,this);
    }
    public Command toHigh() {
        return new RunToPosition(motor,0.0,controller,this);
    }
    @Override
    public void initialize() {
        motor = new MotorEx(name);
    }

}
