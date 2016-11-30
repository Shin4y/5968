package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.text.SimpleDateFormat;
import java.util.Date;

@TeleOp(name = "[5968] That Hertz TeleOp", group = "TeleOp")
public class ThatHertzTeleOp extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    //for wheels
    private DcMotor frontLeftMotor = null;
    private DcMotor frontRightMotor = null;
    private DcMotor backLeftMotor = null;
    private DcMotor backRightMotor = null;

    //code to run on init
    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");

        //for wheels
        frontLeftMotor = hardwareMap.dcMotor.get("f_l_m");
        frontRightMotor = hardwareMap.dcMotor.get("f_r_m");
        backLeftMotor = hardwareMap.dcMotor.get("b_l_m");
        backRightMotor = hardwareMap.dcMotor.get("b_r_m");

        //For shooting mechanism
//        catchWheel = hardwareMap.dcMotor.get("catch_wheel");
//        midWheel = hardwareMap.dcMotor.get("mid_wheel");
//        shootRight = hardwareMap.dcMotor.get("shoot_right");
//        shootLeft = hardwareMap.dcMotor.get("shoot_left");
//        pipeAnchor = hardwareMap.servo.get("pipe_string");
        //make sure all motors spin in the same direction

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
    }


    //code to run once start is pressed (instantaneous)
    @Override
    public void start() {runtime.reset();}


    //Main code that will run during play
    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());

        //MOVING
        if(gamepad1.dpad_left) {
            //move left
            frontLeftMotor.setPower(-.4);
            backRightMotor.setPower(-.4);
            frontRightMotor.setPower(.4);
            backLeftMotor.setPower(.4);
        } else if(gamepad1.dpad_right) {
            //move right
            frontLeftMotor.setPower(.4);
            backRightMotor.setPower(.4);
            frontRightMotor.setPower(-.4);
            backLeftMotor.setPower(-.4);
        } else {

            if (gamepad1.left_bumper) {
                frontLeftMotor.setPower(-gamepad1.left_stick_y * .2);
                backLeftMotor.setPower(-gamepad1.left_stick_y * .2);
                frontRightMotor.setPower(-gamepad1.right_stick_y * .2);
                backRightMotor.setPower(-gamepad1.right_stick_y * .2);
            }
            else {
                frontLeftMotor.setPower(-gamepad1.left_stick_y);
                backLeftMotor.setPower(-gamepad1.left_stick_y);
                frontRightMotor.setPower(-gamepad1.right_stick_y);
                backRightMotor.setPower(-gamepad1.right_stick_y);
            }
        }

//        telemetry.addData("Collection Running", catchWheel.getPower() > 0);
        telemetry.addData("FL Power", frontLeftMotor.getPower());
        telemetry.addData("FR Power", frontRightMotor.getPower());
        telemetry.addData("BL Power", backLeftMotor.getPower());
        telemetry.addData("BR Power", backRightMotor.getPower());
    }
}