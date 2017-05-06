package com.firstgroup.project.consoleHelper;

import com.firstgroup.project.APIs.Controller;
import com.firstgroup.project.DAOs.CommonDAO;
import com.firstgroup.project.Exceptions.*;
import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.hotels.Room;
import com.firstgroup.project.hotels.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


public class ConsoleHelper {
    Controller controller = new Controller();
    BufferedReader buffRead = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        ConsoleHelper consoleHelper = new ConsoleHelper();
//        consoleHelper.mainMenu();
        consoleHelper.loginService();
    }

    public void mainMenu() {

        System.out.println("*-------------------------------------------*\n" +
                "*------> Система бронирования отелей <------*\n" +
                "*-------------------------------------------*");

        System.out.println("1. * Добавить отель");
        System.out.println("2. * Редактировать данные отеля");
        System.out.println("3. * Добавить комнату в отель");
        System.out.println("4. * Редактировать данные комнаты");
        System.out.println("5. * Удалить комнату из отеля");
        System.out.println("6. * Удалить отель");
        System.out.println("7. * Зарегистрировать пользователя");
        System.out.println("8. * Редактировать данные пользователя");
        System.out.println("9. * Удалить пользователя");
        System.out.println("10. * Поиск отеля по имени");
        System.out.println("11. * Поиск отеля по городу");
        System.out.println("12. * Поиск комнаты по отелю");
        System.out.println("13. * Поиск комнат по цене");
        System.out.println("14. * Бронирование комнаты на имя пользователя");
        System.out.println("15. * Отмена бронирования комнаты" + "\n");
        System.out.println("0. * ВЫХОД");
        System.out.println("*-------------------------------------------*");
        chooseTheOperation();
    }

    private void chooseTheOperation() {
        System.out.println("\nВведите номер операции которую вы хотите произвести!!!");
        try {
            switch (Integer.parseInt(buffRead.readLine())) {
                case 1:
                    System.out.println("\n*----------------------------------------------*\n" +
                            "*------> Добавление отеля в базу данных <------*\n" +
                            "*----------------------------------------------*\n");
                    addHotel();
                    mainMenu();
                    break;
                case 2:
                    System.out.println("\n*-----------------------------------------------------*\n" +
                                         "*-----------> Редактирование данных отеля <-----------*\n" +
                                         "*-----------------------------------------------------*\n");
                    editHotelInfo();
                    mainMenu();
                    break;
                case 3:
                    System.out.println("\n*------------------------------------------------------*\n" +
                                         "*------------> Добавление комнаты в отель <------------*\n" +
                                         "*------------------------------------------------------*\n");
                    addRoom();
                    mainMenu();
                    break;
                case 4:
                    System.out.println("\n*-------------------------------------------------------*\n" +
                                         "*-----------> Редактирование данных комнаты <-----------*\n" +
                                         "*-------------------------------------------------------*\n");
                    editRoomInfo();
                    mainMenu();
                    break;
                case 5:
                    System.out.println("*-----------------------------------------------------*\n" +
                                       "*------------> Удалиение комнат из отеля <------------*\n" +
                                       "*-----------------------------------------------------*");
                    deleteRoom();
                    mainMenu();
                    break;
                case 6:
                    System.out.println("*-----------------------------------------------------*\n" +
                                       "*-----------------> Удаление отелей <-----------------*\n" +
                                       "*-----------------------------------------------------*\n");
                    deleteHotel();
                    mainMenu();
                    break;
                case 7:
                    System.out.println("\n***** Регистрация пользователей *****\n");
                    addUser();
                    mainMenu();
                    break;
                case 8:
                    System.out.println("*--------------------------------------------------------------*\n" +
                                       "*------------> Редактирование данных пользователя <------------*\n" +
                                       "*--------------------------------------------------------------*\n");
                    editUserInfo();
                    mainMenu();
                    break;
                case 9:
                    System.out.println("\n***** Удаление пользователей *****\n");
                    deleteUser();
                    mainMenu();
                    break;
                case 10:
                    System.out.println("\n***** Поиск отеля по имени *****\n");
                    findByNameHotel();
                    mainMenu();
                    break;
                case 11:
                    System.out.println("\n***** Поиск отеля по городу *****\n");
                    findByCity();
                    mainMenu();
                    break;
                case 12:
                    System.out.println("\n***** Поиск комнаты по отелю *****\n");
                    findRoomsByHotel();
                    mainMenu();
                    break;
                case 13:
                    System.out.println("\n***** Поиск комнаты по цене *****\n");
                    findRoomsByRangePrice();
                    mainMenu();
                    break;
                case 14:
                    System.out.println("\n***** Бронирование комнаты на имя пользователя *****\n");
                    reservationRoom();
                    mainMenu();
                    break;
                case 15:
                    System.out.println("\n***** Отмена бронирования комнаты *****\n");
                    cancelReservation();
                    mainMenu();
                    break;
                case 0:
                    System.out.println("\n***** Программа завершена, все изменения сохранены *****\n");
                    CommonDAO.save();
                    buffRead.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Не верный номер операции! Повторите попытку!" + " \nДля выхода нажмите \"0\"");
                    chooseTheOperation();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchElementException | NumberFormatException n) {
            System.out.println("Команда введена неверно! Повторите выбор!" + " \nДля выхода нажмите \"0\"");
            mainMenu();
        }
    }

    private void addHotel() {
        try {
            System.out.println("Укажите название отеля. Для выхода введите \"0\"!");
            String hotelName = buffRead.readLine();
            if ("0".equals(hotelName)) return;
            controller.validLine(hotelName);
            System.out.println("Укажите название города:");
            String cityName = buffRead.readLine();
            controller.validLine(cityName);
            System.out.println("Для создания отеля необходимо создать хотя бы одну комнату!");
            System.out.println("Укажите количество спальных мест в номере:");
            int roomPersons = Integer.parseInt(buffRead.readLine());
            if (roomPersons == 0 || roomPersons < 0) {
                System.out.println("Вы ввели 0 или отритцательное число!\nВведите данные повторно!\n");
                addHotel();
            }
            System.out.println("Укажите цену номера в грн/сутки:");
            double roomPrice = Double.parseDouble(buffRead.readLine());
            if (roomPrice == 0 || roomPrice < 0) {
                System.out.println("Вы ввели 0 или отритцательное число!\nВведите данные повторно!\n");
                addHotel();
            }
            System.out.println("Укажите дату когда номер будет доступен в формате year.mm.dd");
            String dateAvailableFrom = buffRead.readLine();
            Hotel hotel = controller.addHotel(hotelName, cityName, roomPersons, roomPrice, dateAvailableFrom);
            System.out.println(hotel.getHotelName() + " успешно сохранен!");
        } catch (HotelAlreadyExist | ValidStringNameException r) {
            System.out.println(r.getMessage());
            addHotel();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Данные введены неверно!\nВведите данные повторно!\n");
            addHotel();
        } catch (IndexOutOfBoundsException | DateTimeException e) {
            System.out.println("Дата введена неверно!\nВведите данные повторно!\n");
            addHotel();
        }
    }

    private void addRoom() {
        System.out.println("Выберите отель в котором вы хотите добавить комнату. Для выхода введите \"0\"!");
        int count = 1;
        List<String> hotelNames = controller.getCommonDAO().getDataBase().getHotelList().stream().map(Hotel::getHotelName).collect(Collectors.toList());
        for (String hotel : hotelNames) {
            System.out.println(count++ + ". * " + hotel);
        }
        try {
            int hotelIndex = Integer.parseInt(buffRead.readLine()) - 1;
            if (-1 == hotelIndex) return;
            Hotel hotel = controller.getCommonDAO().getDataBase().getHotelList().get(hotelIndex);
            System.out.println("**** Добавление комнат в отель " + hotel.getHotelName() + ", город " + hotel.getCityName() + " ****");
            System.out.println("Укажите количество спальных мест в номере:");
            int roomPersons = Integer.parseInt(buffRead.readLine());
            if (roomPersons == 0 || roomPersons < 0) {
                System.out.println("Вы ввели 0 или отритцательное число!\nВведите данные повторно!\n");
                addRoom();
            }
            System.out.println("Укажите цену номера в грн:");
            double roomPrice = Integer.parseInt(buffRead.readLine());
            if (roomPrice == 0 || roomPrice < 0) {
                System.out.println("Вы ввели 0 или отритцательное число!\nВведите данные повторно!\n");
                addRoom();
            }
            System.out.println("Укажите дату когда номер будет доступен в формате year.mm.dd");
            String dateAvailableFrom = buffRead.readLine();
            controller.addRoom(hotelIndex, roomPersons, roomPrice, dateAvailableFrom);
            System.out.println("Комната сохранена в отель " + hotel.getHotelName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ValidStringNameException r) {
            System.out.println(r.getMessage());
            addRoom();
        } catch (NumberFormatException e) {
            System.out.println("Данные введены неверно!\nВведите данные повторно!\n");
            addRoom();
        } catch (DateTimeException e) {
            System.out.println("Дата введена неверно!\nВведите данные повторно!\n");
            addRoom();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Некоректно введенные данные, попробуйте снова!\n");
            addRoom();
        }
    }

    private void deleteHotel() {
        System.out.println("Список отелей: ");
        int count = 1;
        List<String> hotelNames = controller.getCommonDAO().getDataBase().getHotelList().stream().map(Hotel::getHotelName).collect(Collectors.toList());
        if (hotelNames.isEmpty()) {
            System.out.println("Лист отелей пустой, чтобы делать какие-либо действия сначала добавьте отель");
            mainMenu();
        }
        for (String hotel : hotelNames) {
            System.out.println(count++ + ". * " + hotel);
        }
        System.out.println("Введите номер отеля которий вы хотите удалить: ");
        try {
            int hotelIndex = Integer.parseInt(buffRead.readLine()) - 1;
            controller.deleteHotel(hotelIndex);
            System.out.println("Список отелей после удаления: ");
            List<String> hotelNamesWhenWeDeleteHotel = controller.getCommonDAO().getDataBase().getHotelList().stream().map(Hotel::getHotelName).collect(Collectors.toList());
            int count1 = 1;
            for (String hotel : hotelNamesWhenWeDeleteHotel) {
                System.out.println(count1++ + ". * " + hotel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Номер отеля не существует, попробуйте снова");
            deleteHotel();
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат данных, попробуйте снова: ");
            deleteHotel();
        }
    }

    private void deleteRoom() {
        System.out.println("Выберете номер отеля из которого вы хотите удалить комануту: ");
        int count = 1;
        List<String> hotelNames = controller.getCommonDAO().getDataBase().getHotelList().stream().map(Hotel::getHotelName).collect(Collectors.toList());
        if (hotelNames.isEmpty()) {
            System.out.println("Лист отелей пустой, чтобы делать какие-либо действия сначала добавьте отель");
            mainMenu();
        }
        for (String hotelName : hotelNames) {
            System.out.println(count++ + ". * " + hotelName);
        }
        try {
            System.out.println("Введите номер отеля из которого ви хотите удалить:");
            int hotelIndex = Integer.parseInt(buffRead.readLine()) - 1;
            Hotel hotel = controller.getCommonDAO().getDataBase().getHotelList().get(hotelIndex);
            System.out.println("**** Удаление комнат в отеле " + hotel.getHotelName() + " ****");
            count = 1;
            if (hotel.getRoomList().isEmpty()) {
                System.out.println("В " + hotel.getHotelName()
                        + " нет комнат,чтобы делать какие-либо действия сначала добавьте комнату");
                mainMenu();
            }
            for (Room room : hotel.getRoomList()) {
                System.out.println(count++ + ". * " + room);
            }
            System.out.println("Введите номер комнати которою ви хотите удалить: ");
            int i = Integer.parseInt(buffRead.readLine());
            controller.deleteRoom(hotelIndex, i);
            System.out.println("**** Новий список комант в " + hotel.getHotelName() + " ****");
            List<Room> roomList1 = new ArrayList<>(hotel.getRoomList());
            count = 1;
            for (Room room1 : roomList1) {
                System.out.println(count++ + ". * " + room1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Неправильная операция, выберите существующий номер из списка: ");
            deleteRoom();
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат данных, введите даные повторно: ");
            deleteRoom();
        }
    }

    public void editHotelInfo() {
        int count = 1;
        if (controller.getCommonDAO().getDataBase().getHotelList().isEmpty()) {
            System.out.println("В базе нет отелей, сначала необходимо создать отель!");
            mainMenu();
        }
        System.out.println("Выберите из списка номер отеля который необходимо отредактировать. Для выхода введите \"0\"!");
        List<Hotel> hotelList = controller.getCommonDAO().getDataBase().getHotelList();
        for (Hotel hotel : hotelList) {
            System.out.println(count++ + ". * " + hotel.getHotelName() + ", город " + hotel.getCityName());
        }
        try {
            int hotelIndex = Integer.parseInt(buffRead.readLine()) - 1;
            if (-1 == hotelIndex) return;
            System.out.println("Название отеля " + hotelList.get(hotelIndex).getHotelName() + " изменяем на: ");
            String newHotelName = buffRead.readLine();
            controller.validLine(newHotelName);
            System.out.println("Название города " + hotelList.get(hotelIndex).getCityName() + " изменяем на: ");
            String newCityName = buffRead.readLine();
            controller.validLine(newCityName);
            Hotel editedHotel = controller.editHotelDetails(hotelIndex, newHotelName, newCityName);
            System.out.println("Изменениея успешно сохранены: имя отеля " + editedHotel.getHotelName() + ", город: " + editedHotel.getCityName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ValidStringNameException e) {
            System.out.println(e.getMessage());
            editHotelInfo();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Некоректно введенные данные, попробуйте снова!\n");
            editHotelInfo();
        }
    }

    public void editUserInfo() {
        System.out.println("Список пользователей в системе: ");
        Set<Map.Entry<String, User>> entrySet = controller.getCommonDAO().getDataBase().getUserMap().entrySet();
        int count = 1;
        List<String> emailList = new ArrayList<>();
        for (Map.Entry<String, User> userEntry : entrySet) {
            System.out.println(count++ + ". * " + userEntry.getValue());
            emailList.add(userEntry.getValue().getEmail());
        }
        try {
            System.out.println("Введите номер пользователя которого вы хотите редактировать. Для выхода введите \"0\"!");
            int emailIndex = Integer.parseInt(buffRead.readLine()) - 1;
            if (-1 == emailIndex) return;
            String oldEmail = emailList.get(emailIndex);
            System.out.println("Введите новое имя пользователя: ");
            String newName = buffRead.readLine();
            System.out.println("Введите новою фамилию пользователя: ");
            String newSurName = buffRead.readLine();

            System.out.println("Параметры пользователя после редактирования: \n"
                    + controller.editUserInfo(newName, newSurName, oldEmail));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат данных, введите даные повторно: ");
            editUserInfo();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Юзер с таким номером не существует, попробуйте снова");
            editUserInfo();
        }
    }

    public void editRoomInfo() {
        System.out.println("***** Список отелей в системе *****");
        int count = 1;
        List<String> hotelNames = controller.getCommonDAO().getDataBase().getHotelList().stream().map(Hotel::getHotelName).collect(Collectors.toList());
        if (hotelNames.isEmpty()) {
            System.out.println("\nВ системе не создано ни одного отеля, сначала добавьте отель в систему!\n");
            mainMenu();
        }
        for (String hotel : hotelNames) {
            System.out.println(count++ + ". * " + hotel);
        }
        try {
            System.out.println("Введите номер отеля в котором необходимо редактировать комнаты. Для выхода введите \"0\"!");
            int hotelIndex = Integer.parseInt(buffRead.readLine()) - 1;
            if (-1 == hotelIndex) return;
            Hotel hotel = controller.getCommonDAO().getDataBase().getHotelList().get(hotelIndex);
            System.out.println("***** Список комнат в отеле " + hotel.getHotelName() + ", город " + hotel.getCityName() + " *****");
            count = 1;
            for (Room room : hotel.getRoomList()) {
                System.out.println(count++ + ". * " + room);
            }
            System.out.println("Введите номер комнаты которою Вы хотите редактировать: ");
            int roomIndex = Integer.parseInt(buffRead.readLine()) - 1;
            Room room = hotel.getRoomList().get(roomIndex);
            System.out.println("*------> Редактирование параметров комнаты <------*");
            System.out.println("Количество спальных мест в номере: " + room.getPersons() + " изменяем на: ");
            int roomPersons = Integer.parseInt(buffRead.readLine());
            if (roomPersons <= 0) {
                System.out.println("Вы ввели 0 или отритцательное число!\nВведите данные повторно!\n");
                editRoomInfo();
            }
            System.out.println("Цена номера в грн/сутки: " + room.getPrice() + " изменяем на: ");
            double roomPrice = Double.parseDouble(buffRead.readLine());
            if (roomPrice <= 0) {
                System.out.println("Вы ввели 0 или отритцательное число!\nВведите данные повторно!\n");
                editRoomInfo();
            }
            System.out.println("Укажите дату когда номер будет доступен в формате year.mm.dd");
            String dateAvailableFrom = buffRead.readLine();
            System.out.println("Комната после введеных изменений: \n" +
                    controller.editRoomDetails(hotelIndex, roomIndex, roomPersons, roomPrice, dateAvailableFrom));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Неправильная операция, выберите существующий номер из списка: ");
            editRoomInfo();
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат данных, введите даные повторно: ");
            editRoomInfo();
        }
    }

    public void loginService() {
        System.out.println("Чтобы войти в систему создайте профиль или выполните вход с существуещего!");
        System.out.println("\n1. * Зарегистрироваться!");
        System.out.println("2. * Войти!");
        try {
            int line = Integer.parseInt(buffRead.readLine());
            if (1 != line && 2 != line) {
                System.out.println("Не верная команда, повторите попытку!\n");
                loginService();
            }
            if (1 == line) {
                regUser();
                mainMenu();
            } else {
                enterToSystem();
            }
        } catch (NumberFormatException num) {
            System.out.println("Не верный формат даных, попробуйте снова!");
            loginService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enterToSystem() {
        System.out.println("***Вход в систему***");
        String email;
        String password;
        try {
            System.out.println("Введите email: ");
            email = buffRead.readLine();
            System.out.println("Введите password: ");
            password = buffRead.readLine();
            if (controller.loginUser(email, password)) {
                System.out.println("Вход выполнен " + controller.getCommonDAO().getDataBase().getCurrentUser().getName() + "\n");
                mainMenu();
            }
        } catch (IncorrectEmail | IncorrectPassword ex) {
            System.out.println(ex.getMessage());
            enterToSystem();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void regUser() {
        try {
            System.out.println("Введите Ваше имя:");
            System.out.println("Для выхода введите \"0\"");
            String name = buffRead.readLine();
            if ("0".equals(name)) loginService();
            System.out.println("Введите Вашу фамилию");
            String secondName = buffRead.readLine();
            System.out.println("Введите Ваш email");
            String email = buffRead.readLine();
            System.out.println("Введите PASSWORD");
            String password = buffRead.readLine();

            User user = controller.registerUser(name, secondName, email, password);
            System.out.println("Пользователь " + user.getEmail() + " успешно зарегистрирован!\n");
        } catch (UserAlreadyExist e) {
            System.out.println(e.getMessage());
            regUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addUser() {
        try {
            System.out.println("Введите Ваше имя:");
            System.out.println("Для возврата в меню введите \"0\"");
            String name = buffRead.readLine();
            if ("0".equals(name)) return;
            System.out.println("Введите Вашу фамилию");
            String secondName = buffRead.readLine();
            System.out.println("Введите Ваш email");
            String email = buffRead.readLine();
            System.out.println("Введите PASSWORD");
            String password = buffRead.readLine();

            User user = controller.addUser(name, secondName, email, password);
            System.out.println("Пользователь " + user.getEmail() + " успешно зарегистрирован!\n");
        } catch (UserAlreadyExist e) {
            System.out.println(e.getMessage());
            addUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteUser() {
        System.out.println("Cписок пользователей----------------------------------------\n");
        Set<Map.Entry<String, User>> entries = controller.getCommonDAO().getDataBase().getUserMap().entrySet();
        int count = 1;
        List<String> emailList = new ArrayList<>();
        for (Map.Entry<String, User> userEntry : entries) {
            System.out.println(count++ + "." + " " + userEntry.getValue());
            emailList.add(userEntry.getValue().getEmail());
        }
        try {
            System.out.println("\nУкажите номер пользователя которого хотите удалить");
            System.out.println("Для возврата в меню введите \"0\"");
            int emailIndex = Integer.parseInt(buffRead.readLine()) - 1;
            if (emailIndex == -1) return;
            System.out.println("Пользователь " + controller.deleteUser(emailList.get(emailIndex)).getEmail() + " удалён!\n");
            count = 1;
            for (Map.Entry<String, User> entry : entries) {
                System.out.println(count++ + ". " + entry.getValue());
            }
            System.out.println();
        } catch (CantDeleteCurrentUser ex) {
            System.out.println(ex.getMessage());
            deleteUser();
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            System.out.println("Не верная операция, повторите попытку!");
            deleteUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findByNameHotel() {
        System.out.println("В системе имеются следующие отели: ");
        int count = 1;
        List<String> hotelNames = controller.getCommonDAO().getDataBase().getHotelList().stream().map(Hotel::getHotelName).collect(Collectors.toList());
        for (String hotel : hotelNames) {
            System.out.println(count++ + ". " + hotel);
        }
        System.out.println("Введите название отеля: ");
        try {
            String name = buffRead.readLine();
            List<Hotel> hotelByName = controller.findHotelByName(name);
            for (Hotel hotel : hotelByName) {
                System.out.println("Название отеля:" + hotel.getHotelName() + ";" +
                        "Город:" + hotel.getCityName() + "\n" + "Доступные комнаты: ");

                hotel.getRoomList().forEach(System.out::println);

            }
            System.out.println("Для продолжения поиска отеля по названию нажмите 1, в противном случае Вы перейдете в главное меню");
            String answer1 = buffRead.readLine();
            switch (answer1) {
                case "1":
                    findByNameHotel();
                    break;
                default:
                    mainMenu();

            }
        } catch (IncorrectDataInput | ValidStringNameException e) {
            System.out.println(e.getMessage());
            findByNameHotel();
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void findByCity() {
        System.out.println("В системе имеются отели в следующих городах: ");
        int count = 1;
        List<String> citiesNames = controller.getCommonDAO().getDataBase().getHotelList().stream().map(Hotel::getCityName).distinct().collect(Collectors.toList());
        for (String city : citiesNames) {
            System.out.println(count++ + ". " + city);
        }
        System.out.println("Введите название города: ");
        try {
            String city = buffRead.readLine();
            List<Hotel> hotelByCity = controller.findHotelByCity(city);
            System.out.println("По Вашему запросу найдены следующие отели: ");
            for (Hotel hotel : hotelByCity) {
                System.out.println("Название отеля:" + hotel.getHotelName() + ";" +
                        "Город:" + hotel.getCityName() + "\n" + "Доступные комнаты: ");

                hotel.getRoomList().forEach(System.out::println);

            }
            System.out.println("Для продолжения поиска отеля по городу нажмите 1, в противном случае Вы перейдете в главное меню");
            String answer1 = buffRead.readLine();
            switch (answer1) {
                case "1":
                    findByCity();
                    break;
                default:
                    mainMenu();

            }
        } catch (IncorrectDataInput | ValidStringNameException e) {
            System.out.println(e.getMessage());
            findByCity();
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findRoomsByHotel() {
        System.out.println("В системе имеются следующие отели: ");
        int count = 1;
        List<String> hotelNames = controller.getCommonDAO().getDataBase().getHotelList().stream().map(Hotel::getHotelName).collect(Collectors.toList());
        for (String hotel : hotelNames) {
            System.out.println(count++ + ". " + hotel);
        }
        System.out.println("Введите название отеля: ");
        try {
            String name = buffRead.readLine();
            List<Hotel> hotelByName = controller.findRoomsByHotel(name);
            for (Hotel hotel : hotelByName) {

                hotel.getRoomList().forEach(System.out::println);

            }
            System.out.println("Для продолжения поиска комнат по отелям нажмите 1, в противном случае Вы перейдете в главное меню");
            String answer1 = buffRead.readLine();
            switch (answer1) {
                case "1":
                    findRoomsByHotel();
                    break;
                default:
                    mainMenu();

            }
        } catch (IncorrectDataInput | ValidStringNameException e) {
            System.out.println(e.getMessage());
            findByNameHotel();
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void findRoomsByRangePrice(){
        try{
            System.out.println("Введите минимальную цену для поиска: ");
            Double minPrice = Double.parseDouble(buffRead.readLine());
            System.out.println("Введите максимальную цену: ");
            Double maxPrice = Double.parseDouble(buffRead.readLine());
            System.out.println("Введите город для подбора номеров: ");
            String city = buffRead.readLine();
            List<Hotel> hotelByCity = null;
            try {
                hotelByCity = controller.findHotelByCity(city);
            } catch (IncorrectDataInput | ValidStringNameException e) {
                System.out.println(e.getMessage());
                findRoomsByRangePrice();
            }
            List<List<Room>> findRoomByCity = hotelByCity.stream().map(hotel -> hotel.getRoomList()).collect(toList());
            //разматываю лист листов комнат до листа комнат))
            List<Room> rooms = new ArrayList<>();
            for(List<Room> roomList: findRoomByCity){
                for(Room room: roomList){
                    rooms.add(room);
                }
            }
            List<Room> findRoomByPrice = rooms.stream().filter(room -> room.getPrice() > minPrice && room.getPrice() < maxPrice).collect(toList());
            if(!findRoomByPrice.isEmpty()) {
                System.out.println("По вашим критериям поиска найдено следующие комнаты: ");
                findRoomByPrice.forEach(System.out::println);
            } else System.out.println("Ни одной комнаты не найдено.");
        } catch (IOException e) {
            System.out.println("Неверно задали критерии поиска повторите ввод: ");
            findRoomsByRangePrice();
        }

    }

    private void reservationRoom() {
        System.out.println("Виберете отель в которогм вы хотите забронировать комануту: ");
        int count = 1;
        List<String> hotelNames = controller.getCommonDAO().getDataBase().getHotelList().stream().map(Hotel::getHotelName).collect(Collectors.toList());
        for (String hotel : hotelNames) {
            System.out.println(count++ + ". * " + hotel);
        }
        try {
            System.out.println("Введите номер отеля в котором вы хотите забронировать комнату:");
            System.out.println("Для выхода введите \"0\".");
            int hotelIndex = Integer.parseInt(buffRead.readLine()) - 1;
            if (hotelIndex == -1) return;
            Hotel hotel = controller.getCommonDAO().getDataBase().getHotelList().get(hotelIndex);
            System.out.println("**** Бронирование комнат в отеле " + hotel.getHotelName() + " ****");
            count = 1;
            for (Room room : hotel.getRoomList()) {
                System.out.println(count++ + ". * " + room);
            }
            System.out.println("Введите номер комнати которою ви хотите забронировать: ");
            int i = Integer.parseInt(buffRead.readLine());


            controller.roomReservationByName(hotelIndex, i - 1);
            hotel.getRoomList().stream().forEach(System.out::println);
            System.out.println(controller.getCommonDAO().getDataBase().getCurrentUser().getRoomList());
        } catch (InvalidRoomStatus | InvalidHotelStatus invalidStatus) {
            System.out.println(invalidStatus.getMessage());
            reservationRoom();
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            System.out.println("Не верная операция, повторите попытку!");
            reservationRoom();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cancelReservation() {
        int count = 1;
        List<Room> userRooms = controller.getCommonDAO().getDataBase().getCurrentUser().getRoomList();
        for (Room room : userRooms) {
            System.out.println(count++ + ". * " + room);
        }
        try {
            System.out.println("Введите номер комнаты с которой вы хотите снять бронь: ");
            System.out.println("Для выхода введите \"0\".");
            int roomIndex = Integer.parseInt(buffRead.readLine()) - 1;
            if (roomIndex == -1) return;

            if (controller.cancelReservationByName(roomIndex)) {
                System.out.println("Бронь отменена!");
            }
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            System.out.println("Не верная операция, повторите попытку!");
            cancelReservation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}