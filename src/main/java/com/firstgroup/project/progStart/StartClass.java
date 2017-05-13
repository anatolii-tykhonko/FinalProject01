package com.firstgroup.project.progStart;

import com.firstgroup.project.APIs.Application;
import com.firstgroup.project.Controllers.HotelController;
import com.firstgroup.project.Controllers.RoomController;
import com.firstgroup.project.Controllers.UserController;
import com.firstgroup.project.DAOs.HotelDAO;
import com.firstgroup.project.DAOs.RoomDAO;
import com.firstgroup.project.DAOs.UserDAO;
import com.firstgroup.project.consoleHelper.ConsoleHelper;

/**
 * Created by Serega Shevchenko on 07.05.2017.
 * Victor Figurskiy on 07.05.2017.
 * Dmytro Sharyi on 07.05.2017.
 * Nazar Yanovets on 07.05.2017.
 * Anatoliy Tihonko on 07.05.2017.
 */

/**
 * -------------------------------------------------------------------------------------------------------------------------------
 * В данной программе реализовано разделение функционала программы на два меню доступа к функциям!
 * Вход в меню пользователя осуществляется при обычной регистрации.
 * Для входа через меню администратора с расширенным функционалом необходимо зарегестрировать пользователя со специальным паролем,
 * в данный момент пароль для такого входа "admin".
 * -------------------------------------------------------------------------------------------------------------------------------
 */

public class StartClass {
    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();
        HotelDAO hotelDAO = new HotelDAO();
        RoomDAO roomDAO = new RoomDAO();

        UserController userController = new UserController(userDAO);
        HotelController hotelController = new HotelController(hotelDAO);
        RoomController roomController = new RoomController(roomDAO);

        Application application = new Application(hotelController, roomController, userController);

        ConsoleHelper consoleHelper = new ConsoleHelper(application);
        consoleHelper.loginService();
        
    }
}
