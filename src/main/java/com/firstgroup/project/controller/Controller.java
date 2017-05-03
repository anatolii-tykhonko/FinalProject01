package com.firstgroup.project.controller;

import com.firstgroup.project.APIs.HotelsAPI;
import com.firstgroup.project.Exceptions.*;
import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;
import com.firstgroup.project.hotels.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private HotelsAPI hotelsAPI = new HotelsAPI();

    public Hotel addHotel(String hotelName, String cityName, int roomPersons, double roomPrice, String date) throws HotelAlreadyExist {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room(roomPersons, roomPrice, LocalDate.of(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(8, 10)))));
        return hotelsAPI.addHotel(new Hotel(hotelName, cityName, roomList));
    }

    public Room addRoom(int hotelIndex, int roomPersons, double roomPrice, String date) {
        Room newRoom = new Room(roomPersons, roomPrice, LocalDate.of(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(8, 10))));
        return hotelsAPI.addRoom(newRoom, hotelIndex);
    }

    public boolean deleteHotel(int hotelIndex) {
        return hotelsAPI.deleteHotel(hotelIndex);
    }

    public boolean deleteRoom(Room room) {
        if (room != null) {
            System.out.println("controller true");
            return hotelsAPI.deleteRoom(room);
        }
        return false;
    }

    public User editUserInfo(User user) {
        if(user!=null){
            System.out.println("controller true");
            return hotelsAPI.editUserInfo(user);
        }
        return null;
    }

    public User deleteUser(String email) throws UserNotCreated, CantDeleteCurrentUser {
        return hotelsAPI.deleteUser(email);
    }

    public User registerUser (String name, String surname, String email, String password) throws UserAlreadyExist {

        return hotelsAPI.registerUser(new User(name,surname,email,password));

    }

    public User addUser(String name, String surname, String email, String password) throws UserAlreadyExist{
        return hotelsAPI.addUser(new User(name,surname,email,password));
    }

    public boolean loginUser(String email, String password) throws IncorrectEmail, IncorrectPassword {

        return hotelsAPI.loginUser(email,password);
    }

    public HotelsAPI getHotelsAPI() {
        return hotelsAPI;
    }
}
