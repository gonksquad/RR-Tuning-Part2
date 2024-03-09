package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Ploop {
    public Hardware hardware;

    public Ploop(HardwareMap hardwareMap) {
        hardware = new Hardware(hardwareMap);
    }

    public boolean pLoop(Float yaw, double x, double z, Telemetry telemetry) {

        double FrontLeftPower = 0;
        double FrontRightPower = 0;
        double BackLeftPower = 0;
        double BackRightPower = 0;

        double spinPower = -0.02*yaw;
        double strafePower = 0.7*x;
        double straightPower = 0.5*(z-2);

        if (yaw < -1 || yaw > 1) { // spin
            FrontLeftPower += spinPower;
            FrontRightPower -= spinPower;
            BackLeftPower += spinPower;
            BackRightPower -= spinPower;
            telemetry.addData("Spin power: ", spinPower);
        } if (x > 0.1 || x < -0.1) {
            FrontLeftPower -= strafePower;
            FrontRightPower += strafePower;
            BackLeftPower += strafePower;
            BackRightPower -= strafePower;
            telemetry.addData("Strafe power: ", strafePower);
        } if (z > 2.05 || z < 1.95) {
            FrontLeftPower += straightPower;
            FrontRightPower += straightPower;
            BackLeftPower += straightPower;
            BackRightPower += straightPower;
            telemetry.addData("Straight power: ", straightPower);
        }


        hardware.leftFront.setPower(FrontLeftPower);
        hardware.rightFront.setPower(FrontRightPower);
        hardware.leftRear.setPower(BackLeftPower);
        hardware.rightRear.setPower(BackRightPower);
        telemetry.update();

        return((yaw > -1 && yaw < 1) && (x < 0.1 && x > -0.1) && (z < 2.1 && z > 1.9));
    }
}
