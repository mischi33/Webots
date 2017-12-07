public class BangBangDriveTowardsLight extends LightController {


    public BangBangDriveTowardsLight(){
        super();
        initLightSensors(new String[]{"ls6", "ls7", "ls0", "ls1"});
    }

    public static void main(String[] args) {
        BangBangDriveTowardsLight controller = new BangBangDriveTowardsLight();
        controller.run();
    }


    public void run() {
        while (step(TIME_STEP) != -1) {
           driveToLight();
        }
    }
}
