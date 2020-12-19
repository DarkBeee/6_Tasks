package by.epam.careers.java.main;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**Задание 1: создать консольное приложение "Учёт книг в домашней библиотеке".
 * Общие требования к заданию:
 *  Система учитывает книги как в электронном, так и в бумажном варианте.
 *  Существующие роли: пользователь, администратор.
 *  Пользователь может просматривать книги в каталоге книг, осуществлять поиск книг
 *  в каталоге.
 *  Администратор может модифицировать каталог.
 *  При добавления описания книги в каталог оповещение о ней рассылается на e-mail
 *  всем пользователям.
 *  При просмотре каталога желательно реализовать постраничный просмотр.
 *  Пользователь может предложить добавить книгу в библиотеку, переслав ее
 *  администратору на e-mail.
 *  Каталог книг хранится в текстовом файле.
 *  Данные аутенфикации пользователей хранятся в текстовом файле. Пароль не хранится
 *  в открытом виде.*/
public class Main {
    private static Logger logger;

    static {
        try {
            FileInputStream fis = new FileInputStream("C:/Users/Владислав/IdeaProjects/6_Tasks/task1/src/by/epam/careers/resources/log.config");
            LogManager.getLogManager().readConfiguration(fis);
            logger = Logger.getLogger(Main.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Console console = new Console();
        try {
            logger.log(Level.INFO, "Ввод пользователя");
            console.startProgram();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Ошибка при вводе пользователем", e);
        }
    }
}
