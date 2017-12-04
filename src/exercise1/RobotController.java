package exercise1;

import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;

public class RobotController extends DifferentialWheels {
    protected LightSensor[] lightSensors;
    protected DistanceSensor[] distanceSensors;

    protected static int FRONT_RIGHT = 0;
    protected static int FRONT_MIDDLE_RIGHT = 1;
    protected static int RIGHT = 2;
    protected static int BACK_RIGHT = 3;
    protected static int BACK_LEFT = 4;
    protected static int LEFT = 5;
    protected static int FRONT_MIDDLE_LEFT = 6;
    protected static int FRONT_LEFT = 7;

    protected static int TIME_STEP = 15;

    protected void initLightSensors() {
        lightSensors = new LightSensor[] { getLightSensor("ls0"), getLightSensor("ls1"),
                getLightSensor("ls2"), getLightSensor("ls3"),
                getLightSensor("ls4"), getLightSensor("ls5"),
                getLightSensor("ls6"), getLightSensor("ls7") };

        for (LightSensor lightSensor : lightSensors) {
            lightSensor.enable(10);
        }
    }

    protected void initDistanceSensors() {
        distanceSensors = new DistanceSensor[] { getDistanceSensor("ps0"), getDistanceSensor("ps1"),
                getDistanceSensor("ps2"), getDistanceSensor("ps3"),
                getDistanceSensor("ps4"), getDistanceSensor("ps5"),
                getDistanceSensor("ps6"), getDistanceSensor("ps7") };

        for (LightSensor lightSensor : lightSensors) {
            lightSensor.enable(10);
        }
    }
}
