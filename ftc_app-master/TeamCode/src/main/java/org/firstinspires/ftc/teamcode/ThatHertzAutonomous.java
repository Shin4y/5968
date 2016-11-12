package org.firstinspires.ftc.teamcode;

/**
 * Created by Sa'id on 11/6/2016.
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DeviceManager;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.hardware.LegacyModule;

@TeleOp(name = "ThatHertzAutonomous", group = "ThatHertz")
public class ThatHertzAutonomous extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    //For wheels
    private DcMotor frontLeftMotor = null;
    private DcMotor frontRightMotor = null;
    private DcMotor backLeftMotor = null;
    private DcMotor backRightMotor = null;

    //For shooting mechanism
//    private DcMotor catchWheel = null;
//    private DcMotor midWheel = null;
//    private DcMotor shootRight = null;
//    private DcMotor shootLeft = null;
//    private Servo pipeAnchor = null;

    //for sensor
    private LegacyModule legacyModule = null;
    private UltrasonicSensor ultrasonicLeft = null;
    private UltrasonicSensor ultrasonicRight = null;
    private double ultrasonicDifference = 0.0;


    //code to run on init
    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");

        //for wheels
        frontLeftMotor = hardwareMap.dcMotor.get("f_l_m");
        frontRightMotor = hardwareMap.dcMotor.get("f_r_m");
        backLeftMotor = hardwareMap.dcMotor.get("b_l_m");
        backRightMotor = hardwareMap.dcMotor.get("b_r_m");

        //for shooting mechanism
//        catchWheel = hardwareMap.dcMotor.get("catch_wheel");
//        midWheel = hardwareMap.dcMotor.get("mid_wheel");
//        shootRight = hardwareMap.dcMotor.get("shoot_right");
//        shootLeft = hardwareMap.dcMotor.get("shoot_left");
//        pipeAnchor = hardwareMap.servo.get("pipe_string");

        //make sure all motors spin in the same direction
        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);

        //sensors
        legacyModule = hardwareMap.legacyModule.get("legacy");
        legacyModule.enable9v(4, true);
        legacyModule.enable9v(5, true);
        ultrasonicLeft = hardwareMap.ultrasonicSensor.get("ultrasonic_l");
        ultrasonicRight = hardwareMap.ultrasonicSensor.get("ultrasonic_r");


        telemetry.addData("Status", "Initialized");
    }


    //code to run once start is pressed (instantaneous)
    @Override
    public void start() {runtime.reset();}


    //main code that will run during play
    @Override
    public void loop() {
        boolean wereGood = true;
        ultrasonicDifference = ultrasonicLeft.getUltrasonicLevel() - ultrasonicRight.getUltrasonicLevel(); //good to make sure our robot is parallel to the wall
        while(wereGood) {
            while(Math.abs(ultrasonicDifference) <= 1 && ultrasonicRight.getUltrasonicLevel() < 160) {
                backLeftMotor.setPower(.5);
                backRightMotor.setPower(.5);
                frontLeftMotor.setPower(.5);
                frontRightMotor.setPower(.5);
            }
            while(ultrasonicDifference > 1) {
                backRightMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(.5);
                frontLeftMotor.setPower(.5);
            }
            while(ultrasonicDifference < -1) {
                backRightMotor.setPower(.5);
                frontRightMotor.setPower(.5);
                backLeftMotor.setPower(0);
                frontLeftMotor.setPower(0);
            }
            if(ultrasonicRight.getUltrasonicLevel() > 160)
            {
                wereGood = false;
            }
        }
    }
}
