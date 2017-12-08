public abstract class BangBangLightController extends BangBangController {

    protected void driveToLight() {
        int maxFrontSensorValue = 400;
        int maxSideSensorValue = 1300;
        if (getLightSensor("ls7").getValue() < maxFrontSensorValue &&
                getLightSensor("ls0").getValue() < maxFrontSensorValue) {
            driveStraightAhead();
        } else if (getLightSensor("ls6").getValue() < maxSideSensorValue) {
            driveToLeft();
        } else if (getLightSensor("ls1").getValue() < maxSideSensorValue) {
            driveToRight();
        } else {
            driveStraightAhead();
        }
    }
}
