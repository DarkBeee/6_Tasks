package by.epam.careers.java.client.main;

import by.epam.careers.java.client.giu.StudentFrame;

/** Задание 3: создайте клиент-серверное приложение "Архив".
 * Общие требования к заданию:
 *  В архиве хранятся Дела(например, студентов). Архив находится на сервере.
 *  Клиент, в зависимости от прав, может запросить дело на пересмотр, внести в него изменения,
 *  или создать новое дело.
 * Требования к коду лабораторной работы:
 *  Для реализации сетевого соединения используйте сокеты.
 *  Формат хранения данных на сервере - xml-файлы.*/
public class Main {
    public static void main(String[] args) {
        StudentFrame frame = new StudentFrame();
        frame.setVisible(true);
    }
}
