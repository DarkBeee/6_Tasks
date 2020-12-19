package by.epam.careers.java.main;

import by.epam.careers.java.entity.AdminAccount;
import by.epam.careers.java.entity.BookCatalog;
import by.epam.careers.java.entity.UserAccount;
import by.epam.careers.java.entity.UserBase;
import by.epam.careers.java.logic.AdminLogic;
import by.epam.careers.java.logic.UserLogic;
import by.epam.careers.java.service.BookService;
import by.epam.careers.java.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
    private BookCatalog bookCatalog;
    private UserBase userBase;

    public void startProgram() throws IOException {
        bookCatalog = BookService.getInstance().loadCatalog();
        userBase = new UserBase(UserLogic.getInstance().loadUsersFromFile());
        loginMenu();
    }

    public void loginMenu() throws IOException {
        System.out.println("Book Catalog");
        System.out.println("1 - войти в аккаунт");
        System.out.println("2 - зарегистрироваться");
        System.out.println("0 - выход из программы");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        switch (s) {
            case "1" :
                authorizationMenu();
                break;
            case "2" :
                registrationMenu();
                break;
            case "0" :
                return;
        }
        loginMenu();
    }

    private void registrationMenu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("0 - назад");
        System.out.println("Ваш e-mail");
        String email = reader.readLine();
        if (email.equals("0")) {
            return;
        }
        System.out.println("Никнейм");
        String nickname = reader.readLine();
        System.out.println("Пароль");
        String password = reader.readLine();
        UserAccount account = UserService.getInstance().registration(nickname, email, password);
        if (account != null) {
            userBase.getUsers().add(UserService.getInstance().registration(nickname, email, password));
            System.out.println("Аккаунт создан");
            authorizationMenu();
        }
        else {
            System.out.println("Никнейм занят");
            registrationMenu();
        }
    }

    public void authorizationMenu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("0 - назад");
        System.out.println("Введите логин");
        String login = reader.readLine();
        if (login.equals("0")) {
            return;
        }
        System.out.println("Введите пароль");
        String password = reader.readLine();
        UserAccount account = UserService.getInstance().authorization(login, password);
        if (account != null) {
            if (account instanceof AdminAccount) {
                adminMenu(account);
            }
            else {
                userMenu(account);
            }
        } else {
            System.out.println("Не верный логин или пароль");
            authorizationMenu();
        }
    }

    public void userMenu(UserAccount account) throws IOException {
        System.out.println("Добро пажаловать " + account.getNickname());
        System.out.println("1 - просмотреть книги в каталоге");
        System.out.println("2 - искать книги по имени автора");
        System.out.println("3 - искать книги по названию");
        System.out.println("4 - предложить добавить книгу в каталог");
        System.out.println("0 - выход из аккаунта");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        switch (s) {
            case "1" :
                UserService.getInstance().browseBooks(bookCatalog);
                break;
            case "2" :
                System.out.println("Введите автора");
                String author = reader.readLine();
                UserService.getInstance().findByAuthor(bookCatalog, author);
                break;
            case "3" :
                System.out.println("Введите название книги");
                String title = reader.readLine();
                UserService.getInstance().findByTittle(bookCatalog, title);
                break;
            case "4" :
                System.out.println("Введите адресс почты(только google mail)");
                String mail = reader.readLine();
                System.out.println("Введите пароль от вашей почты");
                String password = reader.readLine();
                System.out.println("Введите тему сообщения");
                String theme = reader.readLine();
                System.out.println("Введите сообщение");
                String message = reader.readLine();
                UserService.getInstance().suggestToAddBook(mail, password, theme, message, bookCatalog);
                break;
            case "0" :
                loginMenu();
                return;
        }
        userMenu(account);
    }

    public void adminMenu(UserAccount account) throws IOException {
        System.out.println("Добро пажаловать " + account.getNickname());
        System.out.println("1 - просмотреть книги в каталоге");
        System.out.println("2 - удалить книгу по названию");
        System.out.println("3 - удалить книгу по номеру");
        System.out.println("4 - добавить книгу");
        System.out.println("5 - добавить описание");
        System.out.println("0 - выход из аккаунта");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        switch (s) {
            case "1" :
                UserService.getInstance().browseBooks(bookCatalog);
                break;
            case "2" :
                System.out.println("Введите название книги");
                String tittle = reader.readLine();
                bookCatalog = AdminLogic.getInstance().removeBook(bookCatalog, tittle);
                break;
            case "3" :
                System.out.println("Введите номер книги");
                String i = reader.readLine();
                bookCatalog = AdminLogic.getInstance().removeBook(bookCatalog, Integer.parseInt(i));
                break;
            case "4" :
                System.out.println("Введите название книги");
                String tit = reader.readLine();
                System.out.println("Введите автора");
                String author = reader.readLine();
                System.out.println("Введите год издания");
                int year = Integer.parseInt(reader.readLine());
                System.out.println("Введите количество страниц");
                int pages = Integer.parseInt(reader.readLine());
                System.out.println("Введите цену");
                double price = Double.parseDouble(reader.readLine());
                System.out.println("Введите описание");
                String description = reader.readLine();
                bookCatalog = AdminLogic.getInstance().addBook(bookCatalog, tit, author, year, pages, price, description);
                break;
            case "5":
                System.out.println("Введите позицию книги");
                int poz = Integer.parseInt(reader.readLine());
                System.out.println("Введите описание");
                String descr = reader.readLine();
                bookCatalog = AdminLogic.getInstance().addDescription(bookCatalog, descr, poz);
            case "0" :
                loginMenu();
                return;
        }
        adminMenu(account);
    }
}
