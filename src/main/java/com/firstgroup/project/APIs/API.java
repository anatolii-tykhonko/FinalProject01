package com.firstgroup.project.APIs;

import com.firstgroup.project.Exceptions.HotelAlreadyExist;
import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;
import com.firstgroup.project.hotels.User;

import java.util.List;

/**
 * Created by Sonikb on 22.04.2017.
 */
public interface API {
    Hotel addHotel(Hotel hotel) throws HotelAlreadyExist;
    Hotel editHotelDetails();
    Room addRoom(Room room);
    Room editRoomDetails();
    boolean deleteRoom(Room room);
    boolean deleteHotel(Hotel hotel);
    User registerUser(User user);
    User editUserInfo();
    boolean deleteUser(User user);
    Hotel findHotelByName(String hotelName);
    Hotel findHotelByCity(String cityName);
    List<Room> findRoomsByHotel(String hotelName);
    Room roomReservationByName(String userName);
    boolean cancelReservationByName(String userName);
}
