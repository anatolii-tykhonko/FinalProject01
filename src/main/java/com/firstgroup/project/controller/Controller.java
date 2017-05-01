package com.firstgroup.project.controller;

import com.firstgroup.project.APIs.HotelsAPI;
import com.firstgroup.project.Exceptions.HotelAlreadyExist;
import com.firstgroup.project.dataBase.DBService;
import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;
import com.firstgroup.project.hotels.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    private HotelsAPI hotelsAPI = new HotelsAPI();

    public Hotel addHotel(String hotelName, String cityName, int roomPersons, double roomPrice, String date) throws HotelAlreadyExist {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room(roomPersons, roomPrice, LocalDate.of(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(9, 10)))));
        return hotelsAPI.addHotel(new Hotel(hotelName, cityName, roomList));
    }

    public Room addRoom(Hotel hotel, int roomPersons, double roomPrice, String date) {
        // TODO Do something with this crap!!!
//        List<Hotel> collect = getHotelList().stream().filter(hotels -> hotels.equals(hotel)).collect(Collectors.toList());
//        collect.get(0).getRoomList().add(new Room(roomPersons, roomPrice, LocalDate.of(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(9, 10)))));
        return null;
    }

    public boolean deleteHotel(String hotelName){
        Hotel hotelObject = hotelsAPI.getDbService().getDataBase().getHotelList().stream().filter(hotel ->hotel.getHotelName().equals(hotelName)).findFirst().get();
        if(hotelObject.getHotelName().equals(hotelName)){
            return hotelsAPI.deleteHotel(hotelObject);
        }
        return false;
    }

    public boolean deleteRoom(){
        return false;
    }
    public DBService getDbService() {
        return hotelsAPI.getDbService();
    }

}
