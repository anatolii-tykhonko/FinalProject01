package com.firstgroup.project.DAOs;

import com.firstgroup.project.dataBase.DataBase;
import java.io.*;

/**
 * Created by Sonikb on 08.05.2017.
 */
public class DBService {

    private static DataBase dataBase = new DataBase();

    {
        load();
    }

    //    ***** Cервис для работы с "базой данных"

//    * В этом класе есть одна переменная dataBase которая и будет имитировать нашу БД
//    * Метод save сохраняет все изменения которые мы вносим в нащу БД в файл(в нашем случае изменения вносяться когда мы выходим из програмы через ВЫХОД, команда 0)
//    * Метод load выгружает нашу БД в переменную dataBase при каждом запуске программы, если файл MyDB.xml будет пустой при запуске выкинет ексепшн, топому я сделал следующий метод
//    * Метод resetDBToEmpty перезагружает нашу БД в файле до начального состояния(пустой, но со всеми нужными колекциями), этот метод нужно использовать отдельно когда вы тестируете методы, В коде я его нигде не использовал(вызывайте сами вручную)
//    ** Переменная и все методы кроме getDataBase - статические, я подумал что так будет логично, так как нам надо использовать постоянно одну и ту же переменную для загрузки и выгрузки в файл, так же более проще доступаться к методам save, load, resetDBToEmpty
//    ** В классе HotelDAO есть переменная dbService через нее мы и доступаемся к нашей БД(к переменной dataBase)

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