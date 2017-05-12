package com.firstgroup.project.DAOs;

import com.firstgroup.project.entity.Hotel;
import com.firstgroup.project.entity.Room;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sonikb on 08.05.2017.
 */
public class RoomDAO implements RoomDAOInterface {

    private DBServiceSingleton dbServiceSingleton = DBServiceSingleton.getDBServiceInstance();

    public Room save(Room obj, int hotelIndex) {
        dbServiceSingleton.getDataBase().getHotelList().get(hotelIndex).getRoomList().add(obj);
        dbServiceSingleton.save();
        return obj;
    }

    public boolean delete(Room obj) {
        if (!dbServiceSingleton.getDataBase().getHotelList().stream().anyMatch(hotel -> hotel.getRoomList().contains(obj))) {
            return false;
        }
        boolean okdelete = dbServiceSingleton.getDataBase().getHotelList().stream().anyMatch(hotel -> hotel.getRoomList().remove(obj));
        dbServiceSingleton.save();
        return okdelete;
    }

    public Room update(Room obj, int hotelIndex, int roomIndex) {
        dbServiceSingleton.getDataBase().getHotelList().get(hotelIndex).getRoomList().get(roomIndex).setPersons(obj.getPersons());
        dbServiceSingleton.getDataBase().getHotelList().get(hotelIndex).getRoomList().get(roomIndex).setPrice(obj.getPrice());
        dbServiceSingleton.getDataBase().getHotelList().get(hotelIndex).getRoomList().get(roomIndex).setAvailableFrom(obj.getAvailableFrom());
        Room room = dbServiceSingleton.getDataBase().getHotelList().get(hotelIndex).getRoomList().get(roomIndex);
        dbServiceSingleton.save();
        return room;
    }

    public List<Hotel> findRoomsByHotel(String hotelName) {
        List<Hotel> roomList = dbServiceSingleton.getDataBase().getHotelList().stream().filter(hotel -> hotel.getHotelName().equals(hotelName)).collect(Collectors.toList());
        System.out.println("По Вашему запросу найдены следующие комнаты: ");

        return roomList;
    }

    public DBServiceSingleton getDbServiceSingleton() {
        return dbServiceSingleton;
    }
}
