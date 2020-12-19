package by.epam.careers.java.logic;

import by.epam.careers.java.dao.TextFileBookDao;
import by.epam.careers.java.entity.Book;
import by.epam.careers.java.entity.BookCatalog;

import java.util.List;

public class AdminLogic {
    private static final AdminLogic instance = new AdminLogic();

    private AdminLogic() {
    }

    public static AdminLogic getInstance() {
        return instance;
    }

    public BookCatalog removeBook(BookCatalog catalog, String tittle) {
        List<Book> books = BookLogic.getInstance().searchBookByTittle(catalog.getBooks(), tittle);
        for (Book book : books) {
            catalog.getBooks().remove(book);
        }
        TextFileBookDao.getInstance().overwriteBooks(catalog.getBooks());
        return catalog;
    }

    public BookCatalog removeBook(BookCatalog catalog, int i) {
        catalog.getBooks().remove(i);
        TextFileBookDao.getInstance().overwriteBooks(catalog.getBooks());
        return catalog;
    }

    public BookCatalog addBook(BookCatalog catalog, String tittle, String author, int year,
                               int pages, double price, String description) {
        Book book = new Book(tittle, author, year, pages, price, description);
        catalog.setOneBook(book, catalog.getBooks().size() - 1);
        TextFileBookDao.getInstance().writeOneBook(book);
        return catalog;
    }

    public BookCatalog addDescription(BookCatalog catalog, String description, int poz) {
        catalog.getBooks().get(poz).setDescription(description);
        TextFileBookDao.getInstance().overwriteBooks(catalog.getBooks());
        return catalog;
    }
}
