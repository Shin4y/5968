/**
 * Autonomous Mode
 * <p>
 * Enables control of the robot via the gamepad
 */

package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Autonomous Mode
 * <p>
 * Enables autonomous control over the robot
 *
 * Written by: Sa'id Kharboutli
 */

public class HertzAutonomous extends LinearOpMode {

    DcMotor rightMotors, leftMotors;
    double rightPower, leftPower;

    public void runOpMode() throws InterruptedException {
        rightMotors = hardwareMap.dcMotor.get("motors_right");
        leftMotors = hardwareMap.dcMotor.get("motors_left");
        leftMotors.setDirection(DcMotor.Direction.REVERSE);

        leftMotors.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        leftMotors.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        while (leftMotors.getCurrentPosition() != 0) {
            leftMotors.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            waitOneFullHardwareCycle();
        }
        waitForStart();

        Thread.sleep(10);
        while (opModeIsActive()) {
            leftMotors.setTargetPosition(2880);
            leftMotors.setPower(1);
            rightMotors.setPower(1);

            while (leftMotors.getCurrentPosition() != 2880) {
                waitOneFullHardwareCycle();
            }

            leftMotors.setTargetPosition(4320);
            leftMotors.setPower(0.5);
            rightMotors.setPower(-0.5);

            while (leftMotors.getCurrentPosition() != 5760) {
                waitOneFullHardwareCycle();
            }

            leftMotors.setTargetPosition(8640);
            leftMotors.setPower(1);
            rightMotors.setPower(1);

            while (leftMotors.getCurrentPosition() != 8640) {
                waitOneFullHardwareCycle();
            }

            leftMotors.setPower(0);
            rightMotors.setPower(0);

            waitOneFullHardwareCycle();

            telemetry.addData("Autonomous:", " Finished");

        }
    }
}
//Lest's Meme