

import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.LightSensor;


public class DriveTowardsLightStop extends DriveController {
    private static int STOP_VALUE = 350;
    private static int STOP_VALUE_2 = 1500;
    protected static int MAX_SENSOR_VALUE = 1300;
    protected static int MAX_FRONT_SENSOR_VALUE = 400;

    public DriveTowardsLightStop(){
        super();
        initLightSensors(new String[]{"ls6", "ls7", "ls0", "ls1"});
    }

    public static void main(String[] args) {
        DriveTowardsLightStop controller = new DriveTowardsLightStop();
        controller.run();
    }

    public void run() {
        int left = 0;
        int frontLeft = 1;
        int frontRight = 2;
        int right = 3;
        while (step(TIME_STEP) != -1) {
            if ((lightSensors[frontLeft].getValue() < STOP_VALUE || lightSensors[frontRight].getValue() < STOP_VALUE)
                    && (lightSensors[left].getValue() > STOP_VALUE_2 && lightSensors[right].getValue() > STOP_VALUE_2)) {
                stopDriving();
            } else if (lightSensors[frontLeft].getValue() < MAX_FRONT_SENSOR_VALUE &&
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
}
