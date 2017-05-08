package com.firstgroup.project.APIs;

import com.firstgroup.project.Exceptions.*;
import com.firstgroup.project.entity.Hotel;
import com.firstgroup.project.entity.Room;
import com.firstgroup.project.entity.User;

import java.util.List;

/**
 * Created by MakeMeSm1Le- on 08.05.2017.
 */
public class Application  implements  API{

    @Override
    public Hotel addHotel(String hotelName, String cityName, int roomPersons, double roomPrice, String date) throws HotelAlreadyExist, ValidStringNameException {
        return null;
    }

    @Override
    public Hotel editHotelDetails(int hotelIndex, String newHotelName, String newCityName) throws ValidStringNameException {
        return null;
    }

    @Override
    public Room addRoom(int hotelIndex, int roomPersons, double roomPrice, String date) throws ValidStringNameException {
        return null;
    }

    @Override
    public Room editRoomDetails(int hotelIndex, int roomIndex, int roomPersons, double roomPrice, String dateAvailableFrom) {
        return null;
    }

    @Override
    public boolean deleteRoom(int hotelIndex, int roomIndex) {
        return false;
    }

    @Override
    public boolean deleteHotel(int hotelIndex) {
        return false;
    }

    @Override
    public User registerUser(String name, String surname, String email, String password, boolean regTRUEaddFALSE) throws UserAlreadyExist {
        return null;
    }

    @Override
    public User editUserInfo(String newName, String newSurName, String oldEmail) {
        return null;
    }

    @Override
    public User deleteUser(String email) throws CantDeleteCurrentUser {
        return null;
    }

    @Override
    public List<Hotel> findHotelByName(String hotelName) throws IncorrectDataInput, ValidStringNameException {
        return null;
    }

    @Override
    public List<Hotel> findHotelByCity(String cityName) throws IncorrectDataInput, ValidStringNameException {
        return null;
    }

    @Override
    public List<Hotel> findRoomsByHotel(String hotelName) throws IncorrectDataInput, ValidStringNameException {
        return null;
    }

    @Override
    public Room roomReservationByName(int hotelIndex, int roomIndex, String reservDate) throws InvalidRoomStatus, InvalidHotelStatus, InvalidDateFormat {
        return null;
    }

    @Override
    public boolean cancelReservationByName(int roomIndex) {
        return false;
    }

    @Override
    public boolean loginUser(String email, String password) throws IncorrectEmail, IncorrectPassword {
        return false;
    }
}
