package by.epam.careers.java.logic;

import by.epam.careers.java.dao.TextFileBookDao;
import by.epam.careers.java.entity.Book;
import by.epam.careers.java.entity.BookCatalog;

import java.util.ArrayList;
import java.util.List;

public class BookLogic {
    private static final BookLogic instance = new BookLogic();

    private BookLogic() {}

    public static BookLogic getInstance() {
        return instance;
    }

    public List<Book> searchBookByTittle(List<Book> books, String tittle) {
        List<Book> find = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getTittle().equalsIgnoreCase(tittle.trim())) {
                find.add(book);
            }
        }
        return find;
    }

    public List<Book> searchBookByAuthor(List<Book> books, String author) {
        List<Book> find = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author.trim())) {
                find.add(book);
            }
        }
        return find;
    }

    public List<Book> getBooksFromFile() {
        return TextFileBookDao.getInstance().readAllBooks();
    }
}
