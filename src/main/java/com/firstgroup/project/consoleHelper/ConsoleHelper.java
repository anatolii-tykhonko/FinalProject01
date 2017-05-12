package com.firstgroup.project.consoleHelper;

import com.firstgroup.project.APIs.Application;
import com.firstgroup.project.DAOs.DBServiceSingleton;
import com.firstgroup.project.Exceptions.*;
import com.firstgroup.project.entity.Hotel;
import com.firstgroup.project.entity.Room;
import com.firstgroup.project.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Класс предоставляет консольный пользовательский интерфейс.
 */

public class ConsoleHelper {
    Application application;
    BufferedReader buffRead = new BufferedReader(new InputStreamReader(System.in));

    public ConsoleHelper(Application application) {
        this.application = application;
    }

    private void chooseTheOperation() {

        while (true) {

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

            System.out.println("\nВведите номер операции которую вы хотите произвести!!!");
            try {
                switch (Integer.parseInt(buffRead.readLine())) {
                    case 1:
                        System.out.println("\n*----------------------------------------------*\n" +
                                "*------> Добавление отеля в базу данных <------*\n" +
                                "*----------------------------------------------*\n");
                        addHotel();
                        break;
                    case 2:
                        System.out.println("\n*-----------------------------------------------------*\n" +
                                "*-----------> Редактирование данных отеля <-----------*\n" +
                                "*-----------------------------------------------------*\n");
                        editHotelInfo();
                        break;
                    case 3:
                        System.out.println("\n*------------------------------------------------------*\n" +
                                "*------------> Добавление комнаты в отель <------------*\n" +
                                "*------------------------------------------------------*\n");
                        addRoom();
                        break;
                    case 4:
                        System.out.println("\n*-------------------------------------------------------*\n" +
                                "*-----------> Редактирование данных комнаты <-----------*\n" +
                                "*-------------------------------------------------------*\n");
                        editRoomInfo();
                        break;
                    case 5:
                        System.out.println("*-----------------------------------------------------*\n" +
                                "*------------> Удалиение комнат из отеля <------------*\n" +
                                "*-----------------------------------------------------*");
                        deleteRoom();
                        break;
                    case 6:
                        System.out.println("*-----------------------------------------------------*\n" +
                                "*-----------------> Удаление отелей <-----------------*\n" +
                                "*-----------------------------------------------------*\n");
                        deleteHotel();
                        break;
                    case 7:
                        System.out.println("*---------------------------------------------------------------*\n" +
                                "*-----------------> Регистрация пользователей <-----------------*\n" +
                                "*---------------------------------------------------------------*\n");
                        addUser();
                        break;
                    case 8:
                        System.out.println("*--------------------------------------------------------------*\n" +
                                "*------------> Редактирование данных пользователя <------------*\n" +
                                "*--------------------------------------------------------------*\n");
                        editUserInfo();
                        break;
                    case 9:
                        System.out.println("*------------------------------------------------------------------*\n" +
                                "*---------------------> Удаление пользователей <-------------------*\n" +
                                "*------------------------------------------------------------------*\n");
                        deleteUser();
                        break;
                    case 10:
                        System.out.println("\n*-----------------------------------------------*\n" +
                                "*------------> Поиск отеля по имени <-----------*\n" +
                                "*-----------------------------------------------*\n");
                        findByNameHotel();
                        break;
                    case 11:
                        System.out.println("\n*----------------------------------------------*\n" +
                                "*-----------> Поиск отеля по городу <----------*\n" +
                                "*----------------------------------------------*\n");
                        findByCity();
                        break;
                    case 12:
                        System.out.println("\n*----------------------------------------------*\n" +
                                "*-----------> Поиск комнат по отелю <----------*\n" +
                                "*----------------------------------------------*\n");
                        findRoomsByHotel();
                        break;
                    case 13:
                        System.out.println("\n*----------------------------------------------*\n" +
                                "*-----------> Поиск комнаты по цене <----------*\n" +
                                "*----------------------------------------------*\n");
                        findRoomsByRangePrice();
                        break;
                    case 14:
                        System.out.println("\n*----------------------------------------------------------*\n" +
                                "*-------> Бронирование комнаты на имя пользователя <-------*\n" +
                                "*----------------------------------------------------------*\n");
                        reservationRoom();
                        break;
                    case 15:
                        System.out.println("\n*----------------------------------------------------------------------------*\n" +
                                "*-----------------------> Отмена бронирования комнаты <----------------------*\n" +
                                "*----------------------------------------------------------------------------*\n");
                        cancelReservation();
                        break;
                    case 0:
                        System.out.println("\n*------------------------------------------------------------------------------*\n" +
                                "*---------------> Программа завершена, все изменения сохранены! <--------------*\n" +
                                "*------------------------------------------------------------------------------*\n");
                        buffRead.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Не верный номер операции! Повторите попытку!" + " \nДля выхода нажмите \"0\"");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchElementException | NumberFormatException n) {
                System.out.println("Команда введена неверно! Повторите выбор!" + " \nДля выхода нажмите \"0\"");
            }
        }
    }

    private void userChooseTheOperation() {
        while (true) {

            System.out.println("*-------------------------------------------*\n" +
                    "*------> Система бронирования отелей <------*\n" +
                    "*-------------------------------------------*");

            System.out.println("1. * Поиск отеля по имени");
            System.out.println("2. * Поиск отеля по городу");
            System.out.println("3. * Поиск комнаты по отелю");
            System.out.println("4. * Поиск комнат по цене");
            System.out.println("5. * Бронирование комнаты на имя пользователя");
            System.out.println("6. * Отмена бронирования комнаты" + "\n");
            System.out.println("0. * ВЫХОД");
            System.out.println("*-------------------------------------------*");

            System.out.println("\nВведите номер операции которую вы хотите произвести!!!");
            try {
                switch (Integer.parseInt(buffRead.readLine())) {
                    case 1:
                        System.out.println("\n*-----------------------------------------------*\n" +
                                "*------------> Поиск отеля по имени <-----------*\n" +
                                "*-----------------------------------------------*\n");
                        findByNameHotel();
                        break;
                    case 2:
                        System.out.println("\n*----------------------------------------------*\n" +
                                "*-----------> Поиск отеля по городу <----------*\n" +
                                "*----------------------------------------------*\n");
                        findByCity();
                        break;
                    case 3:
                        System.out.println("\n*----------------------------------------------*\n" +
                                "*-----------> Поиск комнат по отелю <----------*\n" +
                                "*----------------------------------------------*\n");
                        findRoomsByHotel();
                        break;
                    case 4:
                        System.out.println("\n*----------------------------------------------*\n" +
                                "*-----------> Поиск комнаты по цене <----------*\n" +
                                "*----------------------------------------------*\n");
                        findRoomsByRangePrice();
                        break;
                    case 5:
                        System.out.println("\n*----------------------------------------------------------*\n" +
                                "*-------> Бронирование комнаты на имя пользователя <-------*\n" +
                                "*----------------------------------------------------------*\n");
                        reservationRoom();
                        break;
                    case 6:
                        System.out.println("\n*----------------------------------------------------------------------------*\n" +
                                "*-----------------------> Отмена бронирования комнаты <----------------------*\n" +
                                "*----------------------------------------------------------------------------*\n");
                        cancelReservation();
                        break;
                    case 0:
                        System.out.println("\n*------------------------------------------------------------------------------*\n" +
                                "*---------------> Программа завершена, все изменения сохранены! <--------------*\n" +
                                "*------------------------------------------------------------------------------*\n");
//                        DBServiceSingleton.save();
                        buffRead.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Не верный номер операции! Повторите попытку!" + " \nДля выхода нажмите \"0\"");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchElementException | NumberFormatException n) {
                System.out.println("Команда введена неверно! Повторите выбор!" + " \nДля выхода нажмите \"0\"");
            }
        }
    }


    private void addHotel() {
        while (true) {
            try {
                System.out.println("Укажите название отеля. Для выхода введите \"0\"!");
                String hotelName = buffRead.readLine();
                if ("0".equals(hotelName)) return;
                application.validLine(hotelName);
                System.out.println("Укажите название города:");
                String cityName = buffRead.readLine();
                application.validLine(cityName);
                System.out.println("Для создания отеля необходимо создать хотя бы одну комнату!");
                System.out.println("Укажите количество спальных мест в номере:");
                int roomPersons = Integer.parseInt(buffRead.readLine());
                if (roomPersons == 0 || roomPersons < 0) {
                    System.out.println("Вы ввели 0 или отритцательное число!\nВведите данные повторно!\n");
                    continue;
                }
                System.out.println("Укажите цену номера в грн/сутки:");
                double roomPrice = Double.parseDouble(buffRead.readLine());
                if (roomPrice == 0 || roomPrice < 0) {
                    System.out.println("Вы ввели 0 или отритцательное число!\nВведите данные повторно!\n");
                    continue;
                }
                System.out.println("Укажите дату когда номер будет доступен в формате year.mm.dd");
                String dateAvailableFrom = buffRead.readLine();
                Hotel hotel = application.addHotel(hotelName, cityName, roomPersons, roomPrice, dateAvailableFrom);
                System.out.println(hotel.getHotelName() + " успешно сохранен!\n");
                System.out.println("Для повторного добавления отеля нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (HotelAlreadyExist | ValidStringNameException | InvalidDateFormat r) {
                System.out.println(r.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Данные введены неверно!\nВведите данные повторно!\n");
            } catch (IndexOutOfBoundsException | DateTimeException e) {
                System.out.println("Дата введена неверно!\nВведите данные повторно!\n");
            }
        }
    }

    private void addRoom() {
        while (true) {
            System.out.println("Выберите отель в котором вы хотите добавить комнату. Для выхода введите \"0\"!");
            int count = 1;
            List<Hotel> hotelList = application.getHotels();
            for (Hotel hotel : hotelList) {
                System.out.println(count++ + ". * Отель " + hotel.getHotelName() + ", город " + hotel.getCityName());
            }
            hotelList.stream().map(hotel -> {
                System.out.println("Отель: "+ hotel.getHotelName()+", город "+hotel.getHotelName());
                return hotel.getRoomList();
            }).forEach(rooms -> rooms.forEach(System.out::println));
            try {
                int hotelIndex = Integer.parseInt(buffRead.readLine()) - 1;
                if (-1 == hotelIndex) return;
                Hotel hotel = application.getHotels().get(hotelIndex);
                System.out.println("**** Добавление комнат в отель " + hotel.getHotelName() + ", город " + hotel.getCityName() + " ****");
                System.out.println("Укажите количество спальных мест в номере:");
                int roomPersons = Integer.parseInt(buffRead.readLine());
                if (roomPersons == 0 || roomPersons < 0) {
                    System.out.println("Вы ввели 0 или отритцательное число!\nВведите данные повторно!\n");
                    continue;
                }
                System.out.println("Укажите цену номера в грн:");
                double roomPrice = Integer.parseInt(buffRead.readLine());
                if (roomPrice == 0 || roomPrice < 0) {
                    System.out.println("Вы ввели 0 или отритцательное число!\nВведите данные повторно!\n");
                    continue;
                }
                System.out.println("Укажите дату когда номер будет доступен в формате year.mm.dd");
                String dateAvailableFrom = buffRead.readLine();
                application.addRoom(hotelIndex, roomPersons, roomPrice, dateAvailableFrom);
                System.out.println("Комната сохранена в отель " + hotel.getHotelName() + '\n');
                System.out.println("Для повторного добавления комнаты нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ValidStringNameException | InvalidDateFormat r) {
                System.out.println(r.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Данные введены неверно!\nВведите данные повторно!\n");
            } catch (DateTimeException e) {
                System.out.println("Дата введена неверно!\nВведите данные повторно!\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Некоректно введенные данные, попробуйте снова!\n");
            }
        }
    }

    private void deleteHotel() {
        while (true) {
            System.out.println("Cписок отелей для удаления. Для выхода введите \"0\"!");
            int count = 1;
            List<Hotel> hotelList = application.getHotels();
            if (hotelList.isEmpty()) {
                System.out.println("Лист отелей пустой, чтобы делать какие-либо действия сначала добавьте отель");
                return;
            }
            for (Hotel hotel : hotelList) {
                System.out.println(count++ + ". * Отель " + hotel.getHotelName() + ", город " + hotel.getCityName());
            }
            System.out.println("Укажите номер отеля которого вы хотите удалить: ");
            try {
                int hotelIndex = Integer.parseInt(buffRead.readLine()) - 1;
                if (hotelIndex == -1) return;
                application.deleteHotel(hotelIndex);
                System.out.println("Список отелей после удаления: ");
                List<String> hotelNamesWhenWeDeleteHotel = application.getHotels()
                        .stream()
                        .map(Hotel::getHotelName)
                        .collect(toList());
                int count1 = 1;
                for (String hotel : hotelNamesWhenWeDeleteHotel) {
                    System.out.println(count1++ + ". * " + hotel);
                }
                System.out.println("\nДля повторного удаления отеля нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Номер отеля который вы ввели не существует, попробуйте еще раз: ");
            } catch (NumberFormatException e) {
                System.out.println("Не верный формат данных, попробуйте еще раз: ");
            }
        }
    }

    private void deleteRoom() {
        while (true) {
            System.out.println("Список отелей:");
            int count = 1;
            List<Hotel> hotelList = application.getHotels();
            if (hotelList.isEmpty()) {
                System.out.println("Лист отелей пустой, чтобы делать какие-либо действия сначала добавьте отель");
                return;
            }
            for (Hotel hotel : hotelList) {
                System.out.println(count++ + ". * Отель " + hotel.getHotelName() + ", город " + hotel.getCityName());
            }
            try {
                System.out.println("Введите номер отеля с которого вы хотите удалить комнату! Для выхода введите \"0\"! ");
                int hotelIndex = Integer.parseInt(buffRead.readLine()) - 1;
                if (hotelIndex == -1) return;
                Hotel hotel = application.getHotels().get(hotelIndex);
                System.out.println("**** Удаление комнат в отеле " + hotel.getHotelName() + " ****");
                count = 1;
                for (Room room : hotel.getRoomList()) {
                    System.out.println(count++ + ". * " + room);
                }
                if (hotel.getRoomList().size() == 1) {
                    System.out.println("В отеле " + hotel.getHotelName()
                            + " должна оставаться минимум одна комната, поэтому эту комнату удалить нельзя!!! \n");
                    continue;
                }
                System.out.println("Введите номер комнаты которою вы хотите удалить: ");
                int i = Integer.parseInt(buffRead.readLine());
                application.deleteRoom(hotelIndex, i);
                System.out.println("**** Новый список комнат в " + hotel.getHotelName() + " ****");
                List<Room> roomList1 = new ArrayList<>(hotel.getRoomList());
                count = 1;
                for (Room room1 : roomList1) {
                    System.out.println(count++ + ". * " + room1);
                }
                System.out.println("\nДля повторного удаления комнаты нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Неправильная операция, выберите существующий номер из списка: ");
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат данных, введите даные повторно: ");
            }

        }
    }

    public void editHotelInfo() {
        while (true) {
            int count = 1;
            if (application.getHotels().isEmpty()) {
                System.out.println("В базе нет отелей, сначала необходимо создать отель!");
                return;
            }
            System.out.println("Выберите из списка номер отеля который необходимо отредактировать. Для выхода введите \"0\"!");
            List<Hotel> hotelList = application.getHotels();
            for (Hotel hotel : hotelList) {
                System.out.println(count++ + ". * " + hotel.getHotelName() + ", город " + hotel.getCityName());
            }
            try {
                int hotelIndex = Integer.parseInt(buffRead.readLine()) - 1;
                if (-1 == hotelIndex) return;
                System.out.println("Название отеля " + hotelList.get(hotelIndex).getHotelName() + " изменяем на: ");
                String newHotelName = buffRead.readLine();
                application.validLine(newHotelName);
                System.out.println("Название города " + hotelList.get(hotelIndex).getCityName() + " изменяем на: ");
                String newCityName = buffRead.readLine();
                application.validLine(newCityName);
                Hotel editedHotel = application.editHotelDetails(hotelIndex, newHotelName, newCityName);
                System.out.println("Изменениея успешно сохранены: имя отеля " + editedHotel.getHotelName() + ", город: " + editedHotel.getCityName());
                System.out.println("\nДля повторного изменения данных отеля нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ValidStringNameException e) {
                System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Некоректно введенные данные, попробуйте снова!\n");
            } catch (NumberFormatException n) {
                System.out.println("Неверный формат данных, введите даные повторно!\n");
            }
        }
    }

    public void editUserInfo() {
        while (true) {
            System.out.println("Список пользователей в системе: ");
            Set<Map.Entry<String, User>> entrySet = application.getUsers().entrySet();
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
                application.validLine(newName);
                System.out.println("Введите новою фамилию пользователя: ");
                String newSurName = buffRead.readLine();
                application.validLine(newSurName);
                System.out.println("Параметры пользователя после редактирования: \n"
                        + application.editUserInfo(newName, newSurName, oldEmail));
                System.out.println("Для повторного изменения данных пользователя нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат данных, введите даные повторно: ");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Некоректно введенные данные, попробуйте снова!\n");
            } catch (ValidStringNameException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void editRoomInfo() {
        while (true) {
            System.out.println("***** Список отелей в системе *****");
            int count = 1;
            List<Hotel> hotelList = application.getHotels();
            if (hotelList.isEmpty()) {
                System.out.println("\nВ системе не создано ни одного отеля, сначала добавьте отель в систему!\n");
                return;
            }
            for (Hotel hotel : hotelList) {
                System.out.println(count++ + ". * Отель " + hotel.getHotelName() + ", город " + hotel.getCityName());
            }
            try {
                System.out.println("Введите номер отеля в котором необходимо редактировать комнаты. Для выхода введите \"0\"!");
                int hotelIndex = Integer.parseInt(buffRead.readLine()) - 1;
                if (-1 == hotelIndex) return;
                Hotel hotel = application.getHotels().get(hotelIndex);
                System.out.println("***** Список комнат в отеле " + hotel.getHotelName() + ", город " + hotel.getCityName() + " *****");
                count = 1;
                for (Room room : hotel.getRoomList()) {
                    System.out.println(count++ + ". * " + room);
                }
                System.out.println("Введите номер комнаты которою Вы хотите редактировать: ");
                int roomIndex = Integer.parseInt(buffRead.readLine()) - 1;
                Room room = hotel.getRoomList().get(roomIndex);
                if (room.isStatus()) {
                    System.out.println("Данная комната забронирована и ее нельзя изменить до окончания бронирования!");
                    continue;
                }
                System.out.println("*------> Редактирование параметров комнаты <------*");
                System.out.println("Количество спальных мест в номере: " + room.getPersons() + " изменяем на: ");
                int roomPersons = Integer.parseInt(buffRead.readLine());
                if (roomPersons <= 0) {
                    System.out.println("Вы ввели 0 или отритцательное число!\nВведите данные повторно!\n");
                    continue;
                }
                System.out.println("Цена номера в грн/сутки: " + room.getPrice() + " изменяем на: ");
                double roomPrice = Double.parseDouble(buffRead.readLine());
                if (roomPrice <= 0) {
                    System.out.println("Вы ввели 0 или отритцательное число!\nВведите данные повторно!\n");
                    continue;
                }
                System.out.println("Укажите дату когда номер будет доступен в формате year.mm.dd");
                String dateAvailableFrom = buffRead.readLine();
                System.out.println("Комната после введеных изменений: \n" +
                        application.editRoomDetails(hotelIndex, roomIndex, roomPersons, roomPrice, dateAvailableFrom));
                System.out.println("\nДля повторного добавления отеля нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Неправильная операция, попробуйте снова: ");
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат данных, введите даные повторно: ");
            } catch (DateTimeException e) {
                System.out.println("Дата введена неверно!\nВведите данные повторно!\n");
            } catch (InvalidDateFormat e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void loginService() {
        while (true) {
            System.out.println("\n*------------------------------------------------------------------------*\n" +
                    "*----------> Добро пожаловать в систему бронирования отелей:) <----------*\n" +
                    "*------------------------------------------------------------------------*");
            System.out.println("Чтобы войти в систему создайте профиль или выполните вход с существуещего!\n");
            System.out.println("1. * Зарегистрироваться!");
            System.out.println("2. * Войти!");
            System.out.println("*------------------------------------------------------------------------*");
            try {
                int line = Integer.parseInt(buffRead.readLine());
                if (1 != line && 2 != line) {
                    System.out.println("Не верная команда, повторите попытку!\n");
                    continue;
                }
                if (1 == line) {
                    regUser();
                } else {
                    enterToSystem();
                }
            } catch (NumberFormatException num) {
                System.out.println("Не верный формат даных, попробуйте снова!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void enterToSystem() {
        while (true) {
            System.out.println("***Вход в систему***");
            String email;
            String password;
            try {
                System.out.println("Введите email! Для выхода введите \"0\"!");
                email = buffRead.readLine();
                if ("0".equals(email)) return;
                System.out.println("Введите password: ");
                password = buffRead.readLine();
                if (application.loginUser(email, password)) {
                    System.out.println("Вход выполнен " + application.curUser().getName() + "\n");
                    if ("admin".equals(password)) {
                        chooseTheOperation();
                    } else userChooseTheOperation();
                }
            } catch (IncorrectEmail | IncorrectPassword ex) {
                System.out.println(ex.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void regUser() {
        while (true) {
            try {
                System.out.println("Укажите Ваше имя! Для выхода введите \"0\"!");
                String name = buffRead.readLine();
                if ("0".equals(name)) return;
                application.validLine(name);
                System.out.println("Укажите Вашу фамилию");
                String secondName = buffRead.readLine();
                application.validLine(secondName);
                System.out.println("Укажите Ваш email");
                String email = buffRead.readLine();
                System.out.println("Укажите PASSWORD");
                String password = buffRead.readLine();

                User user = application.registerUser(name, secondName, email, password, true);
                System.out.println("Пользователь " + user.getEmail() + " успешно зарегистрирован!\n");
                if (user.getPassword().equals("admin")) {
                    chooseTheOperation();
                } else userChooseTheOperation();
            } catch (UserAlreadyExist | ValidStringNameException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addUser() {
        while (true) {
            try {
                System.out.println("Укажите имя нового пользователя! Для возврата в меню введите \"0\"!");
                String name = buffRead.readLine();
                if ("0".equals(name)) return;
                application.validLine(name);
                System.out.println("Укажите фамилию нового пользователя");
                String secondName = buffRead.readLine();
                application.validLine(secondName);
                System.out.println("Укажите email нового пользователя");
                String email = buffRead.readLine();
                System.out.println("Укажите PASSWORD");
                String password = buffRead.readLine();

                User user = application.registerUser(name, secondName, email, password, false);
                System.out.println("Пользователь " + "\'" + user.getEmail() + "\'" + " успешно зарегистрирован!\n");
                System.out.println("Для повторного добавления пользователя нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (UserAlreadyExist | ValidStringNameException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteUser() {
        while (true) {
            System.out.println("Cписок пользователей ----------------------------------------------\n");
            Set<Map.Entry<String, User>> entries = application.getUsers().entrySet();
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
                System.out.println("Пользователь " + "\'" + application.deleteUser(emailList.get(emailIndex)).getEmail() + "\'" + " удалён!\n");
                System.out.println("Для повторного удаления пользователя нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (CantDeleteCurrentUser ex) {
                System.out.println(ex.getMessage());
            } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                System.out.println("Не верная операция, повторите попытку!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void findByNameHotel() {
        while (true) {
            System.out.println("*------------------------------------------------*");
            System.out.println("В системе имеются следующие отели: ");
            int count = 1;
            List<Hotel> hotelList = application.getHotels();
            for (Hotel hotel : hotelList) {
                System.out.println(count++ + ". " + hotel.getHotelName() + ", город " + hotel.getCityName());
            }
            System.out.println("Введите номер, который соответствует названию отеля. Введите 0, если желаете вернуться в главное меню. ");
            try {
                int index = Integer.parseInt(buffRead.readLine());
                if (index == 0) return;
                String name = application.getHotels().get(index - 1).getHotelName();
                List<Hotel> hotelByName = application.findHotelByName(name);
                for (Hotel hotel : hotelByName) {
                    System.out.println("*-----------------------------------------------------------*");
                    System.out.println("Название отеля: " + hotel.getHotelName() + ";" + "\n" +
                            "Местонахождение отеля: " + hotel.getCityName() + ";" + "\n" + "Доступные комнаты: ");
                    hotel.getRoomList().forEach(System.out::println);
                    System.out.println("*-----------------------------------------------------------*");
                }
                System.out.println("Для продолжения поиска отеля по названию нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Вы ввели недопустимые символы,повторите Ваш ввод");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void findByCity() {
        while (true) {
            System.out.println("*----------------------------------------------*");
            System.out.println("В системе имеются отели в следующих городах: ");
            int count = 1;

            List<String> cityNames = application.getHotels()
                    .stream()
                    .map(Hotel::getCityName)
                    .distinct()
                    .collect(Collectors.toList());

            for (String cityName : cityNames) {
                System.out.println(count++ + ". " + cityName);
            }
            System.out.println("Введите номер, который соответствует названию города. Введите 0, если желаете вернуться в главное меню. ");
            try {
                int index = Integer.parseInt(buffRead.readLine());
                if (index == 0) return;
                String city = cityNames.get(index - 1);
                List<Hotel> hotelByCity = application.findHotelByCity(city);
                System.out.println("По заданым критериям поиска доступны следующие отели: ");
                for (Hotel hotel : hotelByCity) {
                    System.out.println("*----------------------------------------------------------*");
                    System.out.println("Название отеля: " + hotel.getHotelName() + ";" + "\n" +
                            "Местонахождение отеля: " + hotel.getCityName() + ";" + "\n" + "Доступные комнаты: ");
                    hotel.getRoomList().forEach(System.out::println);
                    System.out.println("*----------------------------------------------------------*");
                }
                System.out.println("Для продолжения поиска отеля по городу нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Вы ввели недопустимые символы,повторите Ваш ввод");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void findRoomsByHotel() {
        while (true) {
            System.out.println("*----------------------------------------------*");
            System.out.println("В системе имеются комнаты в следующих отелях: ");
            int count = 1;
            List<Hotel> hotelList = application.getHotels();
            for (Hotel hotel : hotelList) {
                System.out.println(count++ + ". " + hotel.getHotelName() + ", город " + hotel.getCityName());
            }
            System.out.println("Введите номер, который соответствует названию отеля. Введите 0, если желаете вернуться в главное меню. ");
            try {
                int index = Integer.parseInt(buffRead.readLine());
                if (index == 0) return;
                String name = application.getHotels().get(index - 1).getHotelName();
                List<Hotel> hotelByName = application.findRoomsByHotel(name);
                for (Hotel hotel : hotelByName) {

                    System.out.println("*----------------------------------------------------------*");
                    hotel.getRoomList().forEach(System.out::println);
                    System.out.println("*----------------------------------------------------------*");

                }
                System.out.println("Для продолжения поиска комнат по названию отеля нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Вы ввели недопустимые символы,повторите Ваш ввод");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void findRoomsByRangePrice() {
        while (true) {
            int count = 1;
            System.out.println("*-------------------------------------------*");
            System.out.println("В системе имеются отели в следующих городах: ");
            List<String> cityNames = application.getHotels()
                    .stream()
                    .map(Hotel::getCityName)
                    .distinct()
                    .collect(Collectors.toList());

            for (String cityName : cityNames) {
                System.out.println(count++ + ". " + cityName);
            }
            System.out.println("Введите номер, который соответствует названию города. Введите 0, если желаете вернуться в главное меню. ");
            try {
                int index = Integer.parseInt(buffRead.readLine());
                if (index == 0) return;
                String city = cityNames.get(index - 1);
                List<Hotel> hotelByCity = application.findHotelByCity(city);
                System.out.println("Введите минимальную цену для поиска: ");
                Double minPrice = Double.parseDouble(buffRead.readLine());
                System.out.println("Введите максимальную цену: ");
                Double maxPrice = Double.parseDouble(buffRead.readLine());
                System.out.println("По вашим критериям поиска найдено следующие комнаты: ");
                index = 0;
                Map<String, List<Room>> o1 = new HashMap<>();
                for (Hotel hotel : hotelByCity) {
                    o1.put(hotel.getHotelName(), new ArrayList<>());
                    for (Room room : hotel.getRoomList()) {
                        if (room.getPrice() >= minPrice && room.getPrice() <= maxPrice) {
                            index++;
                            o1.get(hotel.getHotelName()).add(room);
                        }
                    }

                }
                for (Map.Entry<String, List<Room>> pair : o1.entrySet()) {

                    if (pair.getValue().isEmpty()) continue;
                    System.out.println("*---------------------------------------------------------------*");
                    System.out.println("Название отеля: " + pair.getKey() + "\n" +
                            "Доступные комнаты: ");
                    for (int i = 0; i < pair.getValue().size(); i++) {
                        System.out.println((i + 1) + ". " + pair.getValue().get(i));
                    }
                    System.out.println("*---------------------------------------------------------------*");
                }
                if (index == 0) {
                    System.out.println("По заданым критериям комнаты отсутствуют.");
                }
                System.out.println("\nДля продолжения поиска нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Вы ввели недопустимые символы,повторите Ваш ввод");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void reservationRoom() {
        while (true) {
            System.out.println("Виберете отель в которогм вы хотите забронировать комануту: \n");
            int count = 1;
            List<Hotel> hotels = application.getHotels();
            for (Hotel hotel : hotels) {
                System.out.println(count++ + ". " + "Отель \'" + hotel.getHotelName() + "\', город " + hotel.getCityName());
            }
            try {
                System.out.println("\nУкажите номер отеля в котором вы хотите забронировать комнату! Для выхода введите \"0\"!");
                int hotelIndex = Integer.parseInt(buffRead.readLine()) - 1;
                if (hotelIndex == -1) return;
                Hotel hotel = application.getHotels().get(hotelIndex);
                System.out.println("**** Бронирование комнат в отеле " + hotel.getHotelName() + " ****");
                count = 1;
                for (Room room : hotel.getRoomList()) {
                    System.out.println(count++ + ". * " + room);
                }
                System.out.println("Укажите номер комнати которою ви хотите забронировать: ");
                int i = Integer.parseInt(buffRead.readLine());
                System.out.println("Укажите дату по которую выхотите забронировать комнату в формате year.mm.dd: ");
                String date = buffRead.readLine();

                Room room = application.roomReservationByName(hotelIndex, i - 1, date);
                System.out.println("***Комната успешно забронирована с " + room.getAvailableFrom() + " по " + room.getReservBefore());
                System.out.println("\nДля повторного бронирования нажмите 1, в противном случае Вы перейдете в главное меню");
                String answer1 = buffRead.readLine();
                switch (answer1) {
                    case "1":
                        continue;
                    default:
                        return;
                }
            } catch (DateTimeException d) {
                System.out.println("Дата введена не верно, поаторите попытку!");
            } catch (InvalidRoomStatus | InvalidHotelStatus invalidStatus) {
                System.out.println(invalidStatus.getMessage());
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                System.out.println("Не верная операция, повторите попытку!");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidDateFormat invalidDateFormat) {
                System.out.println(invalidDateFormat.getMessage() + "\n");
            }

        }
    }

    private void cancelReservation() {
        while (true) {
            int count = 1;
            List<Room> userRooms = application.curUser().getRoomList();
            System.out.println("Комнаты которые вы забронировали:");
            if (userRooms.isEmpty()) System.out.println("У ВАС НЕТ ЗАБРОНИРОВАНЫХ КОМНАТ!!!");
            for (Room room : userRooms) {
                System.out.println(count++ + ". * " + room);
            }
            try {
                System.out.println("\nВведите номер комнаты с которой вы хотите снять бронь! Для выхода введите \"0\"!\n");
                int roomIndex = Integer.parseInt(buffRead.readLine()) - 1;
                if (roomIndex == -1) return;

                if (application.cancelReservationByName(roomIndex)) {
                    System.out.println("   *** Операция успешная! Бронь отменена!\n");
                    return;
                }
            } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                System.out.println("Не верная операция, повторите попытку!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}