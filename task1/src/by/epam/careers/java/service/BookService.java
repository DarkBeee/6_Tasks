package by.epam.careers.java.service;

import by.epam.careers.java.entity.BookCatalog;
import by.epam.careers.java.logic.BookLogic;

public class BookService {
    private static final BookService instance = new BookService();

    private BookService() {
    }

    public static BookService getInstance() {
        return instance;
    }

    public BookCatalog loadCatalog() {
        return new BookCatalog(BookLogic.getInstance().getBooksFromFile());
    }
}
