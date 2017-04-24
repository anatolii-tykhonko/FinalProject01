package com.firstgroup.project.DAOs;

import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;
import com.firstgroup.project.hotels.User;

import java.util.List;

/**
 * Created by MakeMeSm1Le- on 24.04.2017.
 */
public class GeneralDAO implements HotelDAOInterface,RoomDAOInterface,UserDAOInterface {
    public User save(User obj) {
        return null;
    }

    public Hotel save(Hotel obj) {
        // to do
        return null;
    }

    public Room save(Room obj) {
        return null;
    }

    public boolean delete(User obj) {
        return false;
    }

    public boolean delete(Hotel obj) {
        return false;
    }

    public boolean delete(Room obj) {
        return false;
    }

    public User update(User obj) {
        return null;
    }

    public Hotel update(Hotel obj) {
        return null;
    }

    public Room update(Room obj) {
        return null;
    }

    public Hotel findHotelByName(Hotel obj) {
        return null;
    }

    public List<Room> findRoomsByHotel(Hotel hotel) {
        return null;
    }

    public Hotel findHotelByCity(Hotel obj) {
        return null;
    }
}
