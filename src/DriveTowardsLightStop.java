

import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.LightSensor;


public class DriveTowardsLightStop extends DriveController {
    private static int STOP_VALUE = 350;
    private static int STOP_VALUE_2 = 1500;

    private LightSensor[] lightSensors;

    public DriveTowardsLightStop(){
        super();
        lightSensors = new LightSensor[] { getLightSensor("ls0"), getLightSensor("ls1"),
                getLightSensor("ls2"), getLightSensor("ls3"),
                getLightSensor("ls4"), getLightSensor("ls5"),
                getLightSensor("ls6"), getLightSensor("ls7") };

        for (LightSensor lightSensor : lightSensors) {
            lightSensor.enable(10);
        }
    }

    public static void main(String[] args) {
        DriveTowardsLightStop controller = new DriveTowardsLightStop();
        controller.run();
    }

    public void run() {
        while (step(TIME_STEP) != -1) {
            if ((lightSensors[FRONT_LEFT].getValue() < STOP_VALUE || lightSensors[FRONT_RIGHT].getValue() < STOP_VALUE)
                    && (lightSensors[FRONT_MIDDLE_LEFT].getValue() > STOP_VALUE_2 && lightSensors[FRONT_MIDDLE_RIGHT].getValue() > STOP_VALUE_2)) {
                stopDriving();
            } else if (lightSensors[FRONT_LEFT].getValue() < MAX_FRONT_SENSOR_VALUE &&
                    lightSensors[FRONT_RIGHT].getValue() < MAX_FRONT_SENSOR_VALUE) {
                driveStraightAhead();
            } else if (lightSensors[FRONT_MIDDLE_LEFT].getValue() < MAX_SENSOR_VALUE
                    || lightSensors[LEFT].getValue() < MAX_SENSOR_VALUE
                    || lightSensors[BACK_LEFT].getValue() < MAX_SENSOR_VALUE) {
                driveToLeft();
            } else if (lightSensors[FRONT_MIDDLE_RIGHT].getValue() < MAX_SENSOR_VALUE ||
                    lightSensors[RIGHT].getValue() < MAX_SENSOR_VALUE ||
                    lightSensors[BACK_RIGHT].getValue() < MAX_SENSOR_VALUE) {
                driveToRight();
            } else {
                driveStraightAhead();
            }
        }
    }
}
