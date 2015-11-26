package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by Sa'id on 10/28/2015.
 */
public class testOpMode extends K9TeleOp{

    @Override
    public void init() {

        DcMotor motorRight = hardwareMap.dcMotor.get("motor_1");
        DcMotor motorLeft = hardwareMap.dcMotor.get("motor_2");

        float deltaMotorR;
        float deltaMotorL;

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

    }

}
