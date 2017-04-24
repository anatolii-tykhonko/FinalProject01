package com.firstgroup.project.DAOs;

import com.firstgroup.project.hotels.Hotel;

/**
 * Created by MakeMeSm1Le- on 24.04.2017.
 */
public interface HotelDAOInterface {
    public Hotel save(Hotel obj);
    public boolean delete(Hotel obj) ;
    public Hotel update(Hotel obj);
    public Hotel findHotelByName(Hotel obj);
    public Hotel findHotelByCity(Hotel obj);


}
