package com.firstgroup.project.controllers;

import com.firstgroup.project.daos.UserDAOInterface;
import com.firstgroup.project.exceptions.*;
import com.firstgroup.project.entity.Room;
import com.firstgroup.project.entity.User;

import java.time.LocalDate;
import java.util.Map;

/**
 * Created by MakeMeSm1Le- on 08.05.2017.
 */
public class UserController implements UserControllerInterface {

    private UserDAOInterface userDAO;

    public UserController(UserDAOInterface userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Данный метод принимает набор параметров, которые вводит пользователь в классе ConsoleHelper.
     * Все исключительные ситуации обрабатываются на уровень выше.
     * Данный метод возвращает экземпляр типа User,данный экземпляр служит "контейнером" для хранения данных.
     *
     * @param newName    - новое имя пользователя
     * @param newSurName - новая фамилия пользователя
     * @param oldEmail   - старый емейл
     * @return user
     */
    public User editUserInfo(String newName, String newSurName, String oldEmail) {
        User user = new User(newName, newSurName, oldEmail, userDAO.getDbServiceSingleton().getDataBase().getUserMap().get(oldEmail).getPassword());
        return userDAO.update(user);
    }

    /**
     * Метод удаление пользователя из базы данных по аргументу email(уникальный для каждого пользователя).
     *
     * @param email - аргумент - имейл пользователя.
     * @return - если операция прошла удачно возвращаеться удалённый пользователь.
     * @throws CantDeleteCurrentUser - исключение сработает если попытаться удалить текущего пользователя.
     */
    public User deleteUser(String email) throws CantDeleteCurrentUser {
        return userDAO.delete(email);
    }

    /**
     * Метод выполняет две функции - регитрация пользователя и просвоение его как текущего пользователя и добавление
     * пользователя в базуданых без присвоение его как иекущего пользователя, этими функциями можно управлять с помощью
     * аргумента regTRUEaddFALSE типа boolean.
     *
     * @param name            - имя пользователя.
     * @param surname         - фамилия пользователя.
     * @param email           - имейл пользователя - уникальный для каждого пользователя.
     * @param password        - пароль каждого юзера который соответствует конкретному email.
     * @param regTRUEaddFALSE - значение которое задает режим работы метода(true - регистрация, false - добавление пользователя)
     * @return если регистрация или добавление успешное то позвращаеться пользователь которого мы зарегистрировали или добавили.
     * @throws UserAlreadyExist - исключение срабатывает если пользователь уже существует в базе данных, есть такой же email.
     */
    public User registerUser(String name, String surname, String email, String password, boolean regTRUEaddFALSE) throws UserAlreadyExist {
        return userDAO.save(new User(name, surname, email, password), regTRUEaddFALSE);
    }

    /**
     * Метод выполняет функциюю входа в систему, проверяет зарегистрирован ли пользователь в базе даных и проверяет его
     * пароль при входе, если вход выполнен, метод возвращает значение true.
     *
     * @param email    - имейл пользователя - уникальный для каждого пользователя.
     * @param password - пароль который соответствует имейлу юзера.
     * @return - true если операция входа была удачной.
     * @throws IncorrectEmail    - исключение срабатывает если такого имейла нет в базе.
     * @throws IncorrectPassword - не верный пароль для конкретного юзера.
     */

    public boolean loginUser(String email, String password) throws IncorrectEmail, IncorrectPassword {
        if (userDAO.getDbServiceSingleton().getDataBase().getUserMap().keySet().contains(email)) {
            if (userDAO.getDbServiceSingleton().getDataBase().getUserMap().get(email).getPassword().equals(password)) {
                userDAO.getDbServiceSingleton().getDataBase().setCurrentUser(userDAO.getDbServiceSingleton().getDataBase().getUserMap().get(email));
                return true;
            } else throw new IncorrectPassword("Не верный пароль! Повторите ввод!\n");
        } else throw new IncorrectEmail("Юзера с таким email не существует! Повторите ввод!\n");
    }

    /**
     * Метод предназначен для бронирования комнаты для текущего юзера, при удачном бронировании статус комнаты меняеться
     * на RESERV(true) и добавляеться в список забронированых комнат текущего пользователя.
     *
     * @param hotelIndex - индекс отеля(позиция отеля в списке).
     * @param roomIndex  - индекс комнаты(позиция комнаты в списке).
     * @param reservDate - дата по которую вы хотите забронировать комнату в формате уууу.mm.dd.
     * @return Room(room) - если бронирование прошло успешно возвращаеться комната которую Вы забронировали.
     * @throws InvalidRoomStatus  - срабатывает если комната которубю мы хотим забронировать уже занята.
     * @throws InvalidHotelStatus - срабатывает если в даном отеле все комнаты заняты.
     * @throws InvalidDateFormat  - дата вводиться в формате уууу.mm.dd(10символов включая точки), если длинна превышает
     * @throws InvalidDateFormat  - 10 сиволов и когда вы пытаетесь ввести дату более раннюю чем та с которой комната свободна, срабатывает это исключение.
     */

    public Room roomReservationByName(int hotelIndex, int roomIndex, String reservDate) throws InvalidRoomStatus, InvalidHotelStatus, InvalidDateFormat {
        if (userDAO.getDbServiceSingleton().getDataBase().getHotelList().get(hotelIndex).getRoomList().stream().allMatch(Room::isStatus))
            throw new InvalidHotelStatus("Все комнаты в этом отеле заняты!");
        if (reservDate.length() > 10) {
            throw new InvalidDateFormat("Не верный формат даты!");
        }
        Room room = userDAO.getDbServiceSingleton().getDataBase().getHotelList().get(hotelIndex).getRoomList().get(roomIndex);
        LocalDate reserv = LocalDate.of(Integer.valueOf(reservDate.substring(0, 4)), Integer.valueOf(reservDate.substring(5, 7)), Integer.valueOf(reservDate.substring(8, 10)));
        if (room.getAvailableFrom().getYear() > reserv.getYear() ||
                room.getAvailableFrom().getYear() == reserv.getYear() && room.getAvailableFrom().getMonthValue() > reserv.getMonthValue() ||
                room.getAvailableFrom().getYear() == reserv.getYear() && room.getAvailableFrom().getMonthValue() == reserv.getMonthValue() && room.getAvailableFrom().getDayOfMonth() >= reserv.getDayOfMonth()) {
            throw new InvalidDateFormat("Не коректная дата, Вы ввели дату на которую эта комната занята!");
        }
        if (room.isStatus()) throw new InvalidRoomStatus("Эта комната сейчас занята");
        else {
            return userDAO.reservRoom(room, reserv, true);
        }
    }

    /**
     * Метод cancelReservationByName предназначен для отмены бронирования комнаты для текущего юзера,
     * Так как в консоли мы получем пронумерованый список комнат, то мотод принимает порядковый номер комнаты в списке и
     * по нему удаляет выбраную комнату!
     *
     * @param roomIndex - индекс комнаты(позиция комнаты в списке).
     * @return boolean(true) - если удаление было удечным.
     * @throws IndexOutOfBoundsException - возможное исключение которое сработает если мы введем не существующий номер коммнаты.
     * @throws NumberFormatException     - возможное исключение которое сработает если было введено не число.
     */

    public boolean cancelReservationByName(int roomIndex) {
        return userDAO.cancelReserv(roomIndex);
    }

    public Map<String, User> getUsers() {
        return userDAO.getDbServiceSingleton().getDataBase().getUserMap();
    }

    public User getCurrentUser() {
        return userDAO.getDbServiceSingleton().getDataBase().getCurrentUser();
    }
}