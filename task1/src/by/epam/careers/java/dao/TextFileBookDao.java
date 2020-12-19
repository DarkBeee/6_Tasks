package by.epam.careers.java.dao;

import by.epam.careers.java.entity.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TextFileBookDao implements BookDao {
    private static final TextFileBookDao instance = new TextFileBookDao();

    private static Logger logger;

    static {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Владислав\\IdeaProjects\\6_Tasks\\task1\\src\\by\\epam\\careers\\resources\\log.config");
            LogManager.getLogManager().readConfiguration(fis);
            logger = Logger.getLogger(TextFileBookDao.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String fileLocation = "C:\\Users\\Владислав\\IdeaProjects\\6_Tasks\\task1\\src\\by\\epam\\careers\\resources\\books.txt";

    private TextFileBookDao() {
    }

    public static TextFileBookDao getInstance() {
        return instance;
    }

    @Override
    public List<Book> readAllBooks() {
        List<Book> books = new ArrayList<Book>();
        try {
            logger.log(Level.INFO, "Чтение данных книг");
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
            String line = reader.readLine();
            while (line != null) {
                String[] book = line.split("; ");
                books.add(new Book(book[0], book[1], Integer.parseInt(book[2]),
                        Integer.parseInt(book[3]), Double.parseDouble(book[4]), book[5]));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Ошибка при чтении данных книг", e);
        }
        return books;
    }

    @Override
    public void writeAllBook(List<Book> books) {
        try {
            logger.log(Level.INFO, "Запись данных книг");
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation, true));
            for (Book book : books) {
                writer.write(book.getTittle() + "; " + book.getAuthor() + "; " + book.getYear() + "; "
                        + book.getPages() + "; " + book.getPrice() + "; " + book.getDescription() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Ошибка при записи данных книг", e);
        }
    }

    @Override
    public Book readOneBook(int lineNumber) {
        Book book = null;
        try {
            logger.log(Level.INFO, "Чтение данных книги");
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
            String line = reader.readLine();
            int i = 1;
            while (i < lineNumber) {
                line = reader.readLine();
                i++;
            }
            String[] reading = line.split("; ");
            book = new Book(reading[0], reading[1], Integer.parseInt(reading[2]),
                    Integer.parseInt(reading[3]), Double.parseDouble(reading[4]), reading[5]);
            reader.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Ошибка при чтении данных книги", e);
        }
        return book;
    }

    @Override
    public void writeOneBook(Book book) {
        try {
            logger.log(Level.INFO, "Запись данных книги");
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation, true));
            writer.write(book.getTittle() + "; " + book.getAuthor() + "; " + book.getYear() + "; "
                    + book.getPages() + "; " + book.getPrice() + "; " + book.getDescription() + '\n');
            writer.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Ошибка при записи данных книги", e);
        }
    }

    @Override
    public List<Book> findBookByTittle(String tittle) {
        List<Book> books = new ArrayList<Book>();
        try {
            logger.log(Level.INFO, "Чтение данных книг");
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
            String line = reader.readLine();
            while (line != null) {
                String[] reading = line.split("; ");
                if (reading[0].trim().equalsIgnoreCase(tittle.trim())) {
                    books.add(new Book(reading[0], reading[1], Integer.parseInt(reading[2]),
                            Integer.parseInt(reading[3]), Double.parseDouble(reading[4]), reading[5]));
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Ошибка при чтении данных книг", e);
        }
        return books;
    }

    @Override
    public void overwriteBooks(List<Book> books) {
        try {
            logger.log(Level.INFO, "Запись данных книг");
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation));
            for (Book book : books) {
                writer.write(book.getTittle() + "; " + book.getAuthor() + "; " + book.getYear() +
                        "; " + book.getPages() + "; " + book.getPrice() + "; " + book.getDescription() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Ошибка при записи данных книги", e);
        }
    }
}
