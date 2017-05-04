package com.firstgroup.project.hotels;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sonikb on 22.04.2017.
 */
public class Hotel implements Serializable {
    private String hotelName;
    private String cityName;
    private List<Room> roomList;

    public Hotel(String hotelName, String cityName, List<Room> roomList) {
        this.hotelName = hotelName;
        this.cityName = cityName;
        this.roomList = roomList;
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

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
