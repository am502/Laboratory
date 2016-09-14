package DAO.Model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    public Book mapRow (ResultSet resultSet, int rowNum) throws SQLException {
        Book book = new Book(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("price"));
        return book;
    }
}