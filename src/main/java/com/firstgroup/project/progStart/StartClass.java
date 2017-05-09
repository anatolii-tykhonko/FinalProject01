package com.firstgroup.project.progStart;

import com.firstgroup.project.consoleHelper.ConsoleHelper;

/**
 * Created by Serega Shevchenko on 07.05.2017.
 * Victor Figurskiy on 07.05.2017.
 * Dmytro Sharyi on 07.05.2017.
 * Nazar Yanovets on 07.05.2017.
 * Anatoliy Tihonko on 07.05.2017.
 */

/**
 * В данной программе реализовано разделение функционала программы на два меню доступа к функциям!
 * Вход в меню пользователя осуществляется при обычной регистрации.
 * Для входа через меню администратора с расширенным функционалом необходимо зарегестрировать пользователя со специальным паролем,
 * в данный момент пароль для такого входа "admin".
 */
public class StartClass {
    public static void main(String[] args) {
        ConsoleHelper consoleHelper = new ConsoleHelper();
        consoleHelper.loginService();
    }
}
