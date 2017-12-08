public class BangBangDriveTowardsLightStop extends BangBangLightController {

    public BangBangDriveTowardsLightStop(){
        super();
        initLightSensors(new String[]{"ls6", "ls7", "ls0", "ls1"});
    }

    public static void main(String[] args) {
        BangBangDriveTowardsLightStop controller = new BangBangDriveTowardsLightStop();
        controller.run();
    }

    public void run() {
        int stopValueFront = 350;
        int stopValueSide = 1500;
        int left = 0;
        int frontLeft = 1;
        int frontRight = 2;
        int right = 3;

        while (step(TIME_STEP) != -1) {
            if ((getLightSensor("ls7").getValue() < stopValueFront || getLightSensor("ls0").getValue() < stopValueFront)
                    && (getLightSensor("ls6").getValue() > stopValueSide && getLightSensor("ls1").getValue() > stopValueSide)) {
                stopDriving();
            } else {
                driveToLight();
            }
        }
    }
}
