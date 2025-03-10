import lab2.Saab95;
import lab2.Scania;
import lab2.Volvo240;
import lab2.Car;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel implements ModelObserver{

    private ArrayList<Point> carPoints = new ArrayList<Point>();
    private ArrayList<BufferedImage> carImages = new ArrayList<BufferedImage>();

    private BufferedImage volvoWorkshopImage;
    private Point volvoWorkshopPoint = new Point(300,0);

    private BufferedImage volvo240Image;
    private BufferedImage scaniaImage;
    private BufferedImage saab95Image;

    public void addCarRepresentation(Car car, int y){
        if (car.getClass() == Saab95.class) {
            carPoints.add(new Point(0, y));
            carImages.add(saab95Image);
        }

        else if (car.getClass() == Scania.class) {
            carPoints.add(new Point(0, y));
            carImages.add(scaniaImage);
        }

        else if (car.getClass() == Volvo240.class) {
            carPoints.add(new Point(0, y));
            carImages.add(volvo240Image);
        }
    }

    public void removeCarRepresentation(){
        carPoints.removeLast();
        carImages.removeLast();
        repaint();
    }

    // TODO: Make this general for all cars
    public void moveit(int index, int x, int y){
        carPoints.get(index).x = x;
        carPoints.get(index).y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Remember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvo240Image = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            saab95Image = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }
 
    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < carImages.size(); i++) {
            g.drawImage(carImages.get(i), carPoints.get(i).x, carPoints.get(i).y, null);
        }
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}