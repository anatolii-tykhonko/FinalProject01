package com.firstgroup.project.hotels;

import java.util.List;

/**
 * Created by Sonikb on 22.04.2017.
 */
public class Hotel {
    private long hotelID;
    private String hotelName;
    private String cityName;
    List<Room> roomList;

    public Hotel(long hotelID, String hotelName, String cityName, List<Room> roomList) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.cityName = cityName;
        this.roomList = roomList;
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
}
