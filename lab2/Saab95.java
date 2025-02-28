package lab2;

import java.awt.*;


public class Saab95 extends Car{

    private boolean turboOn;



    public Saab95(){
        super(2, 125, Color.red, "Saab95");
	    turboOn = false;
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }
    
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }

    protected boolean isLoadable() {
        return true;
    }

    protected boolean startCondition() {
        return true;
    }

}
