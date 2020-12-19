package by.epam.careers.java.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookCatalog implements Serializable {
    private static final long serialVersionUID = 379646065728528471L;

    private String email = "homelibrarystore@gmail.com";
    private String password = "2RkD1udAGuEC7KSL6xts";
    private List<Book> books;

    {
        books = new ArrayList<Book>();
    }

    public BookCatalog() {
    }

    public BookCatalog(List<Book> books) {
        this.books = books;
    }

    public BookCatalog(String email, List<Book> books) {
        this.email = email;
        this.books = books;
    }

    public Book getOneBook(int i) {
        return books.get(i);
    }

    public void setOneBook(Book book, int i) {
        books.add(i, book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCatalog that = (BookCatalog) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(books, that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, books);
    }

    @Override
    public String toString() {
        return "BookCatalog{" +
                "email='" + email + '\'' +
                ", books=" + books +
                '}';
    }
}
