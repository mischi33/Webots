import com.cyberbotics.webots.controller.Camera;

public class ProportionalPushBall extends ProportionalController {
    private Camera camera;
    private int height;
    private int width;


    public ProportionalPushBall() {
        initDistanceSensors(new String[]{"ps6", "ps7", "ps0", "ps1"});
        camera = getCamera("camera");
        camera.enable(10);
        width = camera.getWidth();
        height = camera.getHeight();
    }

    @Override
    public void run() {
        while (step(TIME_STEP) != -1) {
            findBall();
            driveToBall();
            pushBall();
        }
    }

    private void findBall() {
        boolean ballFound = false;
        while (!ballFound && step(TIME_STEP) != -1) {
            System.out.println("Search Ball.");
            int red = calcRed();
            System.out.println("RED: " + red);
            double left = calcSearchSpeed(red);
            setSpeedValues(left, 0);
            System.out.println("LEFT: " + left);
            if (left <= 0) {
                ballFound = true;
            }
        }
    }

    private void driveToBall() {
        boolean reachedBall = false;
        while (!reachedBall && step(TIME_STEP) != -1) {
            System.out.println("Go to Ball.");
            double left = calcGoForwardSpeed("ps7");
            double right = calcGoForwardSpeed("ps0");
            setSpeedValues(left, right);
            if (left <= 0 || right <= 0) {
                reachedBall = true;
            }
        }
    }

    private void pushBall() {
        while (step(TIME_STEP) != -1) {
            System.out.println("Push Ball.");
            double right = calcPushSpeed(new String[]{"ps6", "ps7"});
            double left = calcPushSpeed(new String[]{"ps0", "ps1"});
            setSpeedValues(left, right);
        }
    }

    private double calcGoForwardSpeed(String name) {
        double speed = 200.0 - getDistanceSensor(name).getValue();
        return (speed < 1000) ? speed : 1000;
    }

    private double calcPushSpeed(String[] sensors) {
        double speed = 0;
        for (String sensor : sensors) {
            speed = speed + getDistanceSensor(sensor).getValue() + 100;
        }
        if (speed > 1000) {
            speed = 1000;
        }
        return speed;
    }

    private double calcSearchSpeed(int redValue) {
        double speed = redValue - 24100;
        return (speed < 1000) ? speed : 1000;
    }


    private int calcRed() {
        int[] image = camera.getImage();
        int red = 0;
        for (int i = width / 3; i < 2 * width / 3; i++) {
            for (int j = height / 2; j < 3 * height / 4; j++) {
                red += Camera.imageGetRed(image, width, i, j);
            }
        }
        return red;
    }

    public static void main(String[] args) {
        ProportionalPushBall controller = new ProportionalPushBall();
        controller.run();
    }
}
