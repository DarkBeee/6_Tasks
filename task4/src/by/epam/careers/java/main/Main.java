package by.epam.careers.java.main;

import by.epam.careers.java.logic.Pier;

/**Задание 4. Многопоточность. Порт. Корабли заходят в порт для
 * разгрузки/загрузки контейнеров. Число контейнеров, находящихся в текущий
 * момент в порту и на корабле, должно быть неотрицательным и превышающим
 * заданную грузоподъёмность судна и вместимость порта. В порту работает
 * несколько причалов. У одного причала может стоять один корабль.
 * Корабль может загружаться у причала или разгружаться.*/
public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Pier());
        Thread thread2 = new Thread(new Pier());
        Thread thread3 = new Thread(new Pier());

        thread1.start();
        thread2.start();
        thread3.start();
    }
}




