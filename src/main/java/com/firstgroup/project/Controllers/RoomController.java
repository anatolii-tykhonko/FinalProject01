package com.firstgroup.project.Controllers;

import com.firstgroup.project.Exceptions.IncorrectDataInput;
import com.firstgroup.project.Exceptions.ValidStringNameException;
import com.firstgroup.project.entity.Hotel;
import com.firstgroup.project.entity.Room;

import java.util.List;

/**
 * Created by MakeMeSm1Le- on 08.05.2017.
 */
public class RoomController implements RoomControllerInterface {

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
    public List<Hotel> findRoomsByHotel(String hotelName) throws IncorrectDataInput, ValidStringNameException {
        return null;
    }
}
