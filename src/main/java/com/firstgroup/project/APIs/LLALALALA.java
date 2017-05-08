package com.firstgroup.project.APIs;

import com.firstgroup.project.DAOs.CommonDAO;
import com.firstgroup.project.Exceptions.*;
import com.firstgroup.project.entity.Hotel;
import com.firstgroup.project.entity.Room;
import com.firstgroup.project.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LLALALALA implements API {
    private CommonDAO commonDAO = new CommonDAO();

    /**
     * Данный метод выполняет создание нового екземпляра отеля и передает его на уровень CommonDAO в метод save(Hotel obj).
     * Возвращаемый результат метода только что созданный объект класса Hotel.
     * Метод кидает ексепшини HotelAlreadyExist - если отель уже существует, ValidStringNameException - если название включает цифры или символы.
     */
    public Hotel addHotel(String hotelName, String cityName, int roomPersons, double roomPrice, String date) throws HotelAlreadyExist, ValidStringNameException {
        List<Room> roomList = new ArrayList<>();
        if (date.length() > 10) {
            throw new IndexOutOfBoundsException();
        }
        LocalDate dateAvailableFrom = LocalDate.of(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(8, 10)));
        roomList.add(new Room(roomPersons, roomPrice, dateAvailableFrom));
        return commonDAO.save(new Hotel(hotelName, cityName, roomList));
    }

    public Room addRoom(int hotelIndex, int roomPersons, double roomPrice, String date) throws ValidStringNameException {
        if (date.length() > 10) {
            throw new IndexOutOfBoundsException();
        }
        Room newRoom = new Room(roomPersons, roomPrice, LocalDate.of(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(8, 10))));
        return commonDAO.save(newRoom, hotelIndex);
    }

    /**
     * @param hotelIndex
     * @return boolean(true)
     * Данный метод принимает индекс отеля, индекс вводит пользователь с консоли в классе ConsoleHelper.
     * Данный метод возвращает true, поскольку все исключительные ситуации например hotelIndex <0 ловят на уровень выше.
     * Так же данный метод передает управление на уровень CommonDAO.
     */
    public boolean deleteHotel(int hotelIndex) {
        return commonDAO.delete(hotelIndex);
    }

    public boolean deleteRoom(int hotelIndex, int roomIndex) {
        Hotel hotel = commonDAO.getDataBase().getHotelList().get(hotelIndex);
        Room room = hotel.getRoomList().get(roomIndex - 1);
        return commonDAO.delete(room);
    }

    /**
     * @param newName
     * @param newSurName
     * @param oldEmail
     * @return user
     * Данный метод принимает набор параметров, которые вводит пользователь в классе ConsoleHelper.
     * Все исключительные ситуации обрабатываются на уровень выше.
     * Данный метод возвращает экземпляр типа User,данный экземпляр служит "контейнером" для хранения данных.
     */
    public User editUserInfo(String newName, String newSurName, String oldEmail) {
        User user = new User(newName, newSurName, oldEmail, commonDAO.getDataBase().getUserMap().get(oldEmail).getPassword());
        return commonDAO.update(user);
    }

    public User deleteUser(String email) throws CantDeleteCurrentUser {
        return commonDAO.delete(email);
    }

    public User registerUser(String name, String surname, String email, String password, boolean regTRUEaddFALSE) throws UserAlreadyExist {
        return commonDAO.save(new User(name, surname, email, password), regTRUEaddFALSE);
    }

    public boolean loginUser(String email, String password) throws IncorrectEmail, IncorrectPassword {
        return commonDAO.loginUser(email, password);
    }

    public Hotel editHotelDetails(int hotelIndex, String newHotelName, String newCityName) throws ValidStringNameException {
        Hotel editedHotel = new Hotel(newHotelName, newCityName, commonDAO.getDataBase().getHotelList().get(hotelIndex).getRoomList());
        return commonDAO.update(editedHotel, hotelIndex);
    }

    /**
     * @param hotelIndex
     * @param roomIndex
     * @param roomPersons
     * @param roomPrice
     * @param date
     * @return
     */
    public Room editRoomDetails(int hotelIndex, int roomIndex, int roomPersons, double roomPrice, String date) {
        if (date.length() > 10) {
            throw new IndexOutOfBoundsException();
        }
        Room room = new Room(roomPersons, roomPrice, LocalDate.of(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(8, 10))));
        return commonDAO.update(room, hotelIndex, roomIndex);
    }

    public List<Hotel> findHotelByName(String hotelName) {

        return commonDAO.findHotelByName(hotelName);
    }

    public List<Hotel> findHotelByCity(String cityName) {

        return commonDAO.findHotelByCity(cityName);
    }

    public List<Hotel> findRoomsByHotel(String hotelName) {
        return commonDAO.findRoomsByHotel(hotelName);
    }

    public Room roomReservationByName(int hotelIndex, int roomIndex, String reservDate) throws InvalidRoomStatus, InvalidHotelStatus, InvalidDateFormat {
        if (commonDAO.getDataBase().getHotelList().get(hotelIndex).getRoomList().stream().allMatch(Room::isStatus))
            throw new InvalidHotelStatus("Все комнаты в этом отеле заняты!");
        if (reservDate.length() > 10) {
            throw new InvalidDateFormat("Не верный формат даты!");
        }
        Room room = getCommonDAO().getDataBase().getHotelList().get(hotelIndex).getRoomList().get(roomIndex);
        LocalDate reserv = LocalDate.of(Integer.valueOf(reservDate.substring(0, 4)), Integer.valueOf(reservDate.substring(5, 7)), Integer.valueOf(reservDate.substring(8, 10)));
        if (room.getAvailableFrom().getYear() > reserv.getYear() || room.getAvailableFrom().getYear() == reserv.getYear() && room.getAvailableFrom().getMonthValue() > reserv.getMonthValue() || room.getAvailableFrom().getYear() == reserv.getYear() && room.getAvailableFrom().getMonthValue() == reserv.getMonthValue() && room.getAvailableFrom().getDayOfMonth() >= reserv.getDayOfMonth()) {
            throw new InvalidDateFormat("Не коректная дата, Вы ввели дату на которую эта комната занята!");
        }
        if (room.isStatus()) throw new InvalidRoomStatus("Эта комната сейчас занята");
        else {
            room.setStatus(true);
            room.setReservBefore(reserv);
            getCommonDAO().getDataBase().getCurrentUser().getRoomList().add(room);
        }
        return room;
    }

    public boolean cancelReservationByName(int roomIndex) {
        commonDAO.getDataBase().getCurrentUser().getRoomList().get(roomIndex).setStatus(false);
        commonDAO.getDataBase().getCurrentUser().getRoomList().remove(roomIndex);
        return true;
    }

    public List<String> hotelsAndTownInSystem(byte criteriaFind) {
        List<String> namesHotelInSystem = commonDAO.getDataBase().getHotelList().stream().map(hotel -> hotel.getHotelName()).collect(Collectors.toList());
        List<String> cityInSystem = commonDAO.getDataBase().getHotelList().stream().map(hotel -> hotel.getCityName()).collect(Collectors.toList());
        if (criteriaFind == 0) {
            return namesHotelInSystem;
        } else {
            return cityInSystem;
        }
    }

    public CommonDAO getCommonDAO() {
        return commonDAO;
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
}
