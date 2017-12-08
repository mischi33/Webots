import com.cyberbotics.webots.controller.DistanceSensor;

public class BangBangDriveAlongTheWall extends BangBangController {


    private static int TURN = 2000;
    private static int MAX_FRONT_VALUE = 200;
    private static int MIN_FRONT_SENSOR_VALUE = 60;
    private static int DRIVE_STRAIGHT_MIN = 1500;
    private static int DRIVE_STRAIGHT_MAX = 1000;

    private static int FRONT_RIGHT = 0;
    private static int FRONT_MIDDLE_RIGHT = 1;
    private static int RIGHT = 2;
    private static int FRONT_LEFT = 4;


    private DistanceSensor[] distanceSensors;

    public BangBangDriveAlongTheWall() {
        super();
        initDistanceSensors(new String[]{"ps0","ps1","ps2","ps7"});


    }

    public static void main(String[] args) {
        BangBangDriveAlongTheWall controller = new BangBangDriveAlongTheWall();
        controller.run();
    }

    public void run() {
        boolean atFirstWall = false;
        while (step(TIME_STEP) != -1) {
            while (step(TIME_STEP) != -1 && !atFirstWall) {
                if (distanceSensors[FRONT_RIGHT].getValue() < MAX_FRONT_VALUE && distanceSensors[FRONT_LEFT].getValue() < MAX_FRONT_VALUE) {
                    driveStraightAhead();
                } else if (distanceSensors[RIGHT].getValue() < TURN) {
                    while (step(TIME_STEP) != -1 && distanceSensors[RIGHT].getValue() < TURN) {
                        driveToLeft();
                    }
                    atFirstWall = true;
                }
            }
            while (step(TIME_STEP) != -1) {
                if (distanceSensors[FRONT_MIDDLE_RIGHT].getValue() < DRIVE_STRAIGHT_MAX && distanceSensors[FRONT_MIDDLE_RIGHT].getValue() > DRIVE_STRAIGHT_MIN && distanceSensors[FRONT_RIGHT].getValue() < MAX_FRONT_VALUE) {
                    driveStraightAhead();
                } else if (distanceSensors[FRONT_MIDDLE_RIGHT].getValue() > DRIVE_STRAIGHT_MIN) {
                    driveToLeft();
                } else if (distanceSensors[FRONT_MIDDLE_RIGHT].getValue() < DRIVE_STRAIGHT_MAX) {
                    driveToRight();
                } else {
                    while (step(TIME_STEP) != -1 && distanceSensors[FRONT_RIGHT].getValue() > MIN_FRONT_SENSOR_VALUE) {
                        driveToLeft();
                    }
                }

            }
        }
    }


}
