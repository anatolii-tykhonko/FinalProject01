package com.firstgroup.project.controller;

import com.firstgroup.project.Exceptions.HotelAlreadyExist;
import com.firstgroup.project.Exceptions.IncorrectEmail;
import com.firstgroup.project.Exceptions.IncorrectPassword;
import com.firstgroup.project.Exceptions.UserAlreadyExist;
import com.firstgroup.project.dataBase.DBService;
import com.firstgroup.project.hotels.Hotel;
import com.firstgroup.project.loginService.LoginController;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Sonikb on 22.04.2017.
 */
public class ConsoleHelper {
    Controller controller = new Controller();
    LoginController loginController = new LoginController();

    public static void main(String[] args) {
        ConsoleHelper consoleHelper = new ConsoleHelper();
//        consoleHelper.mainMenu();
        consoleHelper.loginService();
    }




    public void loginService() {
        System.out.println("Что бы войти в систему создайте профиль или выполните вход с существуещего!");
        System.out.println("\n1. * Зарегистрироваться!");
        System.out.println("2. * Войти!");

        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        if ("1".equals(line)) {
            regUser();
            loginService();
        } else {
            enterToSystem();
        }
    }

    public void enterToSystem() {
        System.out.println("***Вход в систему***");
        String email;
        String password;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Введите email: ");
            email = sc.nextLine();
            System.out.println("Введите password: ");
            password = sc.nextLine();
            if (loginController.loginUser(email, password)) {
                System.out.println("Вход выполнен " + loginController.getDbService().getDataBase().getCurrentUser().getName() + "\n");
                mainMenu();
            }
        } catch (IncorrectEmail incorrectEmail) {
            System.out.println(incorrectEmail.getMessage());
            enterToSystem();
        } catch (IncorrectPassword incorrectPassword) {
            System.out.println(incorrectPassword.getMessage());
            enterToSystem();
        }
    }

    public void regUser() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите Ваше имя");
            String name = sc.nextLine();
            System.out.println("Введите Вашу фамилию");
            String secondName = sc.nextLine();
            System.out.println("Введите Ваш email");
            String email = sc.nextLine();
            System.out.println("Введите PASSWORD");
            String password = sc.nextLine();

            loginController.registerUser(name, secondName, email, password);
        } catch (UserAlreadyExist e) {
            System.out.println(e.getMessage());
            regUser();
        }


    }

    public void mainMenu() {

        System.out.println("-->Система бронирования отелей<--" + "\n");

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
        System.out.println("13. * Бронирование комнаты на имя пользователя");
        System.out.println("14. * Отмена бронирования комнаты" + "\n");
        System.out.println("0. * ВЫХОД");
        chooseTheOperation();
    }

    private void chooseTheOperation() {
        System.out.println("Введите номер операции которую вы хотите произвести!!!");
        try {
            Scanner sc = new Scanner(System.in);
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("\n***** Добавление отеля в базу данных *****\n");
                    addHotel();
                    break;
                case 2:
                    System.out.println("\n***** Редактировать данные отеля *****\n");
                    break;
                case 3:
                    System.out.println("\n***** Добавление комнаты в отель *****\n");
                    addRoom();
                    break;
                case 4:
                    System.out.println("\n***** Редактировать данные комнаты *****\n");
                    break;
                case 5:
                    System.out.println("\n***** Удалить комнату из отеля *****\n");
                    break;
                case 6:
                    System.out.println("\n***** Удалить отель *****\n");
                    break;
                case 7:
                    System.out.println("\n***** Зарегистрировать пользователя *****\n");
                    break;
                case 8:
                    System.out.println("\n***** Редактировать данные пользователя *****\n");
                    break;
                case 9:
                    System.out.println("\n***** Удалить пользователя *****\n");
                    break;
                case 10:
                    System.out.println("\n***** Поиск отеля по имени *****\n");
                    break;
                case 11:
                    System.out.println("\n***** Поиск отеля по городу *****\n");
                    break;
                case 12:
                    System.out.println("\n***** Поиск комнаты по отелю *****\n");
                    break;
                case 13:
                    System.out.println("\n***** Бронирование комнаты на имя пользователя *****\n");
                    break;
                case 14:
                    System.out.println("\n***** Отмена бронирования комнаты *****\n");
                    break;
                case 0:
                    System.out.println("\n***** Программа завершена, все изменения сохранены *****\n");
                    sc.close();
                    DBService.save();
                    break;
                default:
                    System.out.println("Не верный номер операции! Повторите попытку!" + " \nДля выхода нажмите \"0\"");
                    chooseTheOperation();
            }
        } catch (IOException e) {
            e.printStackTrace();              //TODO create some exception
        } catch (NoSuchElementException n) {
            mainMenu();
        }
    }

    private void addHotel() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите название отеля:");
        String hotelName = scanner.nextLine();
        System.out.println("Укажите название города:");
        String cityName = scanner.nextLine();
        System.out.println("Для создания отеля необходимо создать хотя бы одну комнату!");
        System.out.println("Укажите количество спальных мест в номере:");
        int roomPersons = scanner.nextInt();
        System.out.println("Укажите цену номера в грн:");
        double roomPrice = scanner.nextDouble();
        System.out.println("Укажите дату когда номер будет доступен в формате year.mm.dd");
        scanner.nextLine();
        String dateAvailableFrom = scanner.nextLine();
        Hotel hotel = null;
        try {
            hotel = controller.addHotel(hotelName, cityName, roomPersons, roomPrice, dateAvailableFrom);
        } catch (HotelAlreadyExist r) {
            r.getMessage();
            addHotel();
        }
        System.out.println(hotel.getHotelName() + " успешно сохранен!");
        mainMenu();
    }

    private void addRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите отель в котором вы хотите добавить комнату:");
        int count = 1;
        List<String> hotelNames = controller.getDbService().getDataBase().getHotelList().stream().map(Hotel::getHotelName).collect(Collectors.toList());
        for (String hotel : hotelNames) {
            System.out.println(count++ + ". * " + hotel);
        }
        int i = scanner.nextInt();
        Hotel hotel = controller.getDbService().getDataBase().getHotelList().get(i - 1);
        System.out.println("**** Добавление комнат в отель " + hotel.getHotelName() + " ****");
        System.out.println("Укажите количество спальных мест в номере:");
        int roomPersons = scanner.nextInt();
        System.out.println("Укажите цену номера в грн:");
        double roomPrice = scanner.nextDouble();
        System.out.println("Укажите дату когда номер будет доступен в формате year.mm.dd");
        scanner.nextLine();
        String dateAvailableFrom = scanner.nextLine();
        controller.addRoom(hotel, roomPersons, roomPrice, dateAvailableFrom);
    }

}
