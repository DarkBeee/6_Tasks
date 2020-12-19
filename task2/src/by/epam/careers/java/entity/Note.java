package by.epam.careers.java.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Note implements Serializable {
    private static final long serialVersionUID = 36985552444675088L;

    private String theme;
    private LocalDateTime creationDate;
    private String email;
    private String message;

    public Note() {
    }

    public Note(String theme, LocalDateTime creationDate, String email, String message) {
        this.theme = theme;
        this.creationDate = creationDate;
        this.email = email;
        this.message = message;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(theme, note.theme) &&
                Objects.equals(creationDate, note.creationDate) &&
                Objects.equals(email, note.email) &&
                Objects.equals(message, note.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theme, creationDate, email, message);
    }

    @Override
    public String toString() {
        return "Тема: " + theme + ", дата создания: " +
                creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) +
                ", email: " + email + "\nСообщение: " + message;
    }
}
