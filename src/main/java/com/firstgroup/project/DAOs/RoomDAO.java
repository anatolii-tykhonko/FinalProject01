package com.firstgroup.project.DAOs;

import com.firstgroup.project.entity.Hotel;
import com.firstgroup.project.entity.Room;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sonikb on 08.05.2017.
 */
public class RoomDAO extends DBService implements RoomDAOInterface {

    public Room save(Room obj, int hotelIndex) {
        getDataBase().getHotelList().get(hotelIndex).getRoomList().add(obj);
        return obj;
    }

    public boolean delete(Room obj) {
        if (getDataBase().getHotelList().stream().anyMatch(hotel -> hotel.getRoomList().contains(obj))) {
            return getDataBase().getHotelList().stream().anyMatch(hotel -> hotel.getRoomList().remove(obj));
        }
        return false;
    }

    public Room update(Room obj, int hotelIndex, int roomIndex) {
        getDataBase().getHotelList().get(hotelIndex).getRoomList().get(roomIndex).setPersons(obj.getPersons());
        getDataBase().getHotelList().get(hotelIndex).getRoomList().get(roomIndex).setPrice(obj.getPrice());
        getDataBase().getHotelList().get(hotelIndex).getRoomList().get(roomIndex).setAvailableFrom(obj.getAvailableFrom());

        return getDataBase().getHotelList().get(hotelIndex).getRoomList().get(roomIndex);
    }

    public List<Hotel> findRoomsByHotel(String hotelName) {
        List<Hotel> roomList = getDataBase().getHotelList().stream().filter(hotel -> hotel.getHotelName().equals(hotelName)).collect(Collectors.toList());
        System.out.println("По Вашему запросу найдены следующие комнаты: ");

        return roomList;
    }
}
