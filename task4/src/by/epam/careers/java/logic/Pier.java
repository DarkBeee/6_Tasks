package by.epam.careers.java.logic;

import by.epam.careers.java.entity.Ship;

public class Pier implements Runnable {
    private static PortController portController = new PortController();

    @Override
    public void run() {
        Ship ship = new Ship();
        while (ship != null) {
            ship = portController.getShip();
            if (ship != null && ship.isUnload()) {
                portController.unloading(ship);
            }
            else if (ship != null && !ship.isUnload()){
                portController.loading(ship);
            }
        }
    }
}
