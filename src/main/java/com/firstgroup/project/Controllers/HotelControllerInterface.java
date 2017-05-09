package com.firstgroup.project.Controllers;

import com.firstgroup.project.Exceptions.HotelAlreadyExist;
import com.firstgroup.project.Exceptions.IncorrectDataInput;
import com.firstgroup.project.Exceptions.InvalidDateFormat;
import com.firstgroup.project.Exceptions.ValidStringNameException;
import com.firstgroup.project.entity.Hotel;

import java.util.List;

/**
 * Created by MakeMeSm1Le- on 08.05.2017.
 */
public interface HotelControllerInterface {
    Hotel addHotel(String hotelName, String cityName, int roomPersons, double roomPrice, String date) throws HotelAlreadyExist, ValidStringNameException, InvalidDateFormat;

    Hotel editHotelDetails(int hotelIndex, String newHotelName, String newCityName) throws ValidStringNameException;

    boolean deleteHotel(int hotelIndex);

    List<Hotel> findHotelByName(String hotelName);

    List<Hotel> findHotelByCity(String cityName);


}
