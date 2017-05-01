package com.firstgroup.project.DAOs;

import com.firstgroup.project.Exceptions.HotelAlreadyExist;
import com.firstgroup.project.hotels.Hotel;

/**
 * Created by MakeMeSm1Le- on 24.04.2017.
 */
public interface HotelDAOInterface {
    Hotel save(Hotel obj) throws HotelAlreadyExist;
    boolean delete(int hotelIndex) ;
    Hotel update(Hotel obj);
    Hotel findHotelByName(Hotel obj);
    Hotel findHotelByCity(Hotel obj);


}
