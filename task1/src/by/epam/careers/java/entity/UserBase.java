package by.epam.careers.java.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserBase implements Serializable {
    private static final long serialVersionUID = 89976271281079345L;

    private List<UserAccount> users;

    {
        users = new ArrayList<UserAccount>();
    }

    public UserBase() {
    }

    public UserBase(List<UserAccount> users) {
        this.users = users;
    }

    public UserAccount getOneUser(int i) {
        return users.get(i);
    }

    public void setOneUser(UserAccount user, int i) {
        users.add(i, user);
    }

    public List<UserAccount> getUsers() {
        return users;
    }

    public void setUsers(List<UserAccount> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBase userBase = (UserBase) o;
        return Objects.equals(users, userBase.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    @Override
    public String toString() {
        return "UserBase{" +
                "users=" + users +
                '}';
    }
}
