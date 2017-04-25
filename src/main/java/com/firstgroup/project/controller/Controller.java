package com.firstgroup.project.controller;

import com.firstgroup.project.APIs.HotelsAPI;
import com.firstgroup.project.Exceptions.HotelAlreadyExist;
import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private HotelsAPI hotelsAPI = new HotelsAPI();

    public Hotel addHotel(String hotelName, String cityName, int roomPersons, double roomPrice, String date) throws HotelAlreadyExist {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room(roomPersons, roomPrice, LocalDate.of(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(9, 10)))));
        return hotelsAPI.addHotel(new Hotel(hotelName, cityName, roomList));

        //year.mm.dd  2017   10    21
        // 2017.04.21   2017.4.4
    }
}
