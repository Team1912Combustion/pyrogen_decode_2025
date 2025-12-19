package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.Subsystem;
import com.seattlesolvers.solverslib.hardware.motors.CRServo;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;

public class Shooter implements Subsystem {

    private MotorEx left_motor;
    private MotorEx right_motor;
    private MotorGroup motors;
    private CRServo kicker;

    private String left_name = "left_shooter";
    private String right_name = "right_shooter";
    private String servo_name = "kicker";

    private static double SHOOTER_SPEED_LOW = .2;
    private static double SHOOTER_SPEED_MED = .45;
    private static double SHOOTER_SPEED_HIGH = .5;

    private static double KICKER_SPEED_ON = .5;
    private static double KICKER_SPEED_OUT = -.5;

    public Shooter(HardwareMap hMap) {
        kicker = new CRServo(hMap, servo_name);
        left_motor = new MotorEx(hMap, left_name);
        right_motor = new MotorEx(hMap, right_name);
        right_motor.setInverted(true);
        motors = new MotorGroup(left_motor,right_motor);
        motors.setRunMode(Motor.RunMode.VelocityControl);
        motors.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.FLOAT);
        motors.set(0.);
    }

    public void set_high(){
        motors.set(SHOOTER_SPEED_HIGH);
    }
    public void set_medium(){
        motors.set(SHOOTER_SPEED_MED);
    }
    public void set_low(){
        motors.set(SHOOTER_SPEED_LOW);
    }
    public void set_off(){
        motors.set(0.);
    }

    public Command high(){
        return new InstantCommand(this::set_high,this);
    }

    public Command medium(){
        return new InstantCommand(this::set_medium, this);
    }
    public Command off(){
        return new InstantCommand(this::set_off, this);
    }
    public Command low(){
        return new InstantCommand(this::set_low, this);
    }

    public double getSpeed() {
        return motors.getVelocity();
    }

    public void set_kickeron() {
        kicker.set(KICKER_SPEED_ON);
    }
    public void set_kickerout() {
        kicker.set(KICKER_SPEED_OUT);
    }
    public void set_kickeroff() {
        kicker.set(0.);
    }

    public Command kickeron(){
        return new InstantCommand(this::set_kickeron, this);
    }
    public Command kickeroff(){
        return new InstantCommand(this::set_kickeroff, this);
    }
    public Command kickerout(){
        return new InstantCommand(this::set_kickerout, this);
    }

}

