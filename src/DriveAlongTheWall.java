import com.cyberbotics.webots.controller.DistanceSensor;

public class DriveAlongTheWall extends DriveController {


    private static int Drehen = 2000;
    private static int MAX_FRONT_VALUE = 200;
    private static int MIN_FRONT_SENSOR_VALUE = 60;
    private static int DRIVE_Straight_MIN = 1500;
    private static int DRIVE_Straight_MAX = 1000;


    private DistanceSensor[] distanceSensors;

    public DriveAlongTheWall() {
        super();
        distanceSensors = new DistanceSensor[]{getDistanceSensor("ps0"), getDistanceSensor("ps1"),
                getDistanceSensor("ps2"), getDistanceSensor("ps3"),
                getDistanceSensor("ps4"), getDistanceSensor("ps5"),
                getDistanceSensor("ps6"), getDistanceSensor("ps7")};

        for (int i = 0; i < distanceSensors.length; i++) {
            distanceSensors[i].enable(10);
        }

    }

    public static void main(String[] args) {
        DriveAlongTheWall controller = new DriveAlongTheWall();
        controller.run();
    }

    public void run() {

        boolean atFirstWall=false;
        while (step(TIME_STEP) != -1) {
             while (step(TIME_STEP) != -1 && !atFirstWall)
            if (distanceSensors[FRONT_RIGHT].getValue() < MAX_FRONT_VALUE && distanceSensors[FRONT_LEFT].getValue() < MAX_FRONT_VALUE) {
                driveStraightAhead();
            } else if (distanceSensors[RIGHT].getValue() < Drehen) {
                while (step(TIME_STEP) != -1 && distanceSensors[RIGHT].getValue() < Drehen) {
                    driveToLeft();

                }
                atFirstWall=true;
            }
            while (step(TIME_STEP) != -1) {
                if (distanceSensors[FRONT_MIDDLE_RIGHT].getValue() < DRIVE_Straight_MAX && distanceSensors[FRONT_MIDDLE_RIGHT].getValue() > DRIVE_Straight_MIN && distanceSensors[FRONT_RIGHT].getValue() < MAX_FRONT_VALUE) {
                    driveStraightAhead();
                } else if (distanceSensors[FRONT_MIDDLE_RIGHT].getValue() > DRIVE_Straight_MIN) {
                    driveToLeft();
                } else if (distanceSensors[FRONT_MIDDLE_RIGHT].getValue() < DRIVE_Straight_MAX) {
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
