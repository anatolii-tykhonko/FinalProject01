package com.firstgroup.project.Controllers;

import com.firstgroup.project.Exceptions.HotelAlreadyExist;
import com.firstgroup.project.Exceptions.IncorrectDataInput;
import com.firstgroup.project.Exceptions.ValidStringNameException;
import com.firstgroup.project.entity.Hotel;

import java.util.List;

/**
 * Created by MakeMeSm1Le- on 08.05.2017.
 */
public class HotelController implements HotelControllerInterface{
    @Override
    public Hotel addHotel(String hotelName, String cityName, int roomPersons, double roomPrice, String date) throws HotelAlreadyExist, ValidStringNameException {
        return null;
    }

    @Override
    public Hotel editHotelDetails(int hotelIndex, String newHotelName, String newCityName) throws ValidStringNameException {
        return null;
    }

    @Override
    public boolean deleteHotel(int hotelIndex) {
        return false;
    }

    @Override
    public List<Hotel> findHotelByName(String hotelName) throws IncorrectDataInput, ValidStringNameException {
        return null;
    }

    @Override
    public List<Hotel> findHotelByCity(String cityName) throws IncorrectDataInput, ValidStringNameException {
        return null;
    }
}
