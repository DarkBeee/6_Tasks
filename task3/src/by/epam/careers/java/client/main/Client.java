package by.epam.careers.java.client.main;

import by.epam.careers.java.entity.Archive;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Client {
    private static Socket clientSocket;
    private static ObjectInputStream inputStream;
    private static ObjectOutputStream outputStream;

    private static Logger logger;

    static {
        try {
            FileInputStream fis = new FileInputStream("D:\\IdeaProjects\\6_Tasks\\task3\\src\\by\\epam\\careers\\resources\\log.config");
            LogManager.getLogManager().readConfiguration(fis);
            logger = Logger.getLogger(Client.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Archive getStudentFromServer() {
        try {
            logger.log(Level.INFO, "Установка соединения с сервером");
            clientSocket = new Socket("127.0.0.1", 1777);
            logger.log(Level.INFO, "Начало работы с сервером");
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            outputStream.writeObject("1");
            logger.log(Level.INFO, "Чтение данных от сервера");
            Archive archive = (Archive) inputStream.readObject();
            inputStream.close();
            clientSocket.close();
            return archive;
        } catch (IOException e) {
            logger.log(Level.WARNING, "Ошибка при работе с сервером", e);
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, "Ошибка при приведении типов", e);
        }
        return null;
    }

    public void sendStudentToServer(Archive archive) {
        try {
            logger.log(Level.INFO, "Установка соединения с сервером");
            clientSocket = new Socket("127.0.0.1", 1777);
            logger.log(Level.INFO, "Начало работы с сервером");
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            outputStream.writeObject("0");
            outputStream.writeObject(archive);
            outputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Ошибка при работе с сервером", e);
        }
    }
}
