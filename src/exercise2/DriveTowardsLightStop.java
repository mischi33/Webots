package exercise2;

import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.LightSensor;


public class DriveTowardsLightStop extends DifferentialWheels {
    private static int TIME_STEP = 15;
    private static int MAX_SPEED = 800;
    private static int MIN_SPEED = 0;

    private static int MAX_SENSOR_VALUE = 1300;
    private static int MAX_FRONT_SENSOR_VALUE = 400;
    private static int STOP_VALUE = 350;
    private static int STOP_VALUE_2 = 1500;

    private static int FRONT_RIGHT = 0;
    private static int FRONT_MIDDLE_RIGHT = 1;
    private static int RIGHT = 2;
    private static int BACK_RIGHT = 3;
    private static int BACK_LEFT = 4;
    private static int LEFT = 5;
    private static int FRONT_MIDDLE_LEFT = 6;
    private static int FRONT_LEFT = 7;
    private LightSensor[] lightSensors;

    public DriveTowardsLightStop(){
        super();
        lightSensors = new LightSensor[] { getLightSensor("ls0"), getLightSensor("ls1"),
                getLightSensor("ls2"), getLightSensor("ls3"),
                getLightSensor("ls4"), getLightSensor("ls5"),
                getLightSensor("ls6"), getLightSensor("ls7") };

        for (LightSensor lightSensor : lightSensors) {
            lightSensor.enable(10);
        }
    }

    public static void main(String[] args) {
        DriveTowardsLightStop controller = new DriveTowardsLightStop();
        controller.run();
    }

    public void run() {
        while (step(TIME_STEP) != -1) {
            if ((lightSensors[FRONT_LEFT].getValue() < STOP_VALUE || lightSensors[FRONT_RIGHT].getValue() < STOP_VALUE)
                    && (lightSensors[FRONT_MIDDLE_LEFT].getValue() > STOP_VALUE_2 && lightSensors[FRONT_MIDDLE_RIGHT].getValue() > STOP_VALUE_2)) {
                stopDriving();
            } else if (lightSensors[FRONT_LEFT].getValue() < MAX_FRONT_SENSOR_VALUE &&
                    lightSensors[FRONT_RIGHT].getValue() < MAX_FRONT_SENSOR_VALUE) {
                driveStraightAhead();
            } else if (lightSensors[FRONT_MIDDLE_LEFT].getValue() < MAX_SENSOR_VALUE
                    || lightSensors[LEFT].getValue() < MAX_SENSOR_VALUE
                    || lightSensors[BACK_LEFT].getValue() < MAX_SENSOR_VALUE) {
                driveToLeft();
            } else if (lightSensors[FRONT_MIDDLE_RIGHT].getValue() < MAX_SENSOR_VALUE ||
                    lightSensors[RIGHT].getValue() < MAX_SENSOR_VALUE ||
                    lightSensors[BACK_RIGHT].getValue() < MAX_SENSOR_VALUE) {
                driveToRight();
            } else {
                driveStraightAhead();
            }
        }
    }

    private void stopDriving() {
        setSpeed(MIN_SPEED, MIN_SPEED);
    }

    private void driveToRight() {
        setSpeed(MAX_SPEED, MIN_SPEED);
    }

    private void driveToLeft() {
        setSpeed(MIN_SPEED, MAX_SPEED);
    }

    private void driveStraightAhead() {
        setSpeed(MAX_SPEED, MAX_SPEED);
    }
}
