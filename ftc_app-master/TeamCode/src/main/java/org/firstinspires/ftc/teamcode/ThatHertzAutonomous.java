package org.firstinspires.ftc.teamcode;

/**
 * Created by Sa'id on 11/6/2016.
 */

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "ThatHertzAutonomous", group = "ThatHertz")
public class ThatHertzAutonomous extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    //For wheels
    private DcMotor frontLeftMotor = null;
    private DcMotor frontRightMotor = null;
    private DcMotor backLeftMotor = null;
    private DcMotor backRightMotor = null;

    //For shooting mechanism
    private DcMotor catchWheel = null;
    private DcMotor midWheel = null;
    private DcMotor shootRight = null;
    private DcMotor shootLeft = null;
    private Servo pipeAnchor = null;

    //code to run on init
    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");

        //For wheels
        frontLeftMotor = hardwareMap.dcMotor.get("f_l_m");
        frontRightMotor = hardwareMap.dcMotor.get("f_r_m");
        backLeftMotor = hardwareMap.dcMotor.get("b_l_m");
        backRightMotor = hardwareMap.dcMotor.get("b_r_m");

        //For shooting mechanism
        catchWheel = hardwareMap.dcMotor.get("catch_wheel");
        midWheel = hardwareMap.dcMotor.get("mid_wheel");
        shootRight = hardwareMap.dcMotor.get("shoot_right");
        shootLeft = hardwareMap.dcMotor.get("shoot_left");
        pipeAnchor = hardwareMap.servo.get("pipe_string");
        //make sure all motors spin in the same direction (check to see if it works fine as is)
//        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
//        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
//        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
//        backRightMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
    }


    //code to run once start is pressed (instantaneous)
    @Override
    public void start() {runtime.reset();}


    //Main code that will run during play
    @Override
    public void loop() {
        
    }
}
