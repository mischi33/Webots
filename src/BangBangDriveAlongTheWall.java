public class BangBangDriveAlongTheWall extends BangBangController {

    public BangBangDriveAlongTheWall() {
        super();
        initDistanceSensors(new String[]{"ps0", "ps1", "ps2", "ps7"});
    }

    public static void main(String[] args) {
        BangBangDriveAlongTheWall controller = new BangBangDriveAlongTheWall();
        controller.run();
    }

    public void run() {
        int turn = 2000;
        int maxFrontValue = 200;
        int minFrontValue = 60;
        int driveStraightMin = 1500;
        int driveStraightMax = 1000;
        boolean atFirstWall = false;


        while (step(TIME_STEP) != -1) {
            while (step(TIME_STEP) != -1 && !atFirstWall) {
                if (getDistanceSensor("ps0").getValue() < maxFrontValue && getDistanceSensor("ps7").getValue() < maxFrontValue) {
                    driveStraightAhead();
                } else if (getDistanceSensor("ps2").getValue() < turn) {
                    while (step(TIME_STEP) != -1 && getDistanceSensor("ps2").getValue() < turn) {
                        driveToLeft();
                    }
                    atFirstWall = true;
                }
            }
            while (step(TIME_STEP) != -1) {
                if (getDistanceSensor("ps1").getValue() < driveStraightMax
                        && getDistanceSensor("ps1").getValue() > driveStraightMin
                        && getDistanceSensor("ps0").getValue() < maxFrontValue) {
                    driveStraightAhead();
                } else if (getDistanceSensor("ps1").getValue() > driveStraightMin) {
                    driveToLeft();
                } else if (getDistanceSensor("ps1").getValue() < driveStraightMax) {
                    driveToRight();
                } else {
                    while (step(TIME_STEP) != -1 && getDistanceSensor("ps0").getValue() > minFrontValue) {
                        driveToLeft();
                    }
                }

            }
        }
    }


}
