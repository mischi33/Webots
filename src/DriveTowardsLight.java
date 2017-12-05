

import com.cyberbotics.webots.controller.LightSensor;

public class DriveTowardsLight extends DriveController {
    private LightSensor[] lightSensors;

    public DriveTowardsLight(){
        super();
        lightSensors = new LightSensor[] { getLightSensor("ls0"), getLightSensor("ls1"),
                getLightSensor("ls2"), getLightSensor("ls3"),
                getLightSensor("ls4"), getLightSensor("ls5"),
                getLightSensor("ls6"), getLightSensor("ls7") };

        for (int i = 0; i < lightSensors.length; i++) {
            lightSensors[i].enable(10);
        }
    }

    public static void main(String[] args) {
        DriveTowardsLight controller = new DriveTowardsLight();
        controller.run();
    }


    public void run() {
        while (step(TIME_STEP) != -1) {
            if (lightSensors[FRONT_LEFT].getValue() < MAX_FRONT_SENSOR_VALUE &&
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
