package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import java.util.ArrayList;


@Autonomous
public class PedroTest extends OpMode {
    private Follower follower;

    private int pathState;
    private Timer pathTimer, actionTimer, opmodeTimer;

    // Poses
    private final Pose startPose = new Pose(56, 56, Math.toRadians(45));
    private final Pose rowOneStart = new Pose(12, 24, Math.toRadians(90));
    private final Pose rowOneDone = new Pose(12, 48, Math.toRadians(90));
    private final Pose rowTwoStart = new Pose(-12, 24, Math.toRadians(90));
    private final Pose rowTwoDone = new Pose(-12, 48, Math.toRadians(90));
    private final Pose rowThreeStart = new Pose(-36, 24, Math.toRadians(90));
    private final Pose rowThreeDone = new Pose(-36, 48, Math.toRadians(90));
    private final Pose parkPose = new Pose(56, 24, Math.toRadians(-90));

    // Path chains
    private PathChain toRowOne, pickupRowOne, scoreRowOne;
    private PathChain toRowTwo, pickupRowTwo, scoreRowTwo;
    private PathChain toRowThree, pickupRowThree, scoreRowThree;
    private PathChain park;

    final private ElapsedTime mytimer = new ElapsedTime();

    ArrayList<Boolean> buttonArray = new ArrayList<>();
    int booleanIncrementer = 0;

    public void buildPaths() {
        toRowOne = follower.pathBuilder()
                .addPath(new BezierLine(startPose, rowOneStart))
                .setLinearHeadingInterpolation(startPose.getHeading(), rowOneStart.getHeading())
                .build();

        pickupRowOne = follower.pathBuilder()
                .addPath(new BezierLine(rowOneStart, rowOneDone))
                .setLinearHeadingInterpolation(rowOneStart.getHeading(), rowOneDone.getHeading())
                .build();

        scoreRowOne = follower.pathBuilder()
                .addPath(new BezierLine(rowOneDone, startPose))
                .setLinearHeadingInterpolation(rowOneDone.getHeading(), startPose.getHeading())
                .build();

        toRowTwo = follower.pathBuilder()
                .addPath(new BezierLine(startPose, rowTwoStart))
                .setLinearHeadingInterpolation(startPose.getHeading(), rowTwoStart.getHeading())
                .build();

        pickupRowTwo = follower.pathBuilder()
                .addPath(new BezierLine(rowTwoStart, rowTwoDone))
                .setLinearHeadingInterpolation(rowTwoStart.getHeading(), rowTwoDone.getHeading())
                .build();

        scoreRowTwo = follower.pathBuilder()
                .addPath(new BezierLine(rowTwoDone, startPose))
                .setLinearHeadingInterpolation(rowTwoDone.getHeading(), startPose.getHeading())
                .build();

        toRowThree = follower.pathBuilder()
                .addPath(new BezierLine(startPose, rowThreeStart))
                .setLinearHeadingInterpolation(startPose.getHeading(), rowThreeStart.getHeading())
                .build();

        pickupRowThree = follower.pathBuilder()
                .addPath(new BezierLine(rowThreeStart, rowThreeDone))
                .setLinearHeadingInterpolation(rowThreeStart.getHeading(), rowThreeDone.getHeading())
                .build();

        scoreRowThree = follower.pathBuilder()
                .addPath(new BezierLine(rowThreeDone, startPose))
                .setLinearHeadingInterpolation(rowThreeDone.getHeading(), startPose.getHeading())
                .build();

        park = follower.pathBuilder()
                .addPath(new BezierLine(startPose, parkPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), parkPose.getHeading())
                .build();
    }

    private boolean ifPressed(boolean button) {
        boolean output = false;
        boolean buttonWas;
        if (buttonArray.size() == booleanIncrementer) {
            buttonArray.add(false);
        }
        buttonWas = buttonArray.get(booleanIncrementer);
        if (button != buttonWas && buttonWas) {
            output = true;
        }
        buttonArray.set(booleanIncrementer, button);
        booleanIncrementer += 1;
        return output;
    }

    public void autonomousPathUpdate() {
        switch (pathState) {

            case 00:
                mywait(200);
                setPathState(10);
                break;
            case 10:
                follower.followPath(toRowOne);
                setPathState(12);
                break;
            case 12:
                if (!follower.isBusy()) {
                    follower.followPath(pickupRowOne);
                    setPathState(14);
                }
                break;
            case 14:
                if (!follower.isBusy()) {
                    follower.followPath(scoreRowOne);
                    setPathState(15);
                }
                break;
            case 15:
                if (!follower.isBusy()) {
                    mywait(200);
                    setPathState(20);
                }
                break;

            default:
                mywait(200);

            /* You could check for
            - Follower State: "if(!follower.isBusy()) {}"
            - Time: "if(pathTimer.getElapsedTimeSeconds() > 1) {}"
            - Robot Position: "if(follower.getPose().getX() > 36) {}"
            */
        }
    }

    public void mywait(int msec) {
        mytimer.reset();
        while(mytimer.milliseconds() < msec) {}
    }

    /** These change the states of the paths and actions. It will also reset the timers of the individual switches **/
    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }

    /** This is the main loop of the OpMode, it will run repeatedly after clicking "Play". **/
    @Override
    public void loop() {

        // These loop the movements of the robot, these must be called continuously in order to work
        follower.update();
        autonomousPathUpdate();

        // Feedback to Driver Hub for debugging
        telemetry.addData("path state", pathState);
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.update();
    }

    /** This method is called once at the init of the OpMode. **/
    @Override
    public void init() {
        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();

        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        follower.setStartingPose(startPose);

        telemetry.addData(">", "initialization complete.");
        telemetry.update();


    }

    /** This method is called continuously after Init while waiting for "play". **/
    @Override
    public void init_loop() {}

    /** This method is called once at the start of the OpMode.
     * It runs all the setup actions, including building paths and starting the path system **/
    @Override
    public void start() {
        opmodeTimer.resetTimer();
        setPathState(0);
    }

    /** We do not use this because everything should automatically disable **/
    @Override
    public void stop() {}

}