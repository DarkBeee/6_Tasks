package by.epam.careers.java.entity;

import java.io.Serializable;
import java.util.Objects;

public class AdminAccount extends UserAccount implements Serializable {
    private static final long serialVersionUID = 54035967088392319L;

    private boolean admin = true;

    public AdminAccount() {
    }

    public AdminAccount(String nickname, String email, String password) {
        super(nickname, email, password);
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AdminAccount that = (AdminAccount) o;
        return admin == that.admin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), admin);
    }

    @Override
    public String toString() {
        return "AdminAccount{" +
                "admin=" + admin +
                '}';
    }
}
