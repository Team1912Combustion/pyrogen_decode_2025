package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@TeleOp(group = "Test", name = "TestCatapult")
public class TestShooter extends CommandOpMode {

    private GamepadEx toolOp;
    private Shooter shooter;

    @Override
    public void initialize() {
        toolOp = new GamepadEx(gamepad1);
        shooter = new Shooter(hardwareMap);
        register(shooter);
        shooter.setDefaultCommand(shooter.off().perpetually());
        toolOp.getGamepadButton(GamepadKeys.Button.DPAD_UP).whileHeld(shooter.high());
        toolOp.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whileHeld(shooter.medium());
        toolOp.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whileHeld(shooter.low());
    }
}
