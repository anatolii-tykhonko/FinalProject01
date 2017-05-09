package com.firstgroup.project.Controllers;

import com.firstgroup.project.DAOs.HotelDAO;
import com.firstgroup.project.Exceptions.HotelAlreadyExist;
import com.firstgroup.project.Exceptions.InvalidDateFormat;
import com.firstgroup.project.Exceptions.ValidStringNameException;
import com.firstgroup.project.entity.Hotel;
import com.firstgroup.project.entity.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MakeMeSm1Le- on 08.05.2017.
 */
public class HotelController implements HotelControllerInterface {
    private HotelDAO hotelDAO = new HotelDAO();

    /**
     * Данный метод выполняет создание нового екземпляра отеля и передает его на уровень HotelDAO в метод save(Hotel hotel).
     *
     * @param hotelName   - название отеля.
     * @param cityName    - название города.
     * @param roomPersons - количевство мест в номере.
     * @param roomPrice   - цена комнаты в грн./сутки.
     * @param date        - дата когда номер будет доступен.
     * @return - возвращает объект Hotel, если он был успешно сохранен.
     * @throws HotelAlreadyExist        - исключение бросается если в одном и том же городе продублировать создание отеля с одинаковым названием.
     * @throws ValidStringNameException - исключение бросается если в стоках по названиям вписать символы, числа или пустую строку.
     * @throws InvalidDateFormat        - исключение бросается вбить длиннее формат данных чем указан year.mm.dd.
     */
    public Hotel addHotel(String hotelName, String cityName, int roomPersons, double roomPrice, String date) throws HotelAlreadyExist, ValidStringNameException, InvalidDateFormat {
        List<Room> roomList = new ArrayList<>();
        if (date.length() > 10) {
            throw new InvalidDateFormat("Не верный формат даты!");
        }
        LocalDate dateAvailableFrom = LocalDate.of(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(8, 10)));
        roomList.add(new Room(roomPersons, roomPrice, dateAvailableFrom));
        return hotelDAO.save(new Hotel(hotelName, cityName, roomList));
    }

    /**
     * Данный метод предназначен для изменения полей объектов Hotel и передает его на уровень HotelDAO в метод update(Hotel hotel, int hotelIndex).
     *
     * @param hotelIndex   - индекс отеля для поиска в листе.
     * @param newHotelName - новое название отеля.
     * @param newCityName  - новое название города
     * @return - возвращает объект Hotel, если он был успешно перезаписан.
     * @throws ValidStringNameException - исключение бросается если в стоках по названиям вписать символы, числа или пустую строку.
     */
    public Hotel editHotelDetails(int hotelIndex, String newHotelName, String newCityName) throws ValidStringNameException {
        Hotel editedHotel = new Hotel(newHotelName, newCityName, hotelDAO.getDataBase().getHotelList().get(hotelIndex).getRoomList());
        return hotelDAO.update(editedHotel, hotelIndex);
    }

    /**
     * @param hotelIndex
     * @return boolean(true)
     * Данный метод принимает индекс отеля, индекс вводит пользователь с консоли в классе ConsoleHelper.
     * Данный метод возвращает true, поскольку все исключительные ситуации например hotelIndex <0 ловят на уровень выше.
     * Так же данный метод передает управление на уровень HotelDAO.
     */
    public boolean deleteHotel(int hotelIndex) {
        return hotelDAO.delete(hotelIndex);
    }

    /**
     * Данный метод предназначен для поиска отелей по определенному параметру(название отеля) и передает его на уровень HotelDAO в метод findHotelByName(hotelName).
     * Пользователь вводит параметр поиска с консоли в классе ConsoleHelper.
     *
     * @param hotelName - входящий параметр , который является параметром поиска метода.
     * @return - Возвращаемый результат метода - List<Hotel> отвечающий параметру поиска.
     */
    public List<Hotel> findHotelByName(String hotelName) {
        return hotelDAO.findHotelByName(hotelName);
    }


    /**
     * Данный метод предназначен для поиска отелей по определенному параметру(город отеля) и передает его на уровень HotelDAO в метод findHotelByCity(cityName).
     * Пользователь вводит параметр поиска с консоли в классе ConsoleHelper.
     *
     * @param cityName - входящий параметр , который является параметром поиска метода.
     * @return - Возвращаемый результат метода - List<Hotel> отвечающий параметру поиска.
     */

    public List<Hotel> findHotelByCity(String cityName) {
        return hotelDAO.findHotelByCity(cityName);
    }


}