public abstract class BangBangLightController extends BangBangController {
    private static int MAX_SENSOR_VALUE = 1300;
    private static int MAX_FRONT_SENSOR_VALUE = 400;

    protected void driveToLight() {
        int left = 0;
        int frontLeft = 1;
        int frontRight = 2;
        int right = 3;
        if (lightSensors[frontLeft].getValue() < MAX_FRONT_SENSOR_VALUE &&
                lightSensors[frontRight].getValue() < MAX_FRONT_SENSOR_VALUE) {
            driveStraightAhead();
        } else if (lightSensors[left].getValue() < MAX_SENSOR_VALUE) {
            driveToLeft();
        } else if (lightSensors[right].getValue() < MAX_SENSOR_VALUE) {
            driveToRight();
        } else {
            driveStraightAhead();
        }
    }
}
