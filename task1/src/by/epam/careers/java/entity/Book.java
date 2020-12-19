package by.epam.careers.java.entity;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
    private static final long serialVersionUID = 299980293270169735L;

    private String tittle;
    private String author;
    private int year;
    private int pages;
    private double price;
    private String description;

    public Book() {
    }

    public Book(String tittle, String author, int year, int pages, double price, String description) {
        this.tittle = tittle;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.price = price;
        this.description = description;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year &&
                pages == book.pages &&
                Double.compare(book.price, price) == 0 &&
                Objects.equals(tittle, book.tittle) &&
                Objects.equals(author, book.author) &&
                Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tittle, author, year, pages, price, description);
    }

    @Override
    public String toString() {
        return "Книга " + "'" + tittle + "'" +  ", автор '" + author +
                "', год издания " + year + ", страниц " + pages + ", цена " + price +
                '\n' + description;
    }
}
