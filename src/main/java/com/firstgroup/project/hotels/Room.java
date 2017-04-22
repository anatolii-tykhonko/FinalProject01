package com.firstgroup.project.hotels;

import java.util.Date;

/**
 * Created by Sonikb on 22.04.2017.
 */
public class Room {
    private long roomID;
    private int persons;
    private int price;
    private Date availableFrom;

    public Room(long roomID, int persons, int price, Date availableFrom) {
        this.roomID = roomID;
        this.persons = persons;
        this.price = price;
        this.availableFrom = availableFrom;
    }

    public long getRoomID() {
        return roomID;
    }

    public int getPersons() {
        return persons;
    }

    public int getPrice() {
        return price;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }
}
