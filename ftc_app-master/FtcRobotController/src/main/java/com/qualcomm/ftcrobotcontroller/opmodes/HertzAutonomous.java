/**
 * Autonomous Mode
 * <p>
 * Enables control of the robot via the gamepad
 */

package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class HertzAutonomous extends K9TeleOp {

    DcMotor rightMotors, leftMotors;

    public HertzAutonomous() {

    }

    // Code to run when the op mode is initialized goes here
    @Override
    public void init() {


		/*
		 * Use the hardwareMap to get the dc motors and servos by name. Note
		 * that the names of the devices must match the names used when you
		 * configured your robot and created the configuration file.
		 */

        //WHEELS ---
        leftMotors = hardwareMap.dcMotor.get("motor_1");
        rightMotors = hardwareMap.dcMotor.get("motor_2");
        leftMotors.setDirection(DcMotor.Direction.REVERSE);
        //WHEELS END ---
    }

    // This method will be called repeatedly in a loop
    @Override
    public void loop() {
        // Available mappable buttons:
        // left_stick_y/x, right_stick_y/x, a, b, x, y, left_trigger/bumper, right_trigger/bumper
        // left_stick_button, right_stick_button, dpad_up/down/left/right

        // throttle: left_stick_y ranges from -1 to 1, where -1 is full up, and 1 is full down
        // direction: left_stick_x ranges from -1 to 1, where -1 is full left and 1 is full right
        float throttle = -gamepad1.left_stick_y;
        float direction = gamepad1.left_stick_x;
        float right = throttle - direction;
        float left = throttle + direction;

        // clip and then scale the power values
        right = Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);
        right = (float)scaleInput(right);
        left =  (float)scaleInput(left);
        // write the values to the motors
        rightMotors.setPower(right);
        leftMotors.setPower(left);

		/*
		 * Send telemetry data back to driver station. Note that if we are using
		 * a legacy NXT-compatible motor controller, then the getPower() method
		 * will return a null value. The legacy NXT-compatible motor controllers
		 * are currently write only.
		 */
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("left tgt pwr",  "left  pwr: " + String.format("%.2f", left));
        telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", right));

    }

    //Code to run when the op mode is first disabled goes here
    @Override
    public void stop() {

    }
}
//Lest's Meme