package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.hardware.motors.GoBILDA5201Series;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

/*
 * Sine wave sample to demonstrate telemetry and config variables in action. Adjust the amplitude,
 * phase, and frequency of the oscillation and watch the changes propagate immediately to the graph.
 */
@Autonomous
public class PIDTest extends LinearOpMode {
    public static double TARGET_RPM = 3000.;
    public static int TICKS_PER_REV = 28;
    public static int MAX_RPM = 5000;
    public static int RPM_INC = 100;
    public static double VEL_SCALE = 1.;
    public static double PID_P = 120.0;
    public static double PID_I = 0.0;
    public static double PID_D = 0.0;
    public static double PID_F = 0.1;

    DcMotorEx left_motor;
    DcMotorEx right_motor;
    public String left_name = "left_shooter";
    public String right_name = "right_shooter";

    @Override
    public void runOpMode() throws InterruptedException {

        left_motor = (DcMotorEx)hardwareMap.get(DcMotor.class, left_name);
        right_motor = (DcMotorEx)hardwareMap.get(DcMotor.class, right_name);
        left_motor.setDirection(DcMotorSimple.Direction.FORWARD);
        right_motor.setDirection(DcMotorSimple.Direction.REVERSE);

        PIDFCoefficients pidfOld = left_motor.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("LEFT PID", pidfOld);
        pidfOld = right_motor.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("RIGHT PID", pidfOld);
        PIDFCoefficients pidfNew = new PIDFCoefficients(PID_P,PID_I,PID_D,PID_F);
        left_motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfNew);
        right_motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfNew);
        pidfNew = left_motor.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("LEFT PID", pidfNew);
        pidfNew = right_motor.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("RIGHT PID", pidfNew);
        telemetry.update();

        waitForStart();

        if (isStopRequested()) {
            return;
        }


        while (opModeIsActive()) {

            double target_vel = TARGET_RPM * TICKS_PER_REV * VEL_SCALE / 60;
            left_motor.setVelocity(target_vel);
            right_motor.setVelocity(target_vel);
            double left_rpm = left_motor.getVelocity() * 60. / TICKS_PER_REV;
            double right_rpm = right_motor.getVelocity() * 60. / TICKS_PER_REV;

            telemetry.addData("TARGET", TARGET_RPM);
            telemetry.addData("LEFT TICK/SEC", left_motor.getVelocity());
            telemetry.addData("RIGHT TICK/SEC", right_motor.getVelocity());
            telemetry.addData("LEFT RPM", left_rpm);
            telemetry.addData("RIGHT RPM", right_rpm);
            telemetry.addData("LEFT POWER", left_motor.getPower());
            telemetry.addData("RIGHT POWER", right_motor.getPower());
            telemetry.update();

            sleep(20);
        }
    }
}