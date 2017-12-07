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
            boolean ballFound = false;
            while (!ballFound && step(TIME_STEP) != -1) {
                int red = calcRed();
                System.out.println("RED: " + red);
                double left = calcSearchSpeed(red);
                setSpeedValues(left, 0);
                System.out.println("LEFT: " + left);
                if (left <= 0) {
                    ballFound = true;
                }
            }
            boolean reachedBall = false;
            while (!reachedBall && step(TIME_STEP) != -1) {
            }
        }
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
