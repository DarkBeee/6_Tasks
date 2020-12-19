package by.epam.careers.java.server.main;

import by.epam.careers.java.entity.Archive;
import by.epam.careers.java.server.logic.StudentManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Server {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static StudentManager studentManager = new StudentManager();

    private static Logger logger;

    static {
        try {
            FileInputStream fis = new FileInputStream("D:\\IdeaProjects\\6_Tasks\\task3\\src\\by\\epam\\careers\\resources\\log.config");
            LogManager.getLogManager().readConfiguration(fis);
            logger = Logger.getLogger(Server.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        int port = 1777;

        try {
            logger.log(Level.INFO, "Создание ServerSocket, port 1777");
            server = new ServerSocket(1777);
            while (true) {
                logger.log(Level.INFO, "Ожидание поключения");
                clientSocket = server.accept();
                logger.log(Level.INFO, "Начало работы с клиентом");
                try (Socket localSocket = clientSocket;
                     ObjectInputStream inputStream = new ObjectInputStream(localSocket.getInputStream());
                     ObjectOutputStream outputStream = new ObjectOutputStream(localSocket.getOutputStream())) {
                    String s = inputStream.readObject().toString();
                    if (s.equals("1")) {
                        outputStream.writeObject(new Archive(studentManager.readStudentsFromFile()));
                    }
                    else if (s.equals("0")){
                        Archive archive = (Archive) inputStream.readObject();
                        System.out.println(archive.toString());
                        studentManager.writeStudentsOnFile(archive.getStudents());
                    }
                } catch (IOException e) {
                    logger.log(Level.WARNING, "IOException", e);
                } catch (ClassNotFoundException e) {
                    logger.log(Level.WARNING, "Ошибка при приведении типов", e);
                }
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "IOException", e);
        }
    }
}