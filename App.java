import lab2.Car;

public class App {



    public static void main(String[] args) {


        // Instance of this class

        CarModel cm = new CarModel();
        CarView cv = new CarView("CarSim 1.0");
        CarController cc = new CarController(cv, cm);


        cc.initAddActionListeners();
        cm.cmf = new CarMechFactory();

        cm.volvoMechanic = cm.cmf.createVolvoMechanic(3);

        cm.volvoMechanic.setXPos(300);
        cm.volvoMechanic.setYPos(0);

        // Start a new view and send a reference of self


        // Start the timer
        cm.timer.start();
    }

}
