import lab2.*;

public class CarMechFactory {

    public Volvo240 createVolvo() {
        return new Volvo240();
    }

    public Scania createScania() {
        return new Scania();
    }

    public Saab95 createSaab95() {
        return new Saab95();
    }

    public Carcarrier createCarCarrier() {
        return new Carcarrier();
    }

    public Mechanic<Volvo240> createVolvoMechanic(int amount) {
        return new Mechanic<>(amount);
    }
    public Mechanic<Saab95> createSaabMechanic(int amount) {
        return new Mechanic<>(amount);
    }
    public Mechanic<Scania> createScaniaMechanic(int amount) {
        return new Mechanic<>(amount);
    }
    public Mechanic<Carcarrier>eateCarcarrierMechanic(int amount) {
        return new Mechanic<>(amount);
    }
    public Mechanic<Car> createGeneralMechanic(int amount) {
        return new Mechanic<>(amount);
    }

}
