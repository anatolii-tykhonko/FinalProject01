package com.firstgroup.project.fromNewCode;

import com.firstgroup.project.DAOs.CommonDAO;
import com.firstgroup.project.dataBase.DataBase;
import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by anaatolii on 04.05.17.
 */
public class PrintRoomByRangePrice {
    private static DataBase dataBase = new DataBase();
    CommonDAO commonDAO = new CommonDAO();

    //это для просмотра комнат
    //можно доделать что бы потом сразу бронировать закинуть в ArrayList
    // и при выводе показывать сначала номер по порядку а потом данные комнаты
    // потом считываем цифру и из листа передаем комнату на бронирование
    //+вводим дату бронирования
    public void printRoomByRangePrice(){
        BufferedReader buffRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("Введите минимальную цену для поиска");
            Double minPrice = Double.parseDouble(buffRead.readLine());
            System.out.println("Введите максимальную цену");
            Double maxPrice = Double.parseDouble(buffRead.readLine());
            System.out.println("Введите город для подбора номеров");
            String city = buffRead.readLine();
            List<Hotel> hotelList = commonDAO.getDataBase().getHotelList().stream().filter(hotel -> hotel.getCityName().equals(city)).collect(toList());
            List<List<Room>> findRoomByCity = hotelList.stream().map(hotel -> hotel.getRoomList()).collect(toList());
            //разматываю лист листов комнат до листа комнат))
            List<Room> rooms = new ArrayList<>();
            for(List<Room> roomList: findRoomByCity){
                for(Room room: roomList){
                    rooms.add(room);
                }
            }
            List<Room> findRoomByPrice = rooms.stream().filter(room -> room.getPrice() > minPrice && room.getPrice() < maxPrice).collect(toList());
            findRoomByPrice.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
