import com.cyberbotics.webots.controller.Camera;

public class ProportionalPushBall extends RobotController {
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
            int red = calcRed();
            double left = calcSpeed(red, new String[]{"ps0", "ps1"});
            double right = calcSpeed(24750, new String[]{"ps6", "ps7"});
            setSpeed(left, right);
        }
    }

    private double calcSpeed(int redValue, String[] sensors) {
        double speed = 0;
        for (String sensor : sensors) {
            speed = speed + getDistanceSensor(sensor).getValue() + (50 * Math.abs(redValue - 24100) * 0.01);
        }
        return (speed > 1000) ? 1000 : speed;
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
