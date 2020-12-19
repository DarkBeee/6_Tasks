package by.epam.careers.java.dao;

import by.epam.careers.java.entity.Book;

import java.io.IOException;
import java.util.List;

public interface BookDao {

    public List<Book> readAllBooks() throws IOException;

    public void writeAllBook(List<Book> books) throws IOException;

    public Book readOneBook(int lineNumber) throws IOException;

    public void writeOneBook(Book book) throws IOException;

    public List<Book> findBookByTittle(String tittle) throws IOException;

    public void overwriteBooks(List<Book> books) throws IOException;

}
