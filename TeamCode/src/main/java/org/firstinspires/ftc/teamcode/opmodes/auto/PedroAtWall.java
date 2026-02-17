package org.firstinspires.ftc.teamcode.opmodes.auto;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.subsystems.ActiveOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Odometry;
import org.firstinspires.ftc.teamcode.subsystems.Vision;

import java.util.ArrayList;

public class PedroAtWall {

    private static Follower follower;
    private static int PathState;
    private static Telemetry telemetry;

    private static Timer timer;

    private static Pose startPose = null;

    public static void init(HardwareMap hardwareMap, Telemetry telemetry, boolean iAmBlue, boolean shootLast) {
    }

    public static void run() {
    }
    // Path chains

}
