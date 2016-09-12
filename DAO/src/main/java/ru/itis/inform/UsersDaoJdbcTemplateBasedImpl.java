package ru.itis.inform;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class UsersDaoJdbcTemplateBasedImpl implements UsersDao {
    private ArrayList<User> users;
    private String tableName;
    private String url = "jdbc:postgresql://127.0.0.1:5432/usersdao";
    private String username = "postgres";
    private String password = "postgres";

    DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
    JdbcTemplate jdbc = new JdbcTemplate(dataSource);

    public UsersDaoJdbcTemplateBasedImpl(String tableName){
        users = new ArrayList<User>();
        this.tableName = tableName;
        jdbc.execute("DROP TABLE IF EXISTS " + tableName + ";");
        jdbc.execute("CREATE TABLE " + tableName + " ( id INT, name VARCHAR(10));");
    }

    public void save(User user) {
        users.add(user);
        jdbc.update("INSERT INTO users VALUES (" + user.getId() + ", '" + user.getName() + "');");
    }

    public User find(int id) {
        List<User> results = jdbc.query("SELECT * FROM " + tableName + " WHERE id = ?", new Object[] {id}, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException{
                return new User(rs.getInt("id"), rs.getString("name"));
            }
        });
        for(User user : results){
            System.out.println(user.toString());
        }

        return users.get(id);
    }

    public ArrayList<User> findAll() {
        List<User> results = jdbc.query("SELECT * FROM " + tableName + ";", new Object[] {}, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException{
                return new User(rs.getInt("id"), rs.getString("name"));
            }
        });
        for(User user : results){
            System.out.println(user.toString());
        }

        return users;
    }
}
