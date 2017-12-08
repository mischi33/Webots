public class ProportionalDriveTowardsLight extends RobotController {


    public ProportionalDriveTowardsLight() {
        super();
        String[] sensorNames = new String[]{"ls6", "ls7", "ls0", "ls1"};
        initLightSensors(sensorNames);
    }


    public void run() {
        while (step(TIME_STEP) != -1) {
            double left = calcSpeed(new String[] {"ls6", "ls7"});
            double right = calcSpeed(new String[] {"ls0", "ls1"});
            setSpeed(left, right);
        }

    }

    private double calcSpeed(String[] sensors) {
        double speed = 0;
        for (String sensor : sensors) {
            speed = speed + 0.5 * getLightSensor(sensor).getValue();
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
