package by.epam.careers.java.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/** Задание 2. Блокнот. Разработать консольное приложение, работающее с Заметками в
 * Блокноте. Каждая Заметка это: Заметка(тема, дата создания, e-mail, сообщение).
 * Общие пояснения к практическому заданию.
 *  В начале работы приложения данные должны считываться из файла, в конце работы -
 *  сохраняться в файл.
 *  У пользователя должна быть возможность найти запись по любому параметру или по
 *  группе параметров(группу параметров можно можно определить самостоятельно), получить
 *  требуемые записи в отсортированном виде, найти записи, текстовое поле которой
 *  содержит определённое слово, а также добавить новую запись.
 *  Особое условие: поиск, сравнение и валидицию вводимой информации осуществлять с
 *  использование регулярных выражений.
 *  Особое условие: проверку введённой информации на валидность должен осуществлять код,
 *  непосредственно добавляющий информацию.*/
public class Main {
    private static Logger logger;

    static {
        try {
            FileInputStream fis = new FileInputStream("D:\\IdeaProjects\\6_Tasks\\task2\\src\\by\\epam\\careers\\resources\\log.config");
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
