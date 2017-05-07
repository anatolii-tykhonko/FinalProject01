package com.firstgroup.project.dataBase;

import com.firstgroup.project.DAOs.CommonDAO;
import com.firstgroup.project.entity.Hotel;
import com.firstgroup.project.entity.Room;
import com.firstgroup.project.entity.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonikb on 06.05.2017.
 */
public class DBFillerTemporary {
    public static void main(String[] args) {
        CommonDAO commonDAO = new CommonDAO();

        List<Room> roomList1 = new ArrayList<>();
        roomList1.add(new Room(2,1500, LocalDate.of(2017,5,10)));
        roomList1.add(new Room(3,1700, LocalDate.of(2017,5,2)));
        roomList1.add(new Room(4,2000, LocalDate.of(2017,5,1)));
        roomList1.add(new Room(5,2500, LocalDate.of(2017,5,11)));
        roomList1.add(new Room(2,1200, LocalDate.of(2017,5,5)));
        roomList1.add(new Room(4,1900, LocalDate.of(2017,4,25)));
        roomList1.add(new Room(2,1800, LocalDate.of(2017,3,29)));
        roomList1.add(new Room(3,1100, LocalDate.of(2017,5,1)));
        roomList1.add(new Room(2,1100, LocalDate.of(2017,5,6)));
        roomList1.add(new Room(2,1400, LocalDate.of(2017,5,9)));
        Hotel hotelLviv1 = new Hotel("Шопен","Львов",roomList1);

        List<Room> roomList2 = new ArrayList<>();
        roomList2.add(new Room(2,1200, LocalDate.of(2017,5,1)));
        roomList2.add(new Room(3,1500, LocalDate.of(2017,5,3)));
        roomList2.add(new Room(5,1800, LocalDate.of(2017,5,6)));
        roomList2.add(new Room(2,800, LocalDate.of(2017,5,9)));
        roomList2.add(new Room(2,900, LocalDate.of(2017,5,4)));
        roomList2.add(new Room(4,1400, LocalDate.of(2017,4,29)));
        roomList2.add(new Room(2,1100, LocalDate.of(2017,4,22)));
        roomList2.add(new Room(3,1150, LocalDate.of(2017,5,6)));
        roomList2.add(new Room(2,700, LocalDate.of(2017,5,11)));
        roomList2.add(new Room(2,750, LocalDate.of(2017,5,26)));
        Hotel hotelLviv2 = new Hotel("Жорж","Львов",roomList2);

        List<Room> roomList3 = new ArrayList<>();
        roomList3.add(new Room(2,9000, LocalDate.of(2017,5,2)));
        roomList3.add(new Room(3,11000, LocalDate.of(2017,5,12)));
        roomList3.add(new Room(4,12000, LocalDate.of(2017,5,6)));
        roomList3.add(new Room(2,10000, LocalDate.of(2017,5,8)));
        roomList3.add(new Room(2,9800, LocalDate.of(2017,5,11)));
        roomList3.add(new Room(4,10500, LocalDate.of(2017,4,5)));
        roomList3.add(new Room(2,8700, LocalDate.of(2017,4,26)));
        roomList3.add(new Room(3,11400, LocalDate.of(2017,5,5)));
        roomList3.add(new Room(2,7800, LocalDate.of(2017,5,6)));
        roomList3.add(new Room(2,9600, LocalDate.of(2017,5,9)));
        Hotel hotelKiev1 = new Hotel("Hyatt","Киев",roomList3);

        List<Room> roomList4 = new ArrayList<>();
        roomList4.add(new Room(4,8000, LocalDate.of(2017,5,11)));
        roomList4.add(new Room(2,6000, LocalDate.of(2017,5,10)));
        roomList4.add(new Room(5,8500, LocalDate.of(2017,5,9)));
        roomList4.add(new Room(1,5000, LocalDate.of(2017,5,11)));
        roomList4.add(new Room(2,4600, LocalDate.of(2017,5,5)));
        roomList4.add(new Room(4,8900, LocalDate.of(2017,4,29)));
        roomList4.add(new Room(3,6900, LocalDate.of(2017,4,22)));
        roomList4.add(new Room(3,6400, LocalDate.of(2017,5,6)));
        roomList4.add(new Room(3,7600, LocalDate.of(2017,5,9)));
        roomList4.add(new Room(2,4000, LocalDate.of(2017,5,7)));
        Hotel hotelKiev2 = new Hotel("Premier Palace","Киев",roomList4);

        List<Room> roomList5 = new ArrayList<>();
        roomList5.add(new Room(4,3600, LocalDate.of(2017,5,6)));
        roomList5.add(new Room(2,2200, LocalDate.of(2017,5,3)));
        roomList5.add(new Room(5,4000, LocalDate.of(2017,5,6)));
        roomList5.add(new Room(2,3000, LocalDate.of(2017,5,12)));
        roomList5.add(new Room(2,2000, LocalDate.of(2017,5,11)));
        roomList5.add(new Room(4,2900, LocalDate.of(2017,4,28)));
        roomList5.add(new Room(4,4500, LocalDate.of(2017,4,22)));
        roomList5.add(new Room(3,3100, LocalDate.of(2017,4,29)));
        roomList5.add(new Room(2,2000, LocalDate.of(2017,5,8)));
        roomList5.add(new Room(2,2100, LocalDate.of(2017,5,7)));
        Hotel hotelOdessa1 = new Hotel("Frapolli","Одесса",roomList5);

        List<Room> roomList6 = new ArrayList<>();
        roomList6.add(new Room(4,2500, LocalDate.of(2017,5,12)));
        roomList6.add(new Room(2,2000, LocalDate.of(2017,5,6)));
        roomList6.add(new Room(5,3200, LocalDate.of(2017,5,8)));
        roomList6.add(new Room(1,1000, LocalDate.of(2017,5,7)));
        roomList6.add(new Room(2,1800, LocalDate.of(2017,5,9)));
        roomList6.add(new Room(4,2500, LocalDate.of(2017,4,10)));
        roomList6.add(new Room(3,2000, LocalDate.of(2017,4,22)));
        roomList6.add(new Room(3,1900, LocalDate.of(2017,5,9)));
        roomList6.add(new Room(3,2300, LocalDate.of(2017,5,5)));
        roomList6.add(new Room(2,1500, LocalDate.of(2017,5,4)));
        Hotel hotelOdessa2 = new Hotel("Одесський дворик","Одесса",roomList6);

        commonDAO.getDataBase().getHotelList().add(hotelLviv1);
        commonDAO.getDataBase().getHotelList().add(hotelLviv2);
        commonDAO.getDataBase().getHotelList().add(hotelKiev1);
        commonDAO.getDataBase().getHotelList().add(hotelKiev2);
        commonDAO.getDataBase().getHotelList().add(hotelOdessa1);
        commonDAO.getDataBase().getHotelList().add(hotelOdessa2);

        User user1 = new User("Виктор","Баринов","barinov@gmail.com","111");
        User user2 = new User("Сергей","Куценко","serg@gmail.com","222");
        User user3 = new User("Алексей","Потапенко","potap@gmail.com","333");
        User user4 = new User("Ирина","Билык","bilik@gmail.com","444");
        User user5 = new User("Алена","Виницкая","vinitsa@gmail.com","555");

        commonDAO.getDataBase().getUserMap().put(user1.getEmail(),user1);
        commonDAO.getDataBase().getUserMap().put(user2.getEmail(),user2);
        commonDAO.getDataBase().getUserMap().put(user3.getEmail(),user3);
        commonDAO.getDataBase().getUserMap().put(user4.getEmail(),user4);
        commonDAO.getDataBase().getUserMap().put(user5.getEmail(),user5);

        for (Hotel hotel : commonDAO.getDataBase().getHotelList()) {
            System.out.println(hotel.getHotelName() + " " + hotel.getCityName());
        }

        CommonDAO.save();
//        CommonDAO.resetDBToEmpty();
    }
}
