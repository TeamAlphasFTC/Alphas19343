package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "Motor_Test")
public class MotorTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //Initialization Code Goes Here

        DcMotor testMotor = hardwareMap.get(DcMotor.class, "testMotor");

        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()) {

            double leftx = gamepad1.left_stick_x;
            double lefty = gamepad1.left_stick_y;
            double rightx = gamepad1.right_stick_x;
            double righty = gamepad1.right_stick_y;

            double speed = leftx + lefty + righty + rightx;

            testMotor.setPower(speed);

        }
    }
}