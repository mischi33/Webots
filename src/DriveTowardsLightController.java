public class DriveTowardsLightController extends RobotController {


    private static double[] controllerMatrix = {0.5, 0.5};

    public DriveTowardsLightController() {
        super();
        String[] sensorNames = new String[]{"ls6", "ls7", "ls0", "ls1"};
        initLightSensors(sensorNames);
    }


    private void run() {
        while (step(TIME_STEP) != -1) {
            double left = calcSpeed(new String[] {"ls6", "ls7"});
            double right = calcSpeed(new String[] {"ls0", "ls1"});
            setSpeedValues(left, right);
        }

    }

    private void setSpeedValues(double left, double right) {
        setSpeed(left, right);
    }


    private double calcSpeed(String[] sensors) {
        double speed = 0;
        for (int i = 0; i < sensors.length; i ++) {
            speed = speed + controllerMatrix[i] * getLightSensor(sensors[i]).getValue();
        }
        if (speed > 1000) {
            speed = 1000;
        }
        return speed;
    }

    public static void main(String[] args) {
        DriveTowardsLightController controller = new DriveTowardsLightController();
        controller.run();
    }
}
