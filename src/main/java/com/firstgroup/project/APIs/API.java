package com.firstgroup.project.APIs;

import com.firstgroup.project.Exceptions.*;
import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;
import com.firstgroup.project.hotels.User;

import java.util.List;

/**
 * Created by Sonikb on 22.04.2017.
 */
public interface API {
    Hotel addHotel(String hotelName, String cityName, int roomPersons, double roomPrice, String date) throws HotelAlreadyExist;
    Hotel editHotelDetails(int hotelIndex,String newHotelName,String newCityName);
    Room addRoom(int hotelIndex, int roomPersons, double roomPrice, String date);
    Room editRoomDetails(int hotelIndex, int roomIndex, int roomPersons, double roomPrice, String dateAvailableFrom);
    boolean deleteRoom(Room room);
    boolean deleteHotel(int hotelIndex);
    User registerUser(String name, String surname, String email, String password) throws UserAlreadyExist;
    User addUser(String name, String surname, String email, String password) throws UserAlreadyExist;
    User editUserInfo(String newName,String newSurName,String oldEmail);
    User deleteUser(String email) throws UserNotCreated, CantDeleteCurrentUser;
    Hotel findHotelByName(String hotelName);
    Hotel findHotelByCity(String cityName);
    List<Room> findRoomsByHotel(String hotelName);
    Room roomReservationByName(int hotelIndex, int roomIndex) throws InvalidRoomStatus, InvalidHotelStatus;
    boolean cancelReservationByName(int roomIndex);
    boolean loginUser(String email, String password) throws IncorrectEmail, IncorrectPassword;
}
