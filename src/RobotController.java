

import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;

public class RobotController extends DifferentialWheels {
    protected LightSensor[] lightSensors;
    protected DistanceSensor[] distanceSensors;

    protected static int TIME_STEP = 15;

    protected void initLightSensors(String[] sensorNames) {
        lightSensors = new LightSensor[sensorNames.length];
        for (int i = 0; i < sensorNames.length; i++) {
            lightSensors[i] = getLightSensor(sensorNames[i]);
        }

        for (LightSensor lightSensor : lightSensors) {
            lightSensor.enable(10);
        }
    }

    protected void initDistanceSensors(String[] sensorNames) {
        for (int i = 0; i < sensorNames.length; i++) {
            distanceSensors[i] = getDistanceSensor(sensorNames[i]);
        }

        for (DistanceSensor distanceSensor : distanceSensors) {
            distanceSensor.enable(10);
        }
    }
}
