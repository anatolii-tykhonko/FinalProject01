package com.firstgroup.project.hotels;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * Created by Sonikb on 22.04.2017.
 */
public class Room implements Serializable {
    private long roomID;
    private int persons;
    private double price;
    private LocalDate availableFrom;
    private boolean status;

    public Room(int persons, double price, LocalDate availableFrom) {
        this.persons = persons;
        this.price = price;
        this.availableFrom = availableFrom;
        this.roomID = idGenerator();
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailableFrom(LocalDate availableFrom) {
        this.availableFrom = availableFrom;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long idGenerator() {
        return this.persons + (int) this.price + this.availableFrom.hashCode();
    }

    public boolean isStatus() {
        return status;
    }

    public long getRoomID() {
        return roomID;
    }

    public int getPersons() {
        return persons;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    @Override
    public String toString() {
        return "К-тво спальных мест: " + persons +
                ", цена: " + price +
                ", дата: " + availableFrom +
                ", статус: " + (status ? "занятая" : "свободная");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (persons != room.persons) return false;
        if (Double.compare(room.price, price) != 0) return false;
        return status == room.status;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = persons;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status ? 1 : 0);
        return result;
    }
}
