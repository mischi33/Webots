
import com.cyberbotics.webots.controller.DifferentialWheels;

public abstract class BangBangController extends RobotController {
    protected static int TIME_STEP = 15;
    private static int MAX_SPEED = 800;
    private static int MIN_SPEED = 0;



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
