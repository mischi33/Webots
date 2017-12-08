
public class ProportionalDriveAlongTheWall extends RobotController {

    private static double controllerValue = 0.2;


    public ProportionalDriveAlongTheWall() {
        super();
        String[] sensorNames = new String[]{"ps6", "ps0", "ps2", "ps7", "ps5", "ps1"};
        initDistanceSensors(sensorNames);
    }

    public void run() {
        while (step(TIME_STEP) != -1) {
            double left = calcSpeedLeft();
            double right = calcSpeedRight();
            setSpeed(left, right);
        }

    }

    private double calcSpeedRight() {
        //Rechter Sensor +LinksVorne Sensorn +RechtsVorne Sensor -2xLinkerSensor(um die Schwankungen der Sensoren zu minimieren)
        // + 7* (SchrägRechtsVorne Sensor-Rechter Sensor/2) (7* damit er schnell genug dreht und -Rechter Sensor damit er sich nicht im Kreis dreht)
        double speed = controllerValue * (getDistanceSensor("ps2").getValue() + getDistanceSensor("ps7").getValue()
                + getDistanceSensor("ps6").getValue() - 2 * getDistanceSensor("ps5").getValue()
                + 7 * (getDistanceSensor("ps1").getValue() - getDistanceSensor("ps2").getValue() / 2)) + 400;

        return (speed > 1000) ? 1000 : speed;
    }


    private double calcSpeedLeft() {
        double speed = controllerValue * (getDistanceSensor("ps2").getValue() - getDistanceSensor("ps7").getValue()
                - getDistanceSensor("ps6").getValue() + 3 * getDistanceSensor("ps5").getValue()
                - getDistanceSensor("ps0").getValue()) + 400;

        return (speed > 1000) ? 1000 : speed;
    }

    public static void main(String[] args) {
        ProportionalDriveAlongTheWall controller = new ProportionalDriveAlongTheWall();
        controller.run();
    }
}
