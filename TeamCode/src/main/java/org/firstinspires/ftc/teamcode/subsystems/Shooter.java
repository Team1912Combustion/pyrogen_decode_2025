package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class Shooter {
    public static final Shooter INSTANCE = new Shooter();
    private Shooter() { }

    private DcMotorEx left_motor = null;
    private DcMotorEx right_motor = null;
    private DcMotorEx kicker = null;

    private String left_name = "left_shooter";
    private String right_name = "right_shooter";
    private String kicker_name = "kicker";


    public static int TICKS_PER_REV = 28;
    static double TARGET_RPM_HIGH = 3025.;
    static double TARGET_RPM_MED = 2500.;
    static double TARGET_RPM_LOW = 2000.;
    public static double VEL_SCALE = 1.;
    static double TARGET_VEL_HIGH = TARGET_RPM_HIGH * TICKS_PER_REV * VEL_SCALE / 60;
    static double TARGET_VEL_MED = TARGET_RPM_MED * TICKS_PER_REV * VEL_SCALE / 60;
    static double TARGET_VEL_LOW = TARGET_RPM_LOW * TICKS_PER_REV * VEL_SCALE / 60;
    public static double PID_P = 120.0;
    public static double PID_I = 0.0;
    public static double PID_D = 0.0;
    public static double PID_F = 0.1;


    private static double POWER_TO_LAUNCH = -.35;
    private static double SHOOTER_SPEED_LOW = .2;
    private static double SHOOTER_SPEED_MED = .40;
    private static double SHOOTER_SPEED_HIGH = .50;
    private static double POWER_TO_BACK = -.25;

    public void back() {
        left_motor.setPower(POWER_TO_BACK);
        right_motor.setPower(POWER_TO_BACK);
    }

    public void launch() {
        left_motor.setVelocity(TARGET_VEL_HIGH);
        right_motor.setVelocity(TARGET_VEL_HIGH);
    }
    public void medium() {
        left_motor.setVelocity(TARGET_VEL_MED);
        right_motor.setVelocity(TARGET_VEL_MED);
    }

    public void low() {
      left_motor.setVelocity(TARGET_VEL_LOW);
      right_motor.setVelocity(TARGET_VEL_LOW);
    }

    public void high() {
        left_motor.setVelocity(TARGET_VEL_HIGH);
        right_motor.setVelocity(TARGET_VEL_HIGH);
    }

    void bangbang(double speed) {
        double oldspeed = left_motor.getVelocity();
        double power;
        if (oldspeed < speed) {
            power = 1.;
        } else {
            power = 0.;
        }
        left_motor.setPower(power);
        right_motor.setPower(power);
    }

    public void stop() {
        left_motor.setVelocity(0.);
        right_motor.setVelocity(0.);
    }

    public double getLShooterVelocity() {
        return left_motor.getVelocity();
    }
    public double getRShooterVelocity() {
        return right_motor.getVelocity();
    }

    public void kickeron() {
        kicker.setPower(1.0);
    }
    public void kickerout() {
        kicker.setPower(-1.0);
    }
    public void kickeroff() {
        kicker.setPower(0.);
    }

    public void init(HardwareMap hMap) {
        left_motor = hMap.get(DcMotorEx.class,left_name);
        right_motor = hMap.get(DcMotorEx.class,right_name);
        left_motor.setDirection(DcMotorSimple.Direction.FORWARD);
        right_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        left_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        right_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        kicker = hMap.get(DcMotorEx.class,kicker_name);
        kicker.setDirection(DcMotorSimple.Direction.FORWARD);
        kicker.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        kicker.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        stop();
        kickeroff();
        PIDFCoefficients pidfNew = new PIDFCoefficients(PID_P,PID_I,PID_D,PID_F);
        left_motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfNew);
        right_motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfNew);
    }

}

