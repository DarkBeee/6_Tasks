package by.epam.careers.java.service;

import by.epam.careers.java.entity.Book;
import by.epam.careers.java.entity.BookCatalog;
import by.epam.careers.java.entity.UserAccount;
import by.epam.careers.java.logic.BookLogic;
import by.epam.careers.java.logic.EmailSender;
import by.epam.careers.java.logic.PasswordEncryption;
import by.epam.careers.java.logic.UserLogic;
import by.epam.careers.java.view.BookView;

import java.util.List;

public class UserService {
    private static final UserService instance = new UserService();

    private UserLogic userLogic = UserLogic.getInstance();
    private BookLogic bookLogic = BookLogic.getInstance();
    private EmailSender emailSender = EmailSender.getInstance();
    private PasswordEncryption encryption = PasswordEncryption.getInstance();
    private BookView view = BookView.getInstance();

    private UserService() {
    }

    public static UserService getInstance() {
        return instance;
    }

    public UserAccount registration(String nickname, String email, String password) {
        return userLogic.createAnAccount(nickname, email, encryption.encryptPassword(password));
    }

    public UserAccount authorization(String login, String password) {
        return userLogic.signIn(login, encryption.encryptPassword(password));
    }

    public void browseBooks(BookCatalog catalog) {
        view.print(catalog.getBooks());
    }

    public void suggestToAddBook(String from, String password,
                                 String theme, String message, BookCatalog catalog) {
        emailSender.sendBookToLibrary(from, password, theme, message, catalog.getEmail());
    }

    public void findByTittle(BookCatalog catalog, String tittle) {
        List<Book> books = bookLogic.searchBookByTittle(catalog.getBooks(), tittle);
        view.print("Книги с названием " + tittle, books);
    }

    public void findByAuthor(BookCatalog catalog, String author) {
        List<Book> books = bookLogic.searchBookByAuthor(catalog.getBooks(), author);
        view.print("Книги автора " + author, books);
    }
}
