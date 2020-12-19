package by.epam.careers.java.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserAccount implements Serializable {
    private static final long serialVersionUID = 299645229327017287L;

    private String nickname;
    private String email;
    private String password;


    public UserAccount() {
    }

    public UserAccount(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return Objects.equals(nickname, that.nickname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, email, password);
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "nickname='" + nickname + '\'' +
                ", eMail='" + email + " " + password;
    }
}
