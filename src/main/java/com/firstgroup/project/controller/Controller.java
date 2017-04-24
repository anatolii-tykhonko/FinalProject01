package com.firstgroup.project.controller;

import com.firstgroup.project.APIs.HotelsAPI;
import com.firstgroup.project.DAOs.GeneralDAO;
import com.firstgroup.project.hotels.Hotel;

public class Controller {
    HotelsAPI hotelsAPI = new HotelsAPI(new GeneralDAO());

    public Hotel addHotel(Hotel hotel){
        return hotelsAPI.addHotel(hotel);
    }


}
