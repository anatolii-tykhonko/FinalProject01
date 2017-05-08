package com.firstgroup.project.Controllers;

import com.firstgroup.project.DAOs.HotelDAO;
import com.firstgroup.project.Exceptions.HotelAlreadyExist;
import com.firstgroup.project.Exceptions.IncorrectDataInput;
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
     * Данный метод выполняет создание нового екземпляра отеля и передает его на уровень HotelDAO в метод save(Hotel obj).
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
        return hotelDAO.save(new Hotel(hotelName, cityName, roomList));
    }

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

    public List<Hotel> findHotelByName(String hotelName) throws IncorrectDataInput {
        return hotelDAO.findHotelByName(hotelName);
    }

    public List<Hotel> findHotelByCity(String cityName) throws IncorrectDataInput {
        return hotelDAO.findHotelByCity(cityName);
    }
}