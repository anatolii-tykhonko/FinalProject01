package com.firstgroup.project.DAOs;

import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;

import java.util.List;

/**
 * Created by Sonikb on 22.04.2017.
 */
public class RoomDAO implements DAO<Room> {

    public Room save(Room obj) {
        return null;
    }

    public boolean delete(Room obj) {
        return false;
    }

    public Room update(Room obj) {
        return null;
    }

    public List<Room> findRoomsByHotel(Hotel hotel){
        return null;
    }
}
