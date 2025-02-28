import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ConvolveOp;
import java.util.ArrayList;
import lab2.Car;
import lab2.Volvo240;
import lab2.Scania;
import lab2.Saab95;
import lab2.Carcarrier;
import lab2.Mechanic;


/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    private Mechanic<Volvo240> volvoMechanic;
    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Scania());
        cc.cars.add(new Saab95());

        cc.volvoMechanic = new Mechanic<>(3);

        double y = 0;
        for(Car car : cc.cars){
            car.setYPos(y);
            y += 100;
        }

        cc.volvoMechanic.setXPos(300);
        cc.volvoMechanic.setYPos(0);




        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                checkbound(car, volvoMechanic);
                car.move();
                int x = (int) Math.round(car.getXPos());
                int y = (int) Math.round(car.getYPos());
                frame.drawPanel.moveit(car, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }
    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    void startEngine() {
        for (Car car : cars) {
            car.startEngine();
        }
    }

    void stopEngine() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }

    void saabTurboOn() {
        for (Car car : cars) {
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOn();

            }
        }
    }

    void saabTurboOff() {
        for (Car car : cars) {
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void liftBed() {
        for (Car car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).setRampUp();
            }
        }
    }

    void lowerBed() {
        for (Car car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).setRampDown();
            }
        }
    }

    public void checkbound(Car car, Mechanic<Volvo240> mechanic){

        if(car.getXPos() < 0){
            car.stopEngine();
            car.turnRight();
            car.turnRight();
            car.startEngine();
            car.setXPos(Math.max(car.getXPos(),0));
        }
        if (car.getXPos() > 700){
            car.stopEngine();
            car.turnRight();
            car.turnRight();
            car.startEngine();
            car.setXPos(Math.min(car.getXPos(),700));
        }

        if (car.getXPos() >= mechanic.getXPos()
                && car.getXPos() <= mechanic.getXPos() + 100
                && car.getYPos() >= mechanic.getYPos()
                && car.getYPos() <= mechanic.getYPos() + 100
                && car instanceof Volvo240) {
            mechanic.addCar((Volvo240) car);
            car.setMechanicState(true);
        }

        if (car.getMechanicState()) {
            car.setCurrentSpeed(0);
        }
    }

}