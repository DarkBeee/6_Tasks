package by.epam.careers.java.dao;

import by.epam.careers.java.entity.UserAccount;

import java.io.IOException;
import java.util.List;

public interface UserDao {

    public void writeAccountData(UserAccount account) throws IOException;

    public UserAccount findAccountByNickname(String nickname);

    public List<UserAccount> readAccountsData();

    public void writeAccountsData(List<UserAccount> accounts);

    public void overwriteAccountsData(List<UserAccount> accounts);
}
