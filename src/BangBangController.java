
import com.cyberbotics.webots.controller.DifferentialWheels;

public class BangBangController extends RobotController {
    protected static int TIME_STEP = 15;
    protected static int MAX_SPEED = 800;
    protected static int MIN_SPEED = 0;

    protected static int FRONT_RIGHT = 0;
    protected static int FRONT_MIDDLE_RIGHT = 1;
    protected static int RIGHT = 2;
    protected static int BACK_RIGHT = 3;
    protected static int BACK_LEFT = 4;
    protected static int LEFT = 5;
    protected static int FRONT_MIDDLE_LEFT = 6;
    protected static int FRONT_LEFT = 7;




    protected void stopDriving() {
        setSpeed(MIN_SPEED, MIN_SPEED);
    }

    protected void driveToRight() {
        setSpeed(MAX_SPEED, MIN_SPEED);
    }

    protected void driveToLeft() {
        setSpeed(MIN_SPEED, MAX_SPEED);
    }

    protected void driveStraightAhead() {
        setSpeed(MAX_SPEED, MAX_SPEED);
    }
}
