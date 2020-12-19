package by.epam.careers.java.dao;

import by.epam.careers.java.entity.AdminAccount;
import by.epam.careers.java.entity.UserAccount;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TextFileUsersDao implements UserDao {
    private static final TextFileUsersDao instance = new TextFileUsersDao();

    private static Logger logger;

    static {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Владислав\\IdeaProjects\\6_Tasks\\task1\\src\\by\\epam\\careers\\resources\\log.config");
            LogManager.getLogManager().readConfiguration(fis);
            logger = Logger.getLogger(TextFileUsersDao.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String fileLocation = "C:\\Users\\Владислав\\IdeaProjects\\6_Tasks\\task1\\src\\by\\epam\\careers\\resources\\user_data.txt";

    private TextFileUsersDao() {
    }

    public static TextFileUsersDao getInstance() {
        return instance;
    }


    public void writeAccountData(UserAccount account) {
        try {
            logger.log(Level.INFO, "Запись данных пользователя");
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation, true));
            if (account instanceof AdminAccount) {
                writer.write(account.getNickname() + "; " + account.getEmail() + "; " +
                        account.getPassword() + "; " + ((AdminAccount) account).isAdmin() + '\n');
            }
            else {
                writer.write(account.getNickname() + "; " + account.getEmail() + "; " +
                        account.getPassword() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Ошибка при записи данных пользователя", e);
        }
    }

    public UserAccount findAccountByNickname(String nickname) {
        UserAccount account = null;
        try {
            logger.log(Level.INFO, "Чтение данных пользователя");
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
            String line = reader.readLine();
            while (line != null) {
                String[] reading = line.split("; ");
                if (reading[0].trim().equals(nickname.trim())) {
                    if (reading.length == 3) {
                        account = new UserAccount(reading[0], reading[1], reading[2]);
                    }
                    else {
                        account = new AdminAccount(reading[0], reading[1], reading[2]);
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Ошибка при чтении данных пользователя", e);
        }
        return account;
    }

    public List<UserAccount> readAccountsData() {
        List<UserAccount> accounts = new ArrayList<UserAccount>();
        try {
            logger.log(Level.INFO, "Чтение данных пользователей");
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
            String line = reader.readLine();
            while (line != null) {
                String[] reading = line.split("; ");
                if (reading.length == 3) {
                    accounts.add(new UserAccount(reading[0], reading[1], reading[2]));
                }
                else {
                    accounts.add(new AdminAccount(reading[0], reading[1], reading[2]));
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Ошибка при чтении данных пользователей", e);
        }
        return accounts;
    }

    public void writeAccountsData(List<UserAccount> accounts) {
        try {
            logger.log(Level.INFO, "Запись данных пользователей");
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation, true));
            for (UserAccount account : accounts) {
                if (account instanceof AdminAccount) {
                    writer.write(account.getNickname() + "; " + account.getEmail() + "; " +
                            account.getPassword() + "; " + ((AdminAccount) account).isAdmin() + '\n');
                }
                else {
                    writer.write(account.getNickname() + "; " + account.getEmail() + "; " +
                            account.getPassword() + '\n');
                }
            }
            writer.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Ошибка при записи данных пользователей", e);
        }
    }

    public void overwriteAccountsData(List<UserAccount> accounts) {
        try {
            logger.log(Level.INFO, "Запись данных пользователей");
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation));
            for (UserAccount account : accounts) {
                if (account instanceof AdminAccount) {
                    writer.write(account.getNickname() + "; " + account.getEmail() + "; " +
                            account.getPassword() + "; " + ((AdminAccount) account).isAdmin() + '\n');
                }
                else {
                    writer.write(account.getNickname() + "; " + account.getEmail() + "; " +
                            account.getPassword() + '\n');
                }
            }
            writer.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Ошибка при записи данных пользователей", e);
        }
    }
}
