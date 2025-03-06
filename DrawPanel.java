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

public class DrawPanel extends JPanel{
    ArrayList<Point> carPoints = new ArrayList<Point>();
    ArrayList<BufferedImage> carImages = new ArrayList<BufferedImage>();

    // Just a single image, TODO: Generalize
    BufferedImage volvo240Image;
    // To keep track of a single car's position
    //Point volvoPoint = new Point(0,0);

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,0);

    BufferedImage scaniaImage;
    //Point scaniaPoint = new Point(0, 100);

    BufferedImage saab95Image;
    //Point saab95Point = new Point(0,200);

    void addCarRepresentation(Car car, int y){
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

    void removeCarRepresentation(){
        carPoints.removeLast();
        carImages.removeLast();

    }

    // TODO: Make this general for all cars
    void moveit(int index, int x, int y){

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


        //g.drawImage(volvo240Image, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
        //g.drawImage(scaniaImage, scaniaPoint.x, scaniaPoint.y, null);
        //g.drawImage(saab95Image, saab95Point.x, saab95Point.y, null);
    }
}