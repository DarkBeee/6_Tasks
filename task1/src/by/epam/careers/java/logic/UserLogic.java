package by.epam.careers.java.logic;

import by.epam.careers.java.dao.TextFileUsersDao;
import by.epam.careers.java.entity.UserAccount;

import java.util.List;

public class UserLogic {
    private static final UserLogic instance = new UserLogic();

    private UserLogic() {
    }

    public static UserLogic getInstance() {
        return instance;
    }

    public UserAccount signIn(String login, String password) {
        UserAccount account = TextFileUsersDao.getInstance().findAccountByNickname(login);
        if (account != null && account.getNickname().equals(login)) {
            if (account.getPassword().equals(password)) {
                return account;
            }
        }
        return null;
    }

    public UserAccount createAnAccount(String nickname, String email, String password) {
        UserAccount account = TextFileUsersDao.getInstance().findAccountByNickname(nickname);
        if (account != null && account.getNickname().equals(nickname)) {
            return null;
        }
        UserAccount newUser = new UserAccount(nickname, email, password);
        TextFileUsersDao.getInstance().writeAccountData(newUser);
        return newUser;
    }

    public UserAccount searchAccountByNickname(List<UserAccount> accounts, String nickname) {
        UserAccount acc = new UserAccount();
        for (UserAccount account : accounts) {
            if (account.getNickname().equals(nickname)) {
                acc = new UserAccount(account.getNickname(), account.getEmail(), account.getPassword());
            }
        }
        return acc;
    }

    public List<UserAccount> loadUsersFromFile() {
        return TextFileUsersDao.getInstance().readAccountsData();
    }
}
