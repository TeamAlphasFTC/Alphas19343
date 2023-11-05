package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp (name = "Oakridge")
public class Oakridge extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        DcMotor armMotor = hardwareMap.dcMotor.get("armMotor");
        Servo gripperLeftServo = hardwareMap.servo.get("gripperLeftServo");
        Servo gripperRightServo = hardwareMap.servo.get("gripperRightServo");
        DcMotor pixelMotor = hardwareMap.dcMotor.get("pixelMotor");
        Servo pixelLeftServo = hardwareMap.servo.get("pixelLeftServo");
        Servo pixelRightServo = hardwareMap.servo.get("pixelRightServo");


        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");

        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP));

        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            double y = gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = -gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            // This button choice was made so that it is hard to hit on accident,
            // it can be freely changed based on preference.
            // The equivalent button is start on Xbox-style controllers.
            if (gamepad1.dpad_up) {
                imu.resetYaw();
            }

            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            // Rotate the movement direction counter to the bot's rotation
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            rotX = rotX * 1.1;  // Counteract imperfect strafing

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower * 0.7);
            backLeftMotor.setPower(backLeftPower * 0.7);
            frontRightMotor.setPower(frontRightPower * 0.7);
            backRightMotor.setPower(backRightPower * 0.7);

            if (gamepad1.left_trigger > 0) {
                armMotor.setPower(gamepad1.left_trigger * 0.35);
            }

            else if (gamepad1.right_trigger > 0) {
                armMotor.setPower(gamepad1.right_trigger * -0.35);
            }

            if (gamepad1.left_bumper == true) {
                pixelMotor.setPower(0.7);

            }

            else if (gamepad1.right_bumper == true) {
                pixelMotor.setPower(0);
            }

            if (gamepad1.a) {
                gripperLeftServo.setPosition(0.475);
                gripperRightServo.setPosition(0.525);
                }

            else if (gamepad1.y) {
                gripperLeftServo.setPosition(1);
                gripperRightServo.setPosition(0);
                    }

            else if (gamepad1.b) {

                pixelLeftServo.setPosition(1);
                pixelRightServo.setPosition(0);
            }

            else if (gamepad1.x) {
                pixelLeftServo.setPosition(0.41);
                pixelRightServo.setPosition(0.59);

            }

        }
    }
}