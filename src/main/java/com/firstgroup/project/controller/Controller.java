package com.firstgroup.project.controller;

import com.firstgroup.project.APIs.HotelsAPI;
import com.firstgroup.project.hotels.Hotel;

public class Controller {
    HotelsAPI hotelsAPI = new HotelsAPI();

    public Hotel addHotel(Hotel hotel){
        return hotelsAPI.addHotel();
    }


}
