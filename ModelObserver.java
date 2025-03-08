import lab2.Car;

public interface ModelObserver {

    void moveit(int index, int x, int y);
    void repaint();
    void addCarRepresentation(Car car, int y);
    void removeCarRepresentation();

}
