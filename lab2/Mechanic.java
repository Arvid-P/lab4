package lab2;

import java.util.ArrayList;

public class Mechanic<T extends Car> {
    private int carCapacity;
    private ArrayList<T> cars;

    private double xPos;
    private double yPos;

    public Mechanic(int carCapacity) {
        this.carCapacity = carCapacity;
        this.cars = new ArrayList<>();
    }

    public void addCar(T car) {
        if (cars.size() < carCapacity) {
            cars.add(car);
        }
    }

    public <T> T removeCar(T car) {
        cars.remove(car);
        return car;
    }

    public ArrayList<T> getCars(){return cars;}

    public void setXPos(double xPos) {
        this.xPos = xPos;
    }
    public void setYPos(double yPos) {
        this.yPos = yPos;
    }
    public double getXPos() {
        return xPos;
    }
    public double getYPos() {
        return yPos;
    }

}
