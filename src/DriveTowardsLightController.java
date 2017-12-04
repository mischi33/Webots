public class DriveTowardsLightController extends RobotController {
    //    private static double[][] controllerMatrix = {{0.5, 0.5, 0.5, 0.5},
//            {0.5, 0.5, 0.5, 0.5}};


    private static double[] controllerMatrix = {0.5, 0.5};

    public DriveTowardsLightController() {
        super();
        String[] sensorNames = new String[]{"ls6", "ls7", "ls0", "ls1"};
        initLightSensors(sensorNames);
    }


    private void run() {
        while (step(TIME_STEP) != -1) {
//            double[] speed = calcSpeed();
//            setSpeedValues(speed[0], speed[1]);
            double left = calcSpeed(new String[] {"ls6", "ls7"});
            double right = calcSpeed(new String[] {"ls0", "ls1"});
            setSpeedValues(left, right);
        }

    }

    private void setSpeedValues(double left, double right) {
        setSpeed(left, right);
    }

//    private double[] calcSpeed() {
//        double[] result = new double[controllerMatrix.length];
//        for (int i = 0; i < controllerMatrix.length; i++) {
//            double speed = 0;
//            for (int j = 0; j < controllerMatrix[i].length; j++) {
//                speed = speed + controllerMatrix[i][j] * lightSensors[i].getValue();
//            }
//            if (speed > 1000) {
//                speed = 1000;
//            }
//            result[i] = speed;
//        }
//        return result;
//    }

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
