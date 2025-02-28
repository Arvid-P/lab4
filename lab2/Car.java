
package lab2;
import java.awt.*;

public abstract class Car implements Movable {

    private final int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    protected final String modelName; // The car model name
    private double xPos;
    private double yPos;
    private String direction = "West"; //predetermined direction of the car
    private boolean carInmechanic;

    protected Car(int nrDoors, double enginePower, Color color, String modelName) {

       this.nrDoors = nrDoors;
       this.enginePower = enginePower;
       this.color = color;
       this.modelName = modelName;
       stopEngine();

    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setCurrentSpeed(double currentSpeed){
        this.currentSpeed = currentSpeed;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        if (startCondition()) {
            currentSpeed = 0.1;
        }
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    protected abstract double speedFactor();

    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }


    // IncrementSpeed is called when the argument is between the interval 0 and 1
    public void gas(double amount){
        if (amount <= 1 && amount >= 0 && currentSpeed >= 0.1) {
            incrementSpeed(amount);
        }
    }

    // DecrementSpeed is called when the argument is between the interval 0 and 1
    public void brake(double amount){
        if (amount <= 1 && amount >= 0){
            decrementSpeed(amount);
        }
    }

    //Depending on the direction of the car, we are either moving positive or negative in the x ad y axis
    public void move(){
        switch (direction){
            case "North":
                yPos += currentSpeed;
                break;
            case "East":
                xPos += currentSpeed;
                break;
            case "South":
                yPos -= currentSpeed;
                break;
            case "West":
                xPos -= currentSpeed;
                break;
        }
    }

    //The cars direction adjusts 90 degrees to the left replacing the old direction as a string
    public void turnRight(){
        switch (direction){
            case "North":
                direction = "East";
                break;
            case "East":
                direction = "South";
                break;
            case "South":
                direction = "West";
                break;
            case "West":
                direction = "North";
                break;
        }
    }
    //The cars direction adjusts 90 degrees to the right replacing the old direction as a string
    public void turnLeft(){
        switch (direction){
            case "North":
                direction = "West";
                break;
            case "East":
                direction = "North";
                break;
            case "South":
                direction = "East";
                break;
            case "West":
                direction = "South";
                break;
        }
    }

    public double getXPos(){
        return xPos;
    }
    public double getYPos(){
        return yPos;
    }

    public void setXPos(double x) {
        xPos = x;
    }

    public void setYPos(double y) {
        yPos = y;
    }

    protected abstract boolean isLoadable();

    protected abstract boolean startCondition();

    public void setMechanicState(boolean state) {
        carInmechanic = state;
    }
    public boolean getMechanicState(){
        return carInmechanic;
    }

}
