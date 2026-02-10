package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.AutoDrive;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

public class AtGoal {

    double driveSpeed = 0.25;
    double slowSpeed = 0.15;
    double minDriveSpeed = 0.05;
    double turnSpeed = 0.20;
    double holdTime = 0.5;

    public static void runTest() {
        ElapsedTime m_timer = new ElapsedTime();
        m_timer.reset();
        while (m_timer.milliseconds() < 100.) { }
        AutoDrive.INSTANCE.driveStraight(0.2,0.6,-50,4);
        Shooter.INSTANCE.medium();
        m_timer.reset();
        while (m_timer.milliseconds() < 1000.) { }
        Intake.INSTANCE.intakein();
        Shooter.INSTANCE.kickeron();
        m_timer.reset();
        while (m_timer.milliseconds() < 500.) { }
        Shooter.INSTANCE.kickeroff();
        Intake.INSTANCE.intakein();
        Shooter.INSTANCE.stop();
        m_timer.reset();
        while (m_timer.milliseconds() < 500.) { }

        if (AutoSettings.INSTANCE.iAmBlue()) {
            AutoDrive.INSTANCE.turnAndHoldHeading(1., -135., 0.5);
        } else {
            AutoDrive.INSTANCE.turnAndHoldHeading(1., 135., 0.5);
        }
        AutoDrive.INSTANCE.driveStraight(0.2,0.6,-34,4);

        m_timer.reset();
        while (m_timer.milliseconds() < 200.) { }

        AutoDrive.INSTANCE.driveStraight(0.2,0.6,36,4);
        AutoDrive.INSTANCE.turnAndHoldHeading(1., 0., 0.5);
        Shooter.INSTANCE.medium();
        m_timer.reset();
        while (m_timer.milliseconds() < 500.) { }

        Shooter.INSTANCE.kickeron();
        Intake.INSTANCE.intakein();;

        m_timer.reset();
        while (m_timer.milliseconds() < 500.) { }

        Shooter.INSTANCE.kickeroff();
        Intake.INSTANCE.intakeoff();;
        Shooter.INSTANCE.stop();
        m_timer.reset();
        while (m_timer.milliseconds() < 500.) { }
        AutoDrive.INSTANCE.turnAndHoldHeading(1,-135,0.5);
        AutoDrive.INSTANCE.strafeStraight(0.2,0.6,-24,4);
        m_timer.reset();
        while (m_timer.milliseconds() < 500.) { }
        Intake.INSTANCE.intakein();
        AutoDrive.INSTANCE.driveStraight(0.2,0.6,-36,4);
        m_timer.reset();
        while (m_timer.milliseconds() < 500.) { }
        AutoDrive.INSTANCE.driveStraight(0.2,0.6,34,4);
        m_timer.reset();



    }
}