import com.cyberbotics.webots.controller.Camera;

public class BangBangPushBall extends BangBangController {
    private Camera camera;
    private int height;
    private int width;

    public BangBangPushBall() {
        initDistanceSensors(new String[]{"ps6", "ps7", "ps0", "ps1"});
        camera = getCamera("camera");
        camera.enable(10);
        width = camera.getWidth();
        height = camera.getHeight();
    }


    public void run() {
        while (step(TIME_STEP) != -1) {
            boolean ballFound = false;
            while (!ballFound && step(TIME_STEP) != -1) {
                System.out.println("Searching Ball.");
                driveToLeft();
                int red = calcRed();
                if (red < 24100) {
                    ballFound = true;
                }
            }
            boolean reachedBall = false;
            while (!reachedBall && step(TIME_STEP) != -1) {
                System.out.println("Go to Ball.");
                if (getDistanceSensor("ps7").getValue() > 200
                        || getDistanceSensor("ps0").getValue() > 200) {
                    reachedBall = true;
                } else {
                    driveStraightAhead();
                }
            }
            while (step(TIME_STEP) != -1) {
                System.out.println("Push Ball.");
                if (getDistanceSensor("ps7").getValue() > 150 && getDistanceSensor("ps0").getValue() > 150) {
                    driveStraightAhead();
                } else if (getDistanceSensor("ps1").getValue() > 150 || getDistanceSensor("ps0").getValue() > 300) {
                    driveToRight();
                } else if (getDistanceSensor("ps6").getValue() > 150 || getDistanceSensor("ps7").getValue() > 300) {
                    driveToLeft();
                } else {
                    driveStraightAhead();
                }
            }
        }
    }

    public static void main(String[] args) {
        BangBangPushBall controller = new BangBangPushBall();
        controller.run();
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
}
