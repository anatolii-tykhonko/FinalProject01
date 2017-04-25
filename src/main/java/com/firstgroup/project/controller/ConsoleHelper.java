package com.firstgroup.project.controller;

import com.firstgroup.project.Exceptions.HotelAlreadyExist;
import com.firstgroup.project.hotels.Hotel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Sonikb on 22.04.2017.
 */
public class ConsoleHelper {
    Controller controller = new Controller();

    public static void main(String[] args) {
        ConsoleHelper consoleHelper = new ConsoleHelper();
        consoleHelper.mainMenu();
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
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        Scanner scanner = new Scanner(System.in);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            switch (Integer.parseInt(br.readLine())) {
//            switch (scanner.nextInt()){
                case 1:
                    System.out.println("1. * Добавить отель");
                    addHotel();
                    break;
                case 2:
                    System.out.println("2. * Редактировать данные отеля");
                    break;
                case 3:
                    System.out.println("3. * Добавить комнату в отель");
                    break;
                case 4:
                    System.out.println("4. * Редактировать данные комнаты");
                    break;
                case 5:
                    System.out.println("5. * Удалить комнату из отеля");
                    break;
                case 6:
                    System.out.println("6. * Удалить отель");
                    break;
                case 7:
                    System.out.println("7. * Зарегистрировать пользователя");
                    break;
                case 8:
                    System.out.println("8. * Редактировать данные пользователя");
                    break;
                case 9:
                    System.out.println("9. * Удалить пользователя");
                    break;
                case 10:
                    System.out.println("10. * Поиск отеля по имени");
                    break;
                case 11:
                    System.out.println("11. * Поиск отеля по городу");
                    break;
                case 12:
                    System.out.println("12. * Поиск комнаты по отелю");
                    break;
                case 13:
                    System.out.println("13. * Бронирование комнаты на имя пользователя");
                    break;
                case 14:
                    System.out.println("14. * Отмена бронирования комнаты");
                    break;
                case 0:
                    System.out.println("0. * ВЫХОД");
                    br.close();
                    break;
                default:
                    System.err.println("Не верный номер операции! Повторите попытку!" + " \nДля выхода нажмите \"0\"");
                    chooseTheOperation();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException n) {
            System.err.println("Не верный формат даных! Попробуйте снова!" + " \nДля выхода нажмите \"0\"");
            chooseTheOperation();
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
            hotel = controller.addHotel(hotelName,cityName,roomPersons,roomPrice,dateAvailableFrom);
        }catch (HotelAlreadyExist r){
            r.getMessage();
            addHotel();
        }
        System.out.println(hotel.getHotelName() + " успешно сохранен!");
        mainMenu();
    }

}
