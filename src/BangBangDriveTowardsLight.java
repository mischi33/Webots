public class BangBangDriveTowardsLight extends BangBangController {
    protected static int MAX_SENSOR_VALUE = 1300;
    protected static int MAX_FRONT_SENSOR_VALUE = 400;

    public BangBangDriveTowardsLight(){
        super();
        initLightSensors(new String[]{"ls6", "ls7", "ls0", "ls1"});
    }

    public static void main(String[] args) {
        BangBangDriveTowardsLight controller = new BangBangDriveTowardsLight();
        controller.run();
    }


    public void run() {
        int left = 0;
        int frontLeft = 1;
        int frontRight = 2;
        int right = 3;
        while (step(TIME_STEP) != -1) {
            if (lightSensors[frontLeft].getValue() < MAX_FRONT_SENSOR_VALUE &&
                    lightSensors[frontRight].getValue() < MAX_FRONT_SENSOR_VALUE) {
                driveStraightAhead();
            } else if (lightSensors[left].getValue() < MAX_SENSOR_VALUE) {
                driveToLeft();
            } else if (lightSensors[right].getValue() < MAX_SENSOR_VALUE) {
                driveToRight();
            } else {
                driveStraightAhead();
            }
        }
    }
}
