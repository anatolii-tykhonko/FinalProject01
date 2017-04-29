package com.firstgroup.project.hotels;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * Created by Sonikb on 22.04.2017.
 */
public class Room implements Serializable{
    private long roomID;
    private int persons;
    private double price;
    private LocalDate availableFrom;

    public Room(int persons, double price, LocalDate availableFrom) {
        this.persons = persons;
        this.price = price;
        this.availableFrom = availableFrom;
        this.roomID = idGenerator();
    }

    public long idGenerator() {
        return this.persons + (int)this.price + this.availableFrom.hashCode();
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
}
