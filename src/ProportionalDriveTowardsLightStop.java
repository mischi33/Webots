public class ProportionalDriveTowardsLightStop extends RobotController {

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
            double right = calcSpeed("ls7");
            double left = calcSpeed("ls0");
            setSpeedValues(left, right);
        }

    }

    private void setSpeedValues(double left, double right) {
        setSpeed(left, right);
    }


    private double calcSpeed(String name) {
        double speed = 360.0 - getLightSensor(name).getValue();
        speed = speed * 3;
        return (speed > 1000) ? 1000 : speed;
    }
}
