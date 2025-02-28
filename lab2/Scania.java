package lab2;

import java.awt.*;

public class Scania extends Car {

    private int angle;

    public Scania(){
        super(2, 80, Color.gray, "Scania");
    }

    // Incrementing the ramp angle by 1 for each instance where maximum angle is 70
    private void rampUp(){
        if(getCurrentSpeed() == 0 ){
            angle++;
            angle = Math.min(angle, 70);
        }
    }

    // Decrementing the ramp angle by 1 for each instance where the minimum angle is 0
    private void rampDown() {
        if (getCurrentSpeed() == 0) {
            angle--;
            angle = Math.max(angle, 0);
        }
    }

    //Calls instance to the private rampUp method
    public void setRampUp() {
        rampUp();
    }

    //Calls instance to the private rampDown method
    public void setRampDown() {
        rampDown();
    }

    //Returns the current ramp angle
    public int getRampAngle(){ return angle;}

    //Returns the cars speedfactor
    protected double speedFactor(){
        return enginePower * 0.01;
    }

    public boolean isLoadable() {
        return true;
    }

    //The car is ony allowed to move if the ramps angle is 0
    protected boolean startCondition() {
        if  (getRampAngle() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

}


