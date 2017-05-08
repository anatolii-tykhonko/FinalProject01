package com.firstgroup.project.Controllers;

import com.firstgroup.project.DAOs.UserDAO;
import com.firstgroup.project.Exceptions.*;
import com.firstgroup.project.entity.Room;
import com.firstgroup.project.entity.User;

import java.time.LocalDate;

/**
 * Created by MakeMeSm1Le- on 08.05.2017.
 */
public class UserController implements UserControllerInterface {

    private UserDAO userDAO = new UserDAO();

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
        User user = new User(newName, newSurName, oldEmail, userDAO.getDataBase().getUserMap().get(oldEmail).getPassword());
        return userDAO.update(user);
    }

    /**
     * @param email
     * @return
     * @throws CantDeleteCurrentUser
     */
    public User deleteUser(String email) throws CantDeleteCurrentUser {
        return userDAO.delete(email);
    }

    /**
     * @param name
     * @param surname
     * @param email
     * @param password
     * @param regTRUEaddFALSE
     * @return
     * @throws UserAlreadyExist
     */
    public User registerUser(String name, String surname, String email, String password, boolean regTRUEaddFALSE) throws UserAlreadyExist {
        return userDAO.save(new User(name, surname, email, password), regTRUEaddFALSE);
    }

    /**
     * @param email
     * @param password
     * @return
     * @throws IncorrectEmail
     * @throws IncorrectPassword
     */
    public boolean loginUser(String email, String password) throws IncorrectEmail, IncorrectPassword {
        return userDAO.loginUser(email, password);
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
     *                            10 сиволов и кокда вы пытаетесь ввести дату более раннюю чем та с которой комната свободна, срабатывает это исключение.
     */
    public Room roomReservationByName(int hotelIndex, int roomIndex, String reservDate) throws InvalidRoomStatus, InvalidHotelStatus, InvalidDateFormat {
        if (userDAO.getDataBase().getHotelList().get(hotelIndex).getRoomList().stream().allMatch(Room::isStatus))
            throw new InvalidHotelStatus("Все комнаты в этом отеле заняты!");
        if (reservDate.length() > 10) {
            throw new InvalidDateFormat("Не верный формат даты!");
        }
        Room room = userDAO.getDataBase().getHotelList().get(hotelIndex).getRoomList().get(roomIndex);
        LocalDate reserv = LocalDate.of(Integer.valueOf(reservDate.substring(0, 4)), Integer.valueOf(reservDate.substring(5, 7)), Integer.valueOf(reservDate.substring(8, 10)));
        if (room.getAvailableFrom().getYear() > reserv.getYear() || room.getAvailableFrom().getYear() == reserv.getYear() && room.getAvailableFrom().getMonthValue() > reserv.getMonthValue() || room.getAvailableFrom().getYear() == reserv.getYear() && room.getAvailableFrom().getMonthValue() == reserv.getMonthValue() && room.getAvailableFrom().getDayOfMonth() >= reserv.getDayOfMonth()) {
            throw new InvalidDateFormat("Не коректная дата, Вы ввели дату на которую эта комната занята!");
        }
        if (room.isStatus()) throw new InvalidRoomStatus("Эта комната сейчас занята");
        else {
            room.setStatus(true);
            room.setReservBefore(reserv);
            userDAO.getDataBase().getCurrentUser().getRoomList().add(room);
        }
        return room;
    }

    /**
     * Метод cancelReservationByName предназначен для отмены бронирования комнаты для текущего юзера,
     * он выполняет две функции - присваивает значение статуса комнаты(false) командой setStatus(комната свободна)
     * и удаляет комнату из списка забронированых комнат текущего пользователя командой remove
     * Так как в консоли мы получем пронумерованый список комнат, то мотод принимает порядковый номер комнаты в списке и
     * по нему удаляет выбраную комнату!
     *
     * @param roomIndex - индекс комнаты(позиция комнаты в списке).
     * @return boolean(true) - если удаление было удечным.
     * @throws IndexOutOfBoundsException - возможное исключение которое сработает если мы введем не существующий номер коммнаты.
     * @throws NumberFormatException     - возможное исключение которое сработает если было введено не число.
     */

    public boolean cancelReservationByName(int roomIndex) {
        userDAO.getDataBase().getCurrentUser().getRoomList().get(roomIndex).setStatus(false);
        userDAO.getDataBase().getCurrentUser().getRoomList().remove(roomIndex);
        return true;
    }
}