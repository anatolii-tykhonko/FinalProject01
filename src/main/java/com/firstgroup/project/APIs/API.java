package com.firstgroup.project.APIs;

import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;
import com.firstgroup.project.hotels.User;

/**
 * Created by Sonikb on 22.04.2017.
 */
public interface API {
    Hotel addHotel();
    Hotel editHotelDetails();
    Room addRoom();
    Room editRoomDetails();
    boolean deleteRoom();
    boolean deleteHotel();
    User registerUser();
    User editUserInfo();
    boolean deleteUser();
    Hotel findHotelByName();
    Hotel findHotelByCity();
    Room roomReservationByName();
    boolean cancelReservationByName();
}
