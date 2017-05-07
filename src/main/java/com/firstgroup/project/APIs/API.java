package com.firstgroup.project.APIs;

import com.firstgroup.project.Exceptions.*;
import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;
import com.firstgroup.project.hotels.User;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Sonikb on 22.04.2017.
 */
public interface API {
    Hotel addHotel(String hotelName, String cityName, int roomPersons, double roomPrice, String date) throws HotelAlreadyExist, ValidStringNameException;
    Hotel editHotelDetails(int hotelIndex,String newHotelName,String newCityName) throws ValidStringNameException;
    Room addRoom(int hotelIndex, int roomPersons, double roomPrice, String date) throws ValidStringNameException;
    Room editRoomDetails(int hotelIndex, int roomIndex, int roomPersons, double roomPrice, String dateAvailableFrom);
    boolean deleteRoom(int hotelIndex,int roomIdex);
    boolean deleteHotel(int hotelIndex);
    User registerUser(String name, String surname, String email, String password, boolean regTRUEaddFALSE) throws UserAlreadyExist;
    User editUserInfo(String newName,String newSurName,String oldEmail);
    User deleteUser(String email) throws CantDeleteCurrentUser;
    List<Hotel> findHotelByName(String hotelName) throws IncorrectDataInput, ValidStringNameException;
    List<Hotel> findHotelByCity(String cityName) throws IncorrectDataInput, ValidStringNameException;
    List<Hotel> findRoomsByHotel(String hotelName) throws IncorrectDataInput, ValidStringNameException;
    Room roomReservationByName(int hotelIndex, int roomIndex) throws InvalidRoomStatus, InvalidHotelStatus;
    boolean cancelReservationByName(int roomIndex);
    boolean loginUser(String email, String password) throws IncorrectEmail, IncorrectPassword;
}
