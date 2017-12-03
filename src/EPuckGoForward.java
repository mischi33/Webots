
import com.cyberbotics.webots.controller.DifferentialWheels;

public class EPuckGoForward {
    private static int TIME_STEP = 15;

    public static void main(String[] args) {

        DifferentialWheels wheels = new DifferentialWheels();
        while (wheels.step(TIME_STEP) != -1) {
            wheels.setSpeed(100, 100);
        }
    }
}