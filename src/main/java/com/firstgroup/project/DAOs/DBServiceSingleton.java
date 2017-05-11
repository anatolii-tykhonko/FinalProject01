package com.firstgroup.project.DAOs;

import com.firstgroup.project.dataBase.DataBase;

import java.io.*;

/**
 * Created by Sonikb on 08.05.2017.
 */
public final class DBServiceSingleton {

    private static DBServiceSingleton dbServiceSingleton;
    private DataBase dataBase;

    {
        load();
    }

    public static DBServiceSingleton getDBServiceInstance(){
        if(dbServiceSingleton==null) new DBServiceSingleton(new DataBase());
        return dbServiceSingleton;
    }

    private DBServiceSingleton(DataBase dataBase) {
        this.dataBase = dataBase;
    }




    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/java/com/firstgroup/project/dataBase/MyDB.xml"))) {

            oos.writeObject(dataBase);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/java/com/firstgroup/project/dataBase/MyDB.xml"))) {

            dataBase = (DataBase) ois.readObject();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetDBToEmpty() {
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