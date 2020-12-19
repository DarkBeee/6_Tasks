package by.epam.careers.java.entity;

import java.util.Objects;

public class Port {
    private final int capacity;
    private int numberOfContainers;

    public Port(int capacity, int numberOfContainers) {
        this.capacity = capacity;
        this.numberOfContainers = numberOfContainers;
    }

    public boolean loadCheck() {
        if (numberOfContainers <= 0) {
            return false;
        }
        return true;
    }

    public boolean unloadCheck() {
        if (numberOfContainers >= capacity) {
            return false;
        }
        return true;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNumberOfContainers() {
        return numberOfContainers;
    }

    public void setNumberOfContainers(int numberOfContainers) {
        this.numberOfContainers = numberOfContainers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Port port = (Port) o;
        return capacity == port.capacity && numberOfContainers == port.numberOfContainers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, numberOfContainers);
    }

    @Override
    public String toString() {
        return "Port{" +
                "capacity=" + capacity +
                ", numberOfContainers=" + numberOfContainers +
                '}';
    }
}





