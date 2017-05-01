package com.firstgroup.project.APIs;

import com.firstgroup.project.Exceptions.CantDeleteCurrentUser;
import com.firstgroup.project.Exceptions.HotelAlreadyExist;
import com.firstgroup.project.Exceptions.UserAlreadyExist;
import com.firstgroup.project.Exceptions.UserNotCreated;
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
    Room addRoom(Room room, int hotelsIndex);
    Room editRoomDetails();
    boolean deleteRoom(Room room);
    boolean deleteHotel(Hotel hotel);
    User registerUser(User user) throws UserAlreadyExist;
    User editUserInfo(User user);
    User deleteUser(String email) throws UserNotCreated, CantDeleteCurrentUser;
    Hotel findHotelByName(String hotelName);
    Hotel findHotelByCity(String cityName);
    List<Room> findRoomsByHotel(String hotelName);
    Room roomReservationByName(String userName);
    boolean cancelReservationByName(String userName);
}
