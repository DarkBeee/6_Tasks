package by.epam.careers.java.logic;

import by.epam.careers.java.entity.Port;
import by.epam.careers.java.entity.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PortController {
    private Port port;
    private List<Ship> ships = new ArrayList<>();
    private int numberOfShips;

    {
        port = new Port(10_000, 5000);
        Ship ship1 = new Ship("Ship #1", 100, 1000, false);
        Ship ship2 = new Ship("Ship #2", 100, 1500, false);
        Ship ship3 = new Ship("Ship #3", 1900, 2100, true);
        Ship ship4 = new Ship("Ship #4", 1600, 1800, true);
        Ship ship5 = new Ship("Ship #5", 1800, 2100, true);
        ships.addAll(Arrays.asList(ship1, ship2, ship3, ship4, ship5));
        numberOfShips = ships.size() - 1;
    }

    public synchronized Ship getShip() {
        Ship ship = null;
        if (numberOfShips != -1) {
            ship = ships.get(numberOfShips);
            ships.remove(numberOfShips);
            numberOfShips--;
            System.out.println(ship.getName() + " заходит в причал " + Thread.currentThread().getName());
        }
        return ship;
    }

    public void loading(Ship ship) {
        try {
            System.out.println(ship.getName() + " загрузка контейнеров. Загрузка займёт " + ((ship.getCapacity() - ship.getNumberOfContainers())) / 100 + " сек");
            while (ship.loadCheck()) {
                if (!port.loadCheck()) {
                    System.out.println("Загрузка корябля " + ship.getName() + " невозможна - порт пуст");
                    return;
                }
                Thread.sleep(1000);
                ship.setNumberOfContainers(ship.getNumberOfContainers() + 100);
                port.setNumberOfContainers(port.getNumberOfContainers() - 100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ship.getName() + " - загрузка завершена. Причал - " + Thread.currentThread().getName());
    }

    public void unloading(Ship ship) {
        try {
            System.out.println(ship.getName() + " разгрузка контейнеров. Разгрузка займёт " + ship.getNumberOfContainers() / 100 + " сек");
            while (ship.unloadCheck()) {
                if (!port.unloadCheck()) {
                    System.out.println("Разгрузка корабля " + ship.getName() + " невозможна - порт переполнен");
                    return;
                }
                Thread.sleep(1000);
                ship.setNumberOfContainers(ship.getNumberOfContainers() - 100);
                port.setNumberOfContainers(port.getNumberOfContainers() + 100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ship.getName() + " - рагрузка завершена. Причал - " + Thread.currentThread().getName());
    }
}
