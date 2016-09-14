package DAO;

import DAO.Model.Book;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface BookDao {
    void addBook(Book book) throws SQLException;
    void updateBook(int id, int newPrice) throws SQLException;
    void deleteBook(int id) throws SQLException;
    void addAll();
    Book getBook(int id);
    List<Book> showBooks() throws SQLException;
}