public class ProportionalDriveTowardsLight extends ProportionalController {


    private static double controllerValue = 0.5;

    public ProportionalDriveTowardsLight() {
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

    private double calcSpeed(String[] sensors) {
        double speed = 0;
        for (String sensor : sensors) {
            speed = speed + controllerValue * getLightSensor(sensor).getValue();
        }
        if (speed > 1000) {
            speed = 1000;
        }
        return speed;
    }

    public static void main(String[] args) {
        ProportionalDriveTowardsLight controller = new ProportionalDriveTowardsLight();
        controller.run();
    }
}
