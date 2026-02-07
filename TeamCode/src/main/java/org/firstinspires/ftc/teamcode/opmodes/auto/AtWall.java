package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.AutoDrive;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

public class AtWall {

    double driveSpeed = 0.25;
    double slowSpeed = 0.15;
    double minDriveSpeed = 0.05;
    double turnSpeed = 0.20;
    double holdTime = 0.5;

    public static void runTest() {
        ElapsedTime m_timer = new ElapsedTime();
        AutoDrive.INSTANCE.driveStraight(0.2, 0.6, 1., 1.);
        if (AutoSettings.INSTANCE.iAmBlue()) {
            AutoDrive.INSTANCE.turnAndHoldHeading(1., 20., 0.5);
        } else{
            AutoDrive.INSTANCE.turnAndHoldHeading(1., -20., 0.5);
        }
        Shooter.INSTANCE.high();
        m_timer.reset();
        while (m_timer.milliseconds() < 2000.) { }
        Intake.INSTANCE.intakein();
        Shooter.INSTANCE.kickeron();
        m_timer.reset();
        while (m_timer.milliseconds() < 5000.) { }
        Shooter.INSTANCE.stop();
        Intake.INSTANCE.intakeoff();
        Shooter.INSTANCE.kickeroff();
        m_timer.reset();
        while (m_timer.seconds() < 11.) { }
        AutoDrive.INSTANCE.turnAndHoldHeading(1., 0., 0.5);
        AutoDrive.INSTANCE.driveStraight(0.2, 0.6, 18., 4.);

    }

}
