package com.firstgroup.project.DAOs;

import com.firstgroup.project.Exceptions.*;
import com.firstgroup.project.dataBase.DataBase;
import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;
import com.firstgroup.project.hotels.User;

import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.util.List;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by MakeMeSm1Le- on 24.04.2017.
 */
public class CommonDAO implements HotelDAOInterface, RoomDAOInterface, UserDAOInterface {

    private static DataBase dataBase = new DataBase();

    {
        load();
    }

    public User save(User user, boolean regTRUEaddFALSE) throws UserAlreadyExist {
        if (dataBase.getUserMap().keySet().contains(user.getEmail())) {
            throw new UserAlreadyExist("Юзер с таким имейлом уже существует");
        }
        dataBase.getUserMap().put(user.getEmail(), user);
        if (regTRUEaddFALSE) dataBase.setCurrentUser(user);

        return user;
    }

    public Hotel save(Hotel obj) throws HotelAlreadyExist {
        if (dataBase.getHotelList().contains(obj)) {
            throw new HotelAlreadyExist("Отель c таким именем уже существует в єтом городе!");
        }
        dataBase.getHotelList().add(obj);
        return obj;
    }

    public Room save(Room obj, int hotelIndex) {
        dataBase.getHotelList().get(hotelIndex).getRoomList().add(obj);
        return obj;
    }

    public User delete(String email) throws CantDeleteCurrentUser {
        if (dataBase.getCurrentUser().equals(dataBase.getUserMap().get(email)))
            throw new CantDeleteCurrentUser("Нельзя удалить текущего юзера! Повторите попытку!");
        return dataBase.getUserMap().remove(email);
    }

    public boolean delete(int hotelIndex) {
        dataBase.getHotelList().remove(hotelIndex);
        return true;
    }

    public boolean delete(Room obj) {
        if (dataBase.getHotelList().stream().anyMatch(hotel -> hotel.getRoomList().contains(obj))) {
            return dataBase.getHotelList().stream().anyMatch(hotel -> hotel.getRoomList().remove(obj));
        }
        return false;
    }

    public User update(User user) {
        dataBase.getUserMap().get(user.getEmail()).setName(user.getName());
        dataBase.getUserMap().get(user.getEmail()).setSurname(user.getSurname());
        return user;
    }

    public Hotel update(Hotel hotel, int hotelIndex) {
        Hotel editHotel = dataBase.getHotelList().get(hotelIndex);
        editHotel.setHotelName(hotel.getHotelName());
        editHotel.setCityName(hotel.getCityName());
        return getDataBase().getHotelList().get(hotelIndex);
    }

    public List<Hotel> findHotelByName(String hotelName) {
        List<Hotel> hotelList = getDataBase().getHotelList().stream().filter(hotel -> hotel.getHotelName().equals(hotelName)).collect(Collectors.toList());
        System.out.println("По Вашему запросу найдены следующие отели: ");
        return hotelList;
    }

    public List<Hotel> findHotelByCity(String cityName) {
        List<Hotel> hotelList = getDataBase().getHotelList().stream().filter(hotel -> hotel.getCityName().equals(cityName)).collect(Collectors.toList());
        System.out.println("По Вашему запросу найдены следующие отели: ");
        return hotelList;
    }

    public List<Hotel> findRoomsByHotel(String hotelName) {
        List<Hotel> roomList = getDataBase().getHotelList().stream().filter(hotel -> hotel.getHotelName().equals(hotelName)).collect(Collectors.toList());
        System.out.println("По Вашему запросу найдены следующие комнаты: ");

        return roomList;
    }

    public Room update(Room obj, int hotelIndex, int roomIndex) {
        dataBase.getHotelList().get(hotelIndex).getRoomList().get(roomIndex).setPersons(obj.getPersons());
        dataBase.getHotelList().get(hotelIndex).getRoomList().get(roomIndex).setPrice(obj.getPrice());
        dataBase.getHotelList().get(hotelIndex).getRoomList().get(roomIndex).setAvailableFrom(obj.getAvailableFrom());

        return dataBase.getHotelList().get(hotelIndex).getRoomList().get(roomIndex);
    }

    public boolean loginUser(String email, String password) throws IncorrectEmail, IncorrectPassword {
        if (dataBase.getUserMap().keySet().contains(email)) {
            if (dataBase.getUserMap().get(email).getPassword().equals(password)) {
                dataBase.setCurrentUser(dataBase.getUserMap().get(email));
                return true;
            } else throw new IncorrectPassword("Не верный пароль! Повторите ввод!\n");
        } else throw new IncorrectEmail("Юзера с таким email не существует! Повторите ввод!\n");
    }

    //    ***** Cервис для работы с "базой данных"

//    * В этом класе есть одна переменная dataBase которая и будет имитировать нашу БД
//    * Метод save сохраняет все изменения которые мы вносим в нащу БД в файл(в нашем случае изменения вносяться когда мы выходим из програмы через ВЫХОД, команда 0)
//    * Метод load выгружает нашу БД в переменную dataBase при каждом запуске программы, если файл MyDB.xml будет пустой при запуске выкинет ексепшн, топому я сделал следующий метод
//    * Метод resetDBToEmpty перезагружает нашу БД в файле до начального состояния(пустой, но со всеми нужными колекциями), этот метод нужно использовать отдельно когда вы тестируете методы, В коде я его нигде не использовал(вызывайте сами вручную)
//    ** Переменная и все методы кроме getDataBase - статические, я подумал что так будет логично, так как нам надо использовать постоянно одну и ту же переменную для загрузки и выгрузки в файл, так же более проще доступаться к методам save, load, resetDBToEmpty
//    ** В классе CommonDAO есть переменная dbService через нее мы и доступаемся к нашей БД(к переменной dataBase)

    public static void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/java/com/firstgroup/project/dataBase/MyDB.xml"))) {

            oos.writeObject(dataBase);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/java/com/firstgroup/project/dataBase/MyDB.xml"))) {

            dataBase = (DataBase) ois.readObject();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void resetDBToEmpty() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/java/com/firstgroup/project/dataBase/MyDB.xml"))) {

            oos.writeObject(new DataBase());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataBase getDataBase() {
        return dataBase;
    }
}
