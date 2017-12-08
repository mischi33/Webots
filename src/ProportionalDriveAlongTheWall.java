
public class ProportionalDriveAlongTheWall extends RobotController {

    private static double controllerValue = 0.2;
    private static int MAX_SPEED = 100;


    public ProportionalDriveAlongTheWall() {
        super();
        String[] sensorNames = new String[]{"ps6", "ps0", "ps2", "ps7","ps5","ps1"};
        initDistanceSensors(sensorNames);
    }

    private void setSpeedValues(double left, double right) {
        setSpeed(left, right);
    }

    public void run() {
        while (step(TIME_STEP) != -1) {
            double left = calcSpeedLeft(new String[] {"ps1", "ps2"});
            double right = calcSpeedRight(new String[] {"ps6", "ps2","ps1"});
            setSpeedValues(left, right);
        }

    }
    private double calcSpeedRight(String[] sensors) {
        double speed = 0;

        //Rechter Sensor +LinksVorne Sensorn +RechtsVorne Sensor -2xLinkerSensor(um die Schwankungen der Sensoren zu minimieren) + 7* (SchrägRechtsVorne Sensor-Rechter Sensor/2) (7* damit er schnell genug dreht und -Rechter Sensor damit er sich nicht im Kreis dreht)
        speed =controllerValue * (getDistanceSensor("ps2").getValue()+getDistanceSensor("ps7").getValue()+getDistanceSensor("ps6").getValue()-2*getDistanceSensor("ps5").getValue()+7*(getDistanceSensor("ps1").getValue()-getDistanceSensor("ps2").getValue()/2))+400;

        if (speed > 1000) {
            speed = 1000;
        }
        return speed;
    }


    private double calcSpeedLeft(String[] sensors) {
        double speed = 0;

        speed =controllerValue * (getDistanceSensor("ps2").getValue()-getDistanceSensor("ps7").getValue()-getDistanceSensor("ps6").getValue()+3*getDistanceSensor("ps5").getValue()-getDistanceSensor("ps0").getValue())+400;

        if (speed > 1000) {
            speed = 1000;
        }
        return speed;
    }

    public static void main(String[] args) {
        ProportionalDriveAlongTheWall controller = new ProportionalDriveAlongTheWall();
        controller.run();
    }
}
