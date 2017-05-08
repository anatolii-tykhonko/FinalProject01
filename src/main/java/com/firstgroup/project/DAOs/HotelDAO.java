package com.firstgroup.project.DAOs;

import com.firstgroup.project.Exceptions.HotelAlreadyExist;
import com.firstgroup.project.Exceptions.IncorrectDataInput;
import com.firstgroup.project.dataBase.DataBase;
import com.firstgroup.project.entity.Hotel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MakeMeSm1Le- on 08.05.2017.
 */
public class HotelDAO extends DBService implements HotelDAOInterface {

    public Hotel save(Hotel hotel) throws HotelAlreadyExist {
        if (getDataBase().getHotelList().contains(hotel)) {
            throw new HotelAlreadyExist("Отель c таким именем уже существует в этом городе!");
        }
        getDataBase().getHotelList().add(hotel);
        return hotel;
    }

    public boolean delete(int hotelIndex) {
        getDataBase().getHotelList().remove(hotelIndex);
        return true;
    }

    public Hotel update(Hotel hotel, int hotelIndex) {
        Hotel editHotel = getDataBase().getHotelList().get(hotelIndex);
        editHotel.setHotelName(hotel.getHotelName());
        editHotel.setCityName(hotel.getCityName());
        return getDataBase().getHotelList().get(hotelIndex);
    }

    public List<Hotel> findHotelByName(String hotelName) throws IncorrectDataInput {
        List<Hotel> hotelList = getDataBase().getHotelList().stream().filter(hotel -> hotel.getHotelName().equals(hotelName)).collect(Collectors.toList());
        System.out.println("По Вашему запросу найдены следующие отели: ");
        return hotelList;
    }

    public List<Hotel> findHotelByCity(String cityName) throws IncorrectDataInput {
        List<Hotel> hotelList = getDataBase().getHotelList().stream().filter(hotel -> hotel.getCityName().equals(cityName)).collect(Collectors.toList());
        System.out.println("По Вашему запросу найдены следующие отели: ");
        return hotelList;
    }
}