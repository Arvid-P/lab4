package lab2;

import java.awt.*;
import java.math.*;

public class Carcarrier extends Car {

    private boolean rampState;
    private Car[] load = new Car[5];

    public Carcarrier(){
        super(2, 80, Color.gray, "Scania");
    }

    public void rampUp(){
        if(getCurrentSpeed() == 0 ){
            rampState = true;
        }
    }

    public void rampDown() {
        if (getCurrentSpeed() == 0) {
            rampState = false;
        }
    }

    public void loadCar(Car car) {
        if  (!rampState && car.isLoadable()
                && getCurrentSpeed() == 0
                && Math.abs(car.getYPos() - this.getYPos()) < 5
                && (Math.abs(car.getXPos() - this.getXPos()) < 5)) {
            for (int i = 0; i < 5; i++) {
                if (load[i] == null) {
                    load[i] = car;
                    break;
                }
            }
        }
    }

    public void unloadCar() {
        if  (!rampState && getCurrentSpeed() == 0) {
            for (int i = 4; i >= 0; i--) {
                if (load[i] != null) {
                    load[i].setXPos(this.getXPos() + 3);
                    load[i].setYPos(this.getYPos() + 3);
                    load[i] = null;
                    break;
                }
            }
        }
    }

    protected double speedFactor(){
        return enginePower * 0.01;
    }

    public boolean getRampState(){
        return rampState;
    }

    public Car[] getLoad() {return load;}

    protected boolean isLoadable() {
        return false;
    }

    protected boolean startCondition() {
        if  (!rampState) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void move() {
        super.move();
        for (int i = 0; i < 5; i++) {
            if (load[i] != null) {
                load[i].setXPos(this.getXPos());
                load[i].setYPos(this.getYPos());
            }
        }
    }

}
