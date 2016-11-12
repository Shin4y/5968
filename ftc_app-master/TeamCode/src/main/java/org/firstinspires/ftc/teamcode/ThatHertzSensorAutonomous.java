package org.firstinspires.ftc.teamcode;

/**
 * Created by Sa'id on 11/12/2016.
 */

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.hardware.LegacyModule;

@Autonomous(name = "[5968] ThatHertz TeleOp", group = "TeleOp")
public class ThatHertzSensorAutonomous extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    //for wheels
    private DcMotor frontLeftMotor = null;
    private DcMotor frontRightMotor = null;
    private DcMotor backLeftMotor = null;
    private DcMotor backRightMotor = null;

    //for sensors
    private LegacyModule legacyModule = null;
    private UltrasonicSensor ultrasonicLeft = null;
    private UltrasonicSensor ultrasonicRight = null;
    private double ultrasonicDifference = 0.0;


    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");

        //For wheels
        frontLeftMotor = hardwareMap.dcMotor.get("f_l_m");
        frontRightMotor = hardwareMap.dcMotor.get("f_r_m");
        backLeftMotor = hardwareMap.dcMotor.get("b_l_m");
        backRightMotor = hardwareMap.dcMotor.get("b_r_m");

        //for sensors
        legacyModule.enable9v(4, true);
        legacyModule.enable9v(5, true);
        ultrasonicLeft = hardwareMap.ultrasonicSensor.get("ultrasonic_l");
        ultrasonicRight = hardwareMap.ultrasonicSensor.get("ultrasonic_r");

        //make sure all motors spin in the same direction
        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
    }


    //code to run once start is pressed (instantaneous)
    @Override
    public void start() {
        runtime.reset();
    }


    //Main code that will run during play
    @Override
    public void loop() {
        boolean wereGood = true;
        while (wereGood) {
            ultrasonicDifference = ultrasonicLeft.getUltrasonicLevel() - ultrasonicRight.getUltrasonicLevel();
            while (Math.abs(ultrasonicDifference) <= 2.0 && ultrasonicRight.getUltrasonicLevel() < 160) {
                backLeftMotor.setPower(0.3);
                backRightMotor.setPower(0.3);
                frontLeftMotor.setPower(0.3);
                frontRightMotor.setPower(0.3);
                ultrasonicDifference = ultrasonicLeft.getUltrasonicLevel() - ultrasonicRight.getUltrasonicLevel();
            }
            while(ultrasonicDifference > 2) {
                backLeftMotor.setPower(0.0);
                frontLeftMotor.setPower(0.0);
                backRightMotor.setPower(0.2);
                frontRightMotor.setPower(0.2);
                ultrasonicDifference = ultrasonicLeft.getUltrasonicLevel() - ultrasonicRight.getUltrasonicLevel();
            }
            while(ultrasonicDifference < -2) {
                backLeftMotor.setPower(0.2);
                frontLeftMotor.setPower(0.2);
                backRightMotor.setPower(0.0);
                frontRightMotor.setPower(0.0);
                ultrasonicDifference = ultrasonicLeft.getUltrasonicLevel() - ultrasonicRight.getUltrasonicLevel();
            }
            if(ultrasonicRight.getUltrasonicLevel() >= 160)
            {
                wereGood = false;
            }
        }
    }
}