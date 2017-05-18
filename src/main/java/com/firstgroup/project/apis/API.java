package com.firstgroup.project.apis;

import com.firstgroup.project.exceptions.*;
import com.firstgroup.project.entity.Hotel;
import com.firstgroup.project.entity.Room;
import com.firstgroup.project.entity.User;

import java.util.List;

/**
 * Created by Sonikb on 22.04.2017.
 */
public interface API {
    Hotel addHotel(String hotelName, String cityName, int roomPersons, double roomPrice, String date) throws HotelAlreadyExist, ValidStringNameException, InvalidDateFormat;

    Hotel editHotelDetails(int hotelIndex, String newHotelName, String newCityName) throws ValidStringNameException;

    Room addRoom(int hotelIndex, int roomPersons, double roomPrice, String date) throws ValidStringNameException, InvalidDateFormat;

    Room editRoomDetails(int hotelIndex, int roomIndex, int roomPersons, double roomPrice, String dateAvailableFrom) throws InvalidDateFormat;

    boolean deleteRoom(int hotelIndex, int roomIndex);

    boolean deleteHotel(int hotelIndex);

    User registerUser(String name, String surname, String email, String password, boolean regTRUEaddFALSE) throws UserAlreadyExist;

    User editUserInfo(String newName, String newSurName, String oldEmail);

    User deleteUser(String email) throws CantDeleteCurrentUser;

    List<Hotel> findHotelByName(String hotelName);

    List<Hotel> findHotelByCity(String cityName);

    List<Hotel> findRoomsByHotel(String hotelName);

    Room roomReservationByName(int hotelIndex, int roomIndex, String reservDate) throws InvalidRoomStatus, InvalidHotelStatus, InvalidDateFormat;

    boolean cancelReservationByName(int roomIndex);

    boolean loginUser(String email, String password) throws IncorrectEmail, IncorrectPassword;
//
//    Map<String, User> getUsers();
//
//    List<Hotel> getHotels();

    User curUser();

    void showHotelList();

    Hotel getHotelsByIndex(int hotelIndex);

    boolean isEmptyHotelsList();

    void showRoomList(Hotel hotel);

    List<String> showCityNameList();

    List<String> makeEmailUserList();
}
