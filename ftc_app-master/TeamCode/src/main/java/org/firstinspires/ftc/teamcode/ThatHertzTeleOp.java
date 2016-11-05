package org.firstinspires.ftc.teamcode;

    import com.qualcomm.robotcore.eventloop.opmode.Disabled;
    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
    import com.qualcomm.robotcore.eventloop.opmode.OpMode;
    import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.DcMotorSimple;
    import com.qualcomm.robotcore.hardware.Servo;
    import com.qualcomm.robotcore.util.ElapsedTime;

    import java.text.SimpleDateFormat;
    import java.util.Date;

@TeleOp(name = "[5968] That Hertz TeleOp", group = "ThatHertz")

public class ThatHertzTeleOp extends OpMode {
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
        telemetry.addData("Status", "Running: " + runtime.toString());
        //SHOOTING
        if(gamepad1.dpad_down) {
            //Let go
        } else if(gamepad1.dpad_up) {
            //Pull up
        }
        if(gamepad1.a) {
            if(catchWheel.getPower() == 0) {
                catchWheel.setPower(1);
                midWheel.setPower(1);
                shootLeft.setPower(1);
                shootRight.setPower(1);
            } else {
                catchWheel.setPower(0);
                midWheel.setPower(0);
                shootLeft.setPower(0);
                shootRight.setPower(0);
            }
        }

        //MOVING
        if(gamepad1.dpad_left) {
            //move left
            frontLeftMotor.setPower(1);
            backRightMotor.setPower(1);
            frontRightMotor.setPower(-1);
            backLeftMotor.setPower(-1);
        } else if(gamepad1.dpad_right) {
            //move right
            frontLeftMotor.setPower(-1);
            backRightMotor.setPower(-1);
            frontRightMotor.setPower(1);
            backLeftMotor.setPower(1);
        } else {
            frontLeftMotor.setPower(-gamepad1.left_stick_y);
            backLeftMotor.setPower(-gamepad1.left_stick_y);
            frontRightMotor.setPower(-gamepad1.right_stick_y);
            backRightMotor.setPower(-gamepad1.right_stick_y);
        }

    }
}