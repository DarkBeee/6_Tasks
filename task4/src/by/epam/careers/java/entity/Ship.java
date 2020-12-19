package by.epam.careers.java.entity;

import java.util.Objects;

public class Ship {
    private String name;
    private int numberOfContainers;
    private int capacity;
    private boolean unload;

    public Ship() {
    }

    public Ship(String name, int numberOfContainers, int capacity, boolean unload) {
        this.name = name;
        this.numberOfContainers = numberOfContainers;
        this.capacity = capacity;
        this.unload = unload;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean loadCheck() {
        if (numberOfContainers >= capacity) {
            return false;
        }
        return true;
    }

    public boolean unloadCheck() {
        if (numberOfContainers <= 0) {
            return false;
        }
        return true;
    }

    public int getNumberOfContainers() {
        return numberOfContainers;
    }

    public void setNumberOfContainers(int numberOfContainers) {
        this.numberOfContainers = numberOfContainers;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isUnload() {
        return unload;
    }

    public void setUnload(boolean unload) {
        this.unload = unload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return numberOfContainers == ship.numberOfContainers && capacity == ship.capacity && unload == ship.unload && Objects.equals(name, ship.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberOfContainers, capacity, unload);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", numberOfContainers=" + numberOfContainers +
                ", capacity=" + capacity +
                ", unload=" + unload +
                '}';
    }
}

