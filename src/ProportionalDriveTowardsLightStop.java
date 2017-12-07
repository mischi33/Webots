public class ProportionalDriveTowardsLightStop extends RobotController {


    private static double controllerMatrix = 550.0;
    private static int MAX_SPEED = 100;

    public ProportionalDriveTowardsLightStop() {
        super();
        initLightSensors(new String[]{"ls6", "ls7", "ls0", "ls1"});
    }

    public static void main(String args[]) {
        ProportionalDriveTowardsLightStop controller = new ProportionalDriveTowardsLightStop();
        controller.run();
    }

    public void run() {
        while (step(TIME_STEP) != -1) {
            double left = calcSpeed(new String[]{"ls7"});
            double right = calcSpeed(new String[]{"ls0"});
            setSpeedValues(left, right);
        }

    }

    private void setSpeedValues(double left, double right) {
        setSpeed(left, right);
    }


    private double calcSpeed(String[] sensors) {
        double speed = 0;
        for (String name : sensors) {
            speed = controllerMatrix - getLightSensor(name).getValue() / 10.0;
        }
        return  (speed < MAX_SPEED) ? speed : MAX_SPEED;
    }

    /**
     *
     left_speed  = (1024 - ls0_value) / 10.0;
     left_speed = (left_speed < MAX_SPEED) ? left_speed : MAX_SPEED;
     right_speed = (1024 - ls1_value) / 10.0;
     right_speed = (right_speed < MAX_SPEED) ? right_speed : MAX_SPEED;

      Set the motor speeds.
      wb_differential_wheels_set_speed(left_speed, right_speed);
     */
}
