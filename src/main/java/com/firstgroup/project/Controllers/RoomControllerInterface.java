package com.firstgroup.project.Controllers;

import com.firstgroup.project.Exceptions.*;
import com.firstgroup.project.entity.Hotel;
import com.firstgroup.project.entity.Room;

import java.util.List;

/**
 * Created by MakeMeSm1Le- on 08.05.2017.
 */
public interface RoomControllerInterface {
    Room addRoom(int hotelIndex, int roomPersons, double roomPrice, String date) throws ValidStringNameException;
    Room editRoomDetails(int hotelIndex, int roomIndex, int roomPersons, double roomPrice, String dateAvailableFrom);
    boolean deleteRoom(int hotelIndex,int roomIndex);
    List<Hotel> findRoomsByHotel(String hotelName) throws IncorrectDataInput;
}
