package com.firstgroup.project.DAOs;

import com.firstgroup.project.Exceptions.*;
import com.firstgroup.project.dataBase.DBService;
import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;
import com.firstgroup.project.hotels.User;

import java.util.List;
import java.util.Scanner;

/**
 * Created by MakeMeSm1Le- on 24.04.2017.
 */
public class CommonDAO implements HotelDAOInterface, RoomDAOInterface, UserDAOInterface {

    private DBService dbService = new DBService();

    {
        DBService.load();
    }

    public User save(User obj) throws UserAlreadyExist {
        if (dbService.getDataBase().getUserMap().keySet().contains(obj.getEmail())) {
            throw new UserAlreadyExist("Юзер с таким имейлом уже существует");
        }
        dbService.getDataBase().getUserMap().put(obj.getEmail(), obj);
        dbService.getDataBase().setCurrentUser(obj);
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

    public User delete(String email) throws UserNotCreated, CantDeleteCurrentUser {
        if (getDbService().getDataBase().getCurrentUser().equals(getDbService().getDataBase().getUserMap().get(email))) throw new CantDeleteCurrentUser("Нельзя удалить текущего юзера! Повторите попытку!");
        if (!getDbService().getDataBase().getUserMap().keySet().contains(email)) throw new UserNotCreated("Неверный email! Попробуйте снова!");
        return getDbService().getDataBase().getUserMap().remove(email);
    }

    public boolean delete(Hotel obj) {
        if (dbService.getDataBase().getHotelList().contains(obj)) {
            dbService.getDataBase().getHotelList().remove(obj);
            return true;
        }
        return false;
    }

    public boolean delete(Room obj) {
        if(dbService.getDataBase().getHotelList().stream().anyMatch(hotel -> hotel.getRoomList().contains(obj))){
            System.out.println("deleteDAo true");
            return dbService.getDataBase().getHotelList().stream().anyMatch(hotel -> hotel.getRoomList().remove(obj));
        }
        return false;
    }

    public User update(User obj) {
        System.out.println("Введите новое имя пользувателя: ");
        Scanner in = new Scanner(System.in);
        String newName = in.nextLine();
        obj.setName(newName);
        System.out.println("Введите новою фамилию пользувателя: ");
        String newSurName = in.nextLine();
        obj.setSurname(newSurName);
        System.out.println("Введите новий пароль ");
        String newPassword = in.nextLine();
        obj.setPassword(newPassword);
        return  obj;
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
        if (dbService.getDataBase().getUserMap().keySet().contains(email)) {
            if (dbService.getDataBase().getUserMap().get(email).getPassword().equals(password)) {
                getDbService().getDataBase().setCurrentUser(getDbService().getDataBase().getUserMap().get(email));
                return true;
            } else throw new IncorrectPassword("Не верный пароль! Повторите ввод!\n");
        } else throw new IncorrectEmail("Юзера с таким email не существует! Повторите ввод!\n");

    }

    public DBService getDbService() {
        return dbService;
    }
}
