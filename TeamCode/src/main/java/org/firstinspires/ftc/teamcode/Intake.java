package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Intake")
public class Intake extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo intakeLeftServo = hardwareMap.get(Servo.class, "intakeLeftServo");
        Servo intakeRightServo = hardwareMap.get(Servo.class, "intakeRightServo");

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.x) {
                intakeLeftServo.setPosition(0.5);
                intakeRightServo.setPosition(0.5);

            } else if (gamepad1.a) {
                intakeLeftServo.setPosition(1);
                intakeRightServo.setPosition(0);


            }
        }
    }
}
