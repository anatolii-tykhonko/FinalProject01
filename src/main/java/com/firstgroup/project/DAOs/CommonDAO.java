package com.firstgroup.project.DAOs;

import com.firstgroup.project.Exceptions.HotelAlreadyExist;
import com.firstgroup.project.Exceptions.UserAlreadyExist;
import com.firstgroup.project.dataBase.DBService;
import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;
import com.firstgroup.project.hotels.User;

import java.util.List;

/**
 * Created by MakeMeSm1Le- on 24.04.2017.
 */
public class CommonDAO implements HotelDAOInterface, RoomDAOInterface, UserDAOInterface {

    private DBService dbService = new DBService();

    {
        DBService.load();
    }

    public User save (User obj) throws UserAlreadyExist {
        if (dbService.getDataBase().getUserMap().keySet().stream().anyMatch(email -> email.equals(obj.getEmail()))) {
            System.out.println("Юзер с таким имейлом уже существует"); //TODO will create exception if email is exist
            throw new UserAlreadyExist("Юзер уже существует");
        }
        dbService.getDataBase().getUserMap().put(obj.getEmail(),obj);
        return obj;
    }

    public Hotel save(Hotel obj) throws HotelAlreadyExist {
        if (dbService.getDataBase().getHotelList().stream().anyMatch(hotel -> hotel.equals(obj))) {
            System.out.println("Здесь нужно кинуть ексепшн что отель уже существунт");  ///TODO create throw exception
            throw new HotelAlreadyExist("Комната уже существует!");
        }
        dbService.getDataBase().getHotelList().add(obj);
        return obj;
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

    public DBService getDbService() {
        return dbService;
    }
}
