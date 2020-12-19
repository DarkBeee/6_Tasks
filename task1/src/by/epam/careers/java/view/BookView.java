package by.epam.careers.java.view;

import by.epam.careers.java.entity.Book;

import java.util.List;

public class BookView {
    private static final BookView instance = new BookView();

    private BookView() {
    }

    public static BookView getInstance() {
        return instance;
    }

    public void print(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void print(String message, List<Book> books) {
        System.out.println(message);
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
