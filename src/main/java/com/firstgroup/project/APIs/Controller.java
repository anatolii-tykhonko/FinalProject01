package com.firstgroup.project.APIs;

import com.firstgroup.project.DAOs.CommonDAO;
import com.firstgroup.project.Exceptions.*;
import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;
import com.firstgroup.project.hotels.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller implements API {
    private CommonDAO commonDAO = new CommonDAO();

    public Hotel addHotel(String hotelName, String cityName, int roomPersons, double roomPrice, String date) throws HotelAlreadyExist {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room(roomPersons, roomPrice, LocalDate.of(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(8, 10)))));
        return commonDAO.save(new Hotel(hotelName, cityName, roomList));
    }

    public Room addRoom(int hotelIndex, int roomPersons, double roomPrice, String date) {
        Room newRoom = new Room(roomPersons, roomPrice, LocalDate.of(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(8, 10))));
        return commonDAO.save(newRoom, hotelIndex);
    }

    public boolean deleteHotel(int hotelIndex) {
        return commonDAO.delete(hotelIndex);
    }

    public boolean deleteRoom(Room room) {
        if (room != null) {
            System.out.println("consoleHelper true");
            return commonDAO.delete(room);
        }
        return false;
    }

    public User editUserInfo(User user) {
        if (user != null) {
            System.out.println("consoleHelper true");
            return commonDAO.update(user);
        }
        return null;
    }

    public User deleteUser(String email) throws UserNotCreated, CantDeleteCurrentUser {
        return commonDAO.delete(email);
    }

    public User registerUser(String name, String surname, String email, String password) throws UserAlreadyExist {

        return commonDAO.save(new User(name, surname, email, password));

    }

    public User addUser(String name, String surname, String email, String password) throws UserAlreadyExist {
        return commonDAO.add(new User(name, surname, email, password));
    }

    public boolean loginUser(String email, String password) throws IncorrectEmail, IncorrectPassword {

        return commonDAO.loginUser(email, password);
    }

    public Hotel editHotelDetails() {
        return null;
    }

    public Room editRoomDetails() {
        return null;
    }

    public Hotel findHotelByName(String hotelName) {
        return null;
    }

    public Hotel findHotelByCity(String cityName) {
        return null;
    }

    public List<Room> findRoomsByHotel(String hotelName) {
        return null;
    }

    public Room roomReservationByName(int hotelIndex, int roomIndex) throws InvalidRoomStatus, InvalidHotelStatus {
        if (commonDAO.getDataBase().getHotelList().get(hotelIndex).getRoomList().stream().allMatch(Room::isStatus))
            throw new InvalidHotelStatus("Все комнаты в этом отеле заняты!");
        Room room = getCommonDAO().getDataBase().getHotelList().get(hotelIndex).getRoomList().get(roomIndex);
        if (room.isStatus()) throw new InvalidRoomStatus("Эта комната сейчас занята");
        else {
            room.setStatus(true);
            getCommonDAO().getDataBase().getCurrentUser().getRoomList().add(room);
            System.out.println("Комната успешно забронирована!!!");
        }
        return room;
    }

    public boolean cancelReservationByName(int roomIndex) {
        commonDAO.getDataBase().getCurrentUser().getRoomList().get(roomIndex).setStatus(false);
        commonDAO.getDataBase().getCurrentUser().getRoomList().remove(roomIndex);
        return true;
    }

    public CommonDAO getCommonDAO() {
        return commonDAO;
    }
}
