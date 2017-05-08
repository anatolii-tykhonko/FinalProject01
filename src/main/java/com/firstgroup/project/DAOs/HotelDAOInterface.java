package com.firstgroup.project.DAOs;

import com.firstgroup.project.Exceptions.HotelAlreadyExist;
import com.firstgroup.project.Exceptions.IncorrectDataInput;
import com.firstgroup.project.entity.Hotel;
import java.util.List;

/**
 * Created by MakeMeSm1Le- on 24.04.2017.
 */
public interface HotelDAOInterface {
    Hotel save(Hotel hotel) throws HotelAlreadyExist;
    boolean delete(int hotelIndex) ;
    Hotel update(Hotel hotel, int hotelIndex);
    List<Hotel> findHotelByName(String hotelName);
    List<Hotel> findHotelByCity(String cityName);
}
