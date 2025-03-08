import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import lab2.Car;
import lab2.Volvo240;
import lab2.Scania;
import lab2.Saab95;
import lab2.Mechanic;


/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities is to listen to the View and responds in an appropriate manner by
 * modifying the model state and the updating the view.~
 */

public class CarModel  {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    protected Timer timer = new Timer(delay, new TimerListener());

    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    protected Mechanic<Volvo240> volvoMechanic;

    private int initCarYPos = 0;

    protected CarMechFactory cmf;

    private int maxCarSize = 7;

    private ModelObserver mo;

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < cars.size(); i++) {
                checkLeftBound(cars.get(i));
                checkRightBound(cars.get(i));
                checkInMechanicBounds(cars.get(i), volvoMechanic);
                checkCarInMechanicState(cars.get(i));

                cars.get(i).move();
                int x = (int) Math.round(cars.get(i).getXPos());
                int y = (int) Math.round(cars.get(i).getYPos());
                mo.moveit(i, x, y);
                // repaint() calls the paintComponent method of the panel
                mo.repaint();
            }
        }
    }

    public void addObserver(ModelObserver dp) {
        this.mo = dp;
    }

    // Calls the gas method for each car once
    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    public void startEngine() {
        for (Car car : cars) {
            car.startEngine();
        }
    }

    public void stopEngine() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }

    public void saabTurboOn() {
        for (Car car : cars) {
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOn();

            }
        }
    }

    public void saabTurboOff() {
        for (Car car : cars) {
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOff();
            }
        }
    }

    public void liftBed() {
        for (Car car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).setRampUp();
            }
        }
    }

    public void lowerBed() {
        for (Car car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).setRampDown();
            }
        }
    }

    public void addVolvoCar() {
        if (cars.size() < maxCarSize) {
            cars.add(cmf.createVolvo());
            if (cars != null && !cars.isEmpty()) {
                cars.get(cars.size() - 1).setYPos(initCarYPos);
                mo.addCarRepresentation(cars.get(cars.size() - 1), initCarYPos);
            }
            initCarYPos += 100;
        }
    }

    public void addSaabCar() {
        if (cars.size() < maxCarSize) {
            cars.add(cmf.createSaab95());
            if (cars != null && !cars.isEmpty()) {
                cars.get(cars.size() - 1).setYPos(initCarYPos);
                mo.addCarRepresentation(cars.get(cars.size() - 1), initCarYPos);
            }
            initCarYPos += 100;
        }
    }

    public void addScaniaCar() {
        if (cars.size() < maxCarSize) {
            cars.add(cmf.createScania());
            if (cars != null && !cars.isEmpty()) {
                cars.get(cars.size() - 1).setYPos(initCarYPos);
                mo.addCarRepresentation(cars.get(cars.size() - 1), initCarYPos);
            }
            initCarYPos += 100;
        }
    }

    public void removeCar() {
        if (cars != null && !cars.isEmpty()) {
            cars.removeLast();
            mo.removeCarRepresentation();
            initCarYPos -= 100;
        }
    }

    public void checkLeftBound(Car car){
        if(car.getXPos() < 0) {
            car.stopEngine();
            car.turnRight();
            car.turnRight();
            car.startEngine();
            car.setXPos(Math.max(car.getXPos(), 0));
        }
    }

    public void checkRightBound(Car car) {
        if (car.getXPos() > 900){
            car.stopEngine();
            car.turnRight();
            car.turnRight();
            car.startEngine();
            car.setXPos(Math.min(car.getXPos(),900));
        }
    }

    public void checkInMechanicBounds(Car car, Mechanic<Volvo240> mechanic) {
        if (car.getXPos() >= mechanic.getXPos()
                && car.getXPos() <= mechanic.getXPos() + 100
                && car.getYPos() >= mechanic.getYPos()
                && car.getYPos() <= mechanic.getYPos() + 50
                && car instanceof Volvo240) {
            mechanic.addCar((Volvo240) car);
            car.setMechanicState(true);
        }
    }

    public void checkCarInMechanicState(Car car) {
        if (car.getMechanicState()) {
            car.setCurrentSpeed(0);
        }
    }

}