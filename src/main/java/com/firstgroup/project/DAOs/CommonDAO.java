package com.firstgroup.project.DAOs;

import com.firstgroup.project.Exceptions.HotelAlreadyExist;
import com.firstgroup.project.Exceptions.IncorrectEmail;
import com.firstgroup.project.Exceptions.IncorrectPassword;
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
        if (dbService.getDataBase().getUserMap().keySet().contains(obj.getEmail())) {
            throw new UserAlreadyExist("Юзер с таким имейлом уже существует");
        }
        dbService.getDataBase().getUserMap().put(obj.getEmail(),obj);
        return obj;
    }

    public Hotel save(Hotel obj) throws HotelAlreadyExist {
        if (dbService.getDataBase().getHotelList().contains(obj)) {
            System.out.println("Здесь нужно кинуть ексепшн что отель уже существунт");  ///TODO create throw exception
            throw new HotelAlreadyExist("Комната уже существует!");
        }
        dbService.getDataBase().getHotelList().add(obj);
        return obj;
    }

    public Room save(Room obj, int hotelIndex) {
        dbService.getDataBase().getHotelList().get(hotelIndex).getRoomList().add(obj);
        return obj;
    }

    public boolean delete(User obj) {
        return false;
    }

    public boolean delete(Hotel obj) {
        if(dbService.getDataBase().getHotelList().contains(obj)){
            dbService.getDataBase().getHotelList().remove(obj);
            return true;
        }
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

    public boolean loginUser(String email, String password) throws IncorrectEmail, IncorrectPassword {
        if (dbService.getDataBase().getUserMap().keySet().contains(email)){
            if (dbService.getDataBase().getUserMap().get(email).getPassword().equals(password)) {
                getDbService().getDataBase().setCurrentUser(getDbService().getDataBase().getUserMap().get(email));
                return true;
            }else throw new IncorrectPassword("Не верный пароль! Повторите ввод!");
        }else throw new IncorrectEmail("Юзера с таким email не существует! Повторите ввод!");

    }

    public DBService getDbService() {
        return dbService;
    }
}
