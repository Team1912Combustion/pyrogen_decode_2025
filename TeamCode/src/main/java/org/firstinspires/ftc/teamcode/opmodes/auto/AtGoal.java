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
        while (m_timer.milliseconds() < 3000.) { }
        AutoDrive.INSTANCE.driveStraight(0.2,0.6,-42,4);
        Intake.INSTANCE.intakein();
        m_timer.reset();
        while (m_timer.milliseconds() < 2000.) { }
        Shooter.INSTANCE.kickeron();
        Shooter.INSTANCE.high();
        m_timer.reset();
        while (m_timer.milliseconds() < 2000.) { }
        Shooter.INSTANCE.kickeroff();
        Shooter.INSTANCE.stop();
        if (AutoSettings.INSTANCE.iAmBlue()) {
            AutoDrive.INSTANCE.turnAndHoldHeading(1., -135., 0.5);
        } else {
            AutoDrive.INSTANCE.turnAndHoldHeading(1., 135., 0.5);
        }
        m_timer.reset();
        AutoDrive.INSTANCE.driveStraight(0.2,0.6,-34,4);
        while (m_timer.milliseconds() < 2000.) { }
        //AutoDrive.INSTANCE.strafeStraight(0.2,0.6,-90,4);
        m_timer.reset();
        while (m_timer.milliseconds() < 2000. ) { }
        AutoDrive.INSTANCE.driveStraight(0.2,0.6,36,4);
        m_timer.reset();
        while (m_timer.milliseconds() < 2000.) { }
        AutoDrive.INSTANCE.turnAndHoldHeading(1., 0., 0.5);
        //AutoDrive.INSTANCE.strafeStraight(0.2,0.6,90,4);
        m_timer.reset();
        while (m_timer.milliseconds() < 2000.) { }
        Shooter.INSTANCE.kickeron();
        Shooter.INSTANCE.high();
        m_timer.reset();






    }

}
