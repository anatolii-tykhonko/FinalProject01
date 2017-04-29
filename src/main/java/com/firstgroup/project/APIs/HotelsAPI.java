package com.firstgroup.project.APIs;

import com.firstgroup.project.DAOs.CommonDAO;
import com.firstgroup.project.Exceptions.HotelAlreadyExist;
import com.firstgroup.project.Exceptions.UserAlreadyExist;
import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;
import com.firstgroup.project.hotels.User;

import java.util.List;

/**
 * Created by Sonikb on 22.04.2017.
 */
public class HotelsAPI implements API {
    private CommonDAO commonDAO = new CommonDAO();

    public Hotel addHotel(Hotel hotel) throws HotelAlreadyExist {
        return commonDAO.save(hotel);
    }

    public Hotel editHotelDetails() {
        return null;
    }

    public Room addRoom(Room room) {
        return null;
    }

    public Room editRoomDetails() {
        return null;
    }

    public boolean deleteRoom(Room room) {
        return false;
    }

    public boolean deleteHotel(Hotel hotel) {
        return false;
    }

    public User registerUser (User user) throws UserAlreadyExist {
        return commonDAO.save(user);
    }

    public User editUserInfo() {
        return null;
    }

    public boolean deleteUser(User user) {
        return false;
    }

    public Hotel findHotelByName(String hotelName) {
        return null;
    }

    public Hotel findHotelByCity(String cityName) {
        return null;
    }

    public List<Room> findRoomsByHotel(String hotelName) {
        return null;
    }

    public Room roomReservationByName(String userName) {
        return null;
    }

    public boolean cancelReservationByName(String userName) {
        return false;
    }
}
