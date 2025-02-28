package lab2;

import java.awt.*;

public class Volvo240 extends Car{

    private final static double trimFactor = 1.25;

    public Volvo240(){
        super(4, 100, Color.black, "Volvo240");
    }

    public double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

    protected boolean isLoadable() {
        return true;
    }

    protected boolean startCondition() {
        return true;
    }

}
