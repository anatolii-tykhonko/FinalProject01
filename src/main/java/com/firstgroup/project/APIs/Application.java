package com.firstgroup.project.APIs;

import com.firstgroup.project.Controllers.*;
import com.firstgroup.project.Exceptions.*;
import com.firstgroup.project.entity.Hotel;
import com.firstgroup.project.entity.Room;
import com.firstgroup.project.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Данный класс реализует доступ к методам АРІ.
 */

public class Application implements API {
    private HotelControllerInterface hotelController;
    private RoomControllerInterface roomController;
    private UserControllerInterface userController;

    public Application(HotelControllerInterface hotelController, RoomControllerInterface roomController, UserControllerInterface userController) {
        this.hotelController = hotelController;
        this.roomController = roomController;
        this.userController = userController;
    }

    public Hotel addHotel(String hotelName, String cityName, int roomPersons, double roomPrice, String date) throws HotelAlreadyExist, ValidStringNameException, InvalidDateFormat {
        return hotelController.addHotel(hotelName, cityName, roomPersons, roomPrice, date);
    }

    public Hotel editHotelDetails(int hotelIndex, String newHotelName, String newCityName) throws ValidStringNameException {
        return hotelController.editHotelDetails(hotelIndex, newHotelName, newCityName);
    }

    public Room addRoom(int hotelIndex, int roomPersons, double roomPrice, String date) throws ValidStringNameException, InvalidDateFormat {
        return roomController.addRoom(hotelIndex, roomPersons, roomPrice, date);
    }

    public Room editRoomDetails(int hotelIndex, int roomIndex, int roomPersons, double roomPrice, String dateAvailableFrom) throws InvalidDateFormat {
        return roomController.editRoomDetails(hotelIndex, roomIndex, roomPersons, roomPrice, dateAvailableFrom);
    }

    public boolean deleteRoom(int hotelIndex, int roomIndex) {
        return roomController.deleteRoom(hotelIndex, roomIndex);
    }

    public boolean deleteHotel(int hotelIndex) {
        return hotelController.deleteHotel(hotelIndex);
    }

    public User registerUser(String name, String surname, String email, String password, boolean regTRUEaddFALSE) throws UserAlreadyExist {
        return userController.registerUser(name, surname, email, password, regTRUEaddFALSE);
    }

    public User editUserInfo(String newName, String newSurName, String oldEmail) {
        return userController.editUserInfo(newName, newSurName, oldEmail);
    }

    public User deleteUser(String email) throws CantDeleteCurrentUser {
        return userController.deleteUser(email);
    }

    public List<Hotel> findHotelByName(String hotelName) {
        return hotelController.findHotelByName(hotelName);
    }

    public List<Hotel> findHotelByCity(String cityName) {
        return hotelController.findHotelByCity(cityName);
    }

    public List<Hotel> findRoomsByHotel(String hotelName) {
        return roomController.findRoomsByHotel(hotelName);
    }

    public Room roomReservationByName(int hotelIndex, int roomIndex, String reservDate) throws InvalidRoomStatus, InvalidHotelStatus, InvalidDateFormat {
        return userController.roomReservationByName(hotelIndex, roomIndex, reservDate);
    }

    public boolean cancelReservationByName(int roomIndex) {
        return userController.cancelReservationByName(roomIndex);
    }

    public boolean loginUser(String email, String password) throws IncorrectEmail, IncorrectPassword {
        return userController.loginUser(email, password);
    }

//    public Map<String, User> getUsers() {
//        return userController.getUsers();
//    }
//
//    public List<Hotel> getHotels() {
//        return hotelController.getHotels();
//    }

    public User curUser() {
        return userController.getCurrentUser();
    }

    public void validLine(String line) throws ValidStringNameException {
        if (line.contains("1") || line.contains("2") || line.contains("3") ||
                line.contains("4") || line.contains("5") || line.contains("6") ||
                line.contains("7") || line.contains("8") || line.contains("9") ||
                line.contains("0") || line.contains("^") || line.contains("-") ||
                line.contains("?") || line.contains("+") || line.contains("=") ||
                line.contains("_") || line.contains("\\") || line.contains("/") ||
                line.contains("@") || line.contains("#") || line.contains("$") ||
                line.contains("%") || line.contains("&") || line.contains("$") ||
                line.contains("*") || line.contains("(") || line.contains(")") ||
                line.contains("`") || line.contains("!") || line.contains("~") ||
                line.contains("{") || line.contains("}") || line.contains("[") ||
                line.contains("]") || line.contains("<") || line.contains(">") ||
                line.contains(";") || line.contains(":") || line.contains("'") ||
                line.contains(",") || line.contains(".") || line.isEmpty()) {
            throw new ValidStringNameException("Название не должно включать цифры или символы!!!\nВведите данные заново!\n");
        }
    }

    public void showHotelList() {
        int count = 1;
        List<Hotel> hotelList = hotelController.getHotels();
        for (Hotel hotel : hotelList) {
            System.out.println(count++ + ". * Отель " + hotel.getHotelName() + ", город " + hotel.getCityName());
        }
    }

    public Hotel getHotelsByIndex(int hotelIndex) {
        return hotelController.getHotels().get(hotelIndex);
    }

    public boolean isEmptyHotelsList(){
        return hotelController.getHotels().isEmpty();
    }

    public void showRoomList(Hotel hotel) {
        int count = 1;
        for (Room room : hotel.getRoomList()) {
            System.out.println(count++ + ". * " + room);
        }
    }

//    public void showUsersList() {
//        Set<Map.Entry<String, User>> entrySet = userController.getUsers().entrySet();
//        int count = 1;
//        List<String> emailList = new ArrayList<>();
//        for (Map.Entry<String, User> userEntry : entrySet) {
//            System.out.println(count++ + ". * " + userEntry.getValue());
//            emailList.add(userEntry.getValue().getEmail());
//        }
//    }

    public void showCityNameList() {
        int count = 1;
        List<String> cityNames = hotelController.getHotels()
                .stream()
                .map(Hotel::getCityName)
                .distinct()
                .collect(Collectors.toList());

        for (String cityName : cityNames) {
            System.out.println(count++ + ". " + cityName);
        }
    }

    public List<String> makeEmailUserList() {
        Set<Map.Entry<String, User>> entries = userController.getUsers().entrySet();
        int count = 1;
        List<String> emailList = new ArrayList<>();
        for (Map.Entry<String, User> userEntry : entries) {
            System.out.println(count++ + "." + " " + userEntry.getValue());
            emailList.add(userEntry.getValue().getEmail());
        }
        return emailList;
    }
}
