package com.firstgroup.project.hotels;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sonikb on 22.04.2017.
 */
public class Hotel implements Serializable {
    private long hotelID;
    private String hotelName;
    private String cityName;
    private List<Room> roomList;

    public Hotel(String hotelName, String cityName, List<Room> roomList) {
        this.hotelName = hotelName;
        this.cityName = cityName;
        this.roomList = roomList;
        this.hotelID = idGenerator();
    }

    public int idGenerator() {
        return hotelName.hashCode() + cityName.hashCode() / 2;
    }

    public long getHotelID() {
        return hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getCityName() {
        return cityName;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        return hotelName != null ? hotelName.equals(hotel.hotelName) : hotel.hotelName == null;
    }

    @Override
    public int hashCode() {
        return hotelName != null ? hotelName.hashCode() : 0;
    }

}
