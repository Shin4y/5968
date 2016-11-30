package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.LegacyModule;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Sa'id on 11/19/2016.
 */
@TeleOp(name = "ThatHertz Sensor Test", group = "Test")
public class ThatHertzSensorTest extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private UltrasonicSensor ultrasonicLeft = null;
    private UltrasonicSensor ultrasonicRight = null;
    private LegacyModule legacy = null;
    private DcMotor frontLeftMotor = null;
    private DcMotor frontRightMotor = null;
    private DcMotor backLeftMotor = null;
    private DcMotor backRightMotor = null;
    private double ultrasonicDifference = 0.0;

    @Override
    public void init() {
        ultrasonicLeft = hardwareMap.ultrasonicSensor.get("ultrasonic_l");
        ultrasonicRight = hardwareMap.ultrasonicSensor.get("ultrasonic_r");
        legacy = hardwareMap.legacyModule.get("legacy");

        legacy.enable9v(4, true);
        legacy.enable9v(5, true);

        frontLeftMotor = hardwareMap.dcMotor.get("f_l_m");
        frontRightMotor = hardwareMap.dcMotor.get("f_r_m");
        backLeftMotor = hardwareMap.dcMotor.get("b_l_m");
        backRightMotor = hardwareMap.dcMotor.get("b_r_m");

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
    }


    @Override
    public void start() {runtime.reset();}


    @Override
    public void loop() {
        boolean notForward = true;
        boolean notTurned = true;
        boolean notNearBeacon = true;

        if (notForward) {
            ultrasonicDifference = ultrasonicRight.getUltrasonicLevel() - ultrasonicLeft.getUltrasonicLevel();
            if (Math.abs(ultrasonicDifference) < 5 && ultrasonicRight.getUltrasonicLevel() < 61) {
                frontLeftMotor.setPower(.1);
                frontRightMotor.setPower(.1);
                backRightMotor.setPower(.1);
                backLeftMotor.setPower(.1);
            } else if (ultrasonicDifference >= 5) {
                backLeftMotor.setPower(.05);
                frontLeftMotor.setPower(.05);
                frontRightMotor.setPower(-.05);
                backRightMotor.setPower(-.05);
            } else if (ultrasonicDifference <= -5) {
                backLeftMotor.setPower(-.05);
                frontLeftMotor.setPower(-.05);
                frontRightMotor.setPower(.05);
                backRightMotor.setPower(.05);
            } else {
                backLeftMotor.setPower(0);
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(-0);
                backRightMotor.setPower(-0);
                notForward = false;
            }
        }

//        if(notTurned) {
//
//        }

        telemetry.addData("Ultrasonic Sensor Left", ultrasonicLeft.getUltrasonicLevel() + "");
        telemetry.addData("Ultrasonic Sensor Right", ultrasonicRight.getUltrasonicLevel() + "");
        telemetry.addData("Ultrasonic Difference", ultrasonicDifference + "");
    }
}