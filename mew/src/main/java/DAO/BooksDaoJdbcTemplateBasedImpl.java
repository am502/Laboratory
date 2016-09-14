package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import DAO.Model.Book;
import DAO.Model.BookMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class BooksDaoJdbcTemplateBasedImpl implements BookDao  {
    private List<Book> bookList = new LinkedList<>();
    private String tableName = "books";
    private String url = "jdbc:postgresql://127.0.0.1:5432/usersdao";
    private String username = "postgres";
    private String password = "postgres";
    private DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
    private JdbcTemplate jdbc = new JdbcTemplate(dataSource);

    public void addAll(){
        bookList = showBooks();
    }

    public void addBook(Book book) {
        jdbc.update("INSERT INTO " + tableName + " VALUES (" + book.getId() + ", '" + book.getName() + "', " + book.getPrice() + ");");
        bookList.add(book);
    }

    public void deleteBook(int id){
        jdbc.update("DELETE FROM " + tableName + " WHERE id = ?", new Object[] { id });
        bookList.remove(id);
    }

    public void updateBook(int curid ,int newPrice){
        jdbc.update("UPDATE " + tableName + " SET price = " + newPrice + " WHERE id = ?", new Object[] {curid});
        bookList.get(curid).setPrice(newPrice);
    }

    public Book getBook(int id){
        return bookList.get(id);
    }

    public List<Book> showBooks() {
        List<Book> books = jdbc.query("SELECT * FROM " + tableName + ";", new BookMapper());
        return books;
    }
}