import lab2.Car;

public class App {

    public static void main(String[] args) {

        CarModel cm = new CarModel();
        CarView cv = new CarView("CarSim 1.0");
        CarController cc = new CarController(cv, cm);

        cm.addObserver(cv.drawPanel);
        cm.cmf = new CarMechFactory();

        cm.volvoMechanic = cm.cmf.createVolvoMechanic(3);

        cm.volvoMechanic.setXPos(300);
        cm.volvoMechanic.setYPos(0);

        // Start the timer
        cm.timer.start();
    }

}
