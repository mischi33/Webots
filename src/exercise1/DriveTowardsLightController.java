package exercise1;

import com.cyberbotics.webots.controller.LightSensor;
import exercise2.DriveTowardsLight;

public class DriveTowardsLightController extends RobotController {
    private static int MAX_SENSOR_VALUE = 1300;
    private static int MAX_FRONT_SENSOR_VALUE = 400;

    public DriveTowardsLightController() {
        super();
        initLightSensors();
    }


    private void run() {


    }

    private int calcLeftSpeed(){
        return 0;
    }
    private int calcRightSpeed(){
        return 0;
    }
}
