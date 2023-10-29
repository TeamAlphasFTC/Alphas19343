package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Arm")
public class Arm extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor armLeftMotor = hardwareMap.get(DcMotor.class, "armLeftMotor");
        DcMotor armRightMotor = hardwareMap.get(DcMotor.class, "armRightMotor");

        waitForStart();

        while (opModeIsActive()) {

            double speed = gamepad1.left_stick_y;

            armLeftMotor.setPower(speed);
            armRightMotor.setPower(-speed);


            }
        }
    }
