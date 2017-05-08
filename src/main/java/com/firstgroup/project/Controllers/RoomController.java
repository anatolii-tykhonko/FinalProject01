package com.firstgroup.project.Controllers;

import com.firstgroup.project.DAOs.RoomDAO;
import com.firstgroup.project.Exceptions.IncorrectDataInput;
import com.firstgroup.project.Exceptions.ValidStringNameException;
import com.firstgroup.project.entity.Hotel;
import com.firstgroup.project.entity.Room;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by MakeMeSm1Le- on 08.05.2017.
 */
public class RoomController implements RoomControllerInterface {
    private RoomDAO roomDAO = new RoomDAO();

    public Room addRoom(int hotelIndex, int roomPersons, double roomPrice, String date) throws ValidStringNameException {
        if (date.length() > 10) {
            throw new IndexOutOfBoundsException();
        }
        Room newRoom = new Room(roomPersons, roomPrice, LocalDate.of(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(8, 10))));
        return roomDAO.save(newRoom, hotelIndex);
    }

    public Room editRoomDetails(int hotelIndex, int roomIndex, int roomPersons, double roomPrice, String date) {
        if (date.length() > 10) {
            throw new IndexOutOfBoundsException();
        }
        Room room = new Room(roomPersons, roomPrice, LocalDate.of(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(8, 10))));
        return roomDAO.update(room, hotelIndex, roomIndex);
    }

    public boolean deleteRoom(int hotelIndex, int roomIndex) {
        Hotel hotel = roomDAO.getDataBase().getHotelList().get(hotelIndex);
        Room room = hotel.getRoomList().get(roomIndex - 1);
        return roomDAO.delete(room);
    }

    public List<Hotel> findRoomsByHotel(String hotelName) {
        return roomDAO.findRoomsByHotel(hotelName);
    }
}
