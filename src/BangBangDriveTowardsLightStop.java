public class BangBangDriveTowardsLightStop extends BangBangLightController {
    private static int STOP_VALUE = 350;
    private static int STOP_VALUE_2 = 1500;

    public BangBangDriveTowardsLightStop(){
        super();
        initLightSensors(new String[]{"ls6", "ls7", "ls0", "ls1"});
    }

    public static void main(String[] args) {
        BangBangDriveTowardsLightStop controller = new BangBangDriveTowardsLightStop();
        controller.run();
    }

    public void run() {
        int left = 0;
        int frontLeft = 1;
        int frontRight = 2;
        int right = 3;
        while (step(TIME_STEP) != -1) {
            if ((lightSensors[frontLeft].getValue() < STOP_VALUE || lightSensors[frontRight].getValue() < STOP_VALUE)
                    && (lightSensors[left].getValue() > STOP_VALUE_2 && lightSensors[right].getValue() > STOP_VALUE_2)) {
                stopDriving();
            } else {
                driveToLight();
            }
        }
    }
}
