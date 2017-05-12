package com.firstgroup.project.DAOs;

import com.firstgroup.project.Exceptions.HotelAlreadyExist;
import com.firstgroup.project.entity.Hotel;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MakeMeSm1Le- on 08.05.2017.
 */
public class HotelDAO implements HotelDAOInterface {

    private DBServiceSingleton dbServiceSingleton = DBServiceSingleton.getDBServiceInstance();

    public Hotel save(Hotel hotel) throws HotelAlreadyExist {
        if (dbServiceSingleton.getDataBase().getHotelList().contains(hotel)) {
            throw new HotelAlreadyExist("Отель c таким именем уже существует в этом городе!");
        }
        dbServiceSingleton.getDataBase().getHotelList().add(hotel);
        dbServiceSingleton.save();
        return hotel;
    }

    public boolean delete(int hotelIndex) {
        dbServiceSingleton.getDataBase().getHotelList().remove(hotelIndex);
        dbServiceSingleton.save();
        return true;
    }

    public Hotel update(Hotel hotel, int hotelIndex) {
        Hotel editHotel = dbServiceSingleton.getDataBase().getHotelList().get(hotelIndex);
        editHotel.setHotelName(hotel.getHotelName());
        editHotel.setCityName(hotel.getCityName());
        dbServiceSingleton.save();
        return dbServiceSingleton.getDataBase().getHotelList().get(hotelIndex);
    }

    public List<Hotel> findHotelByName(String hotelName) {
        List<Hotel> hotelList = dbServiceSingleton.getDataBase().getHotelList().stream().filter(hotel -> hotel.getHotelName().equals(hotelName)).collect(Collectors.toList());
        System.out.println("По Вашему запросу найдены следующие отели: ");
        return hotelList;
    }

    public List<Hotel> findHotelByCity(String cityName) {
        List<Hotel> hotelList = dbServiceSingleton.getDataBase().getHotelList().stream().filter(hotel -> hotel.getCityName().equals(cityName)).collect(Collectors.toList());

        return hotelList;
    }

    public DBServiceSingleton getDbServiceSingleton() {
        return dbServiceSingleton;
    }
}