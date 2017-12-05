public class DriveTowardsLightStopController extends RobotController {

    /**
     * TODO WIE WIE WIRD DER WERT 0 ????????
     */
    private static double[] controllerMatrix = {0.5, 0.5};

    public DriveTowardsLightStopController() {
        super();
        initLightSensors(new String[]{"ls6", "ls7", "ls0", "ls1"});
    }

    public static void main(String args[]) {
        DriveTowardsLightStopController controller = new DriveTowardsLightStopController();
        controller.run();
    }

    private void run() {
        while (step(TIME_STEP) != -1) {
            double left = calcSpeed(new String[]{"ls6", "ls7"});
            double right = calcSpeed(new String[]{"ls0", "ls1"});
            setSpeedValues(left, right);
        }

    }

    private void setSpeedValues(double left, double right) {
        setSpeed(left, right);
    }


    private double calcSpeed(String[] sensors) {
        double speed = 0;
        for (int i = 0; i < sensors.length; i++) {
            speed = speed + getLightSensor(sensors[i]).getValue() / controllerMatrix[i] - 500;
        }
        if (speed > 1000) {
            speed = 1000;
        }
        return speed;
    }
}
