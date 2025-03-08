import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarController {




    CarModel cm;
    CarView cv;




    public CarController(CarView cv, CarModel cm){

        this.cv = cv;
        this.cm = cm;

        initAddActionListeners();

    }

    public void initAddActionListeners() {

        // This actionListener is for the gas button only
        // TODO: Create more for each component as necessary
        cv.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {cm.gas(cv.gasAmount);}
        });

        cv.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.brake(cv.gasAmount);
            }
        });

        cv.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.startEngine();
            }
        });

        cv.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.stopEngine();
            }
        });

        cv.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.saabTurboOn();
            }
        });

        cv.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.saabTurboOff();
            }
        });

        cv.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.liftBed();
            }
        });

        cv.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.lowerBed();
            }
        });

        cv.addVolvoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cm.addVolvoCar(); }
        });

        cv.addSaabButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cm.addSaabCar(); }

        });

        cv.addScaniaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cm.addScaniaCar(); }
        });

        cv.removeCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.removeCar();
            }
        });



    }



}


