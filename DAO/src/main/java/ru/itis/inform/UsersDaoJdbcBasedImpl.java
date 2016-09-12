package ru.itis.inform;

import java.sql.*;
import java.util.*;

public class UsersDaoJdbcBasedImpl implements UsersDao {
    private String tablename;
    private ArrayList<User> users;
    private Connection connection;
    private String url;
    private String name;
    private String password;

    public UsersDaoJdbcBasedImpl(String tablename){
        users = new ArrayList<User>();
        connection = null;
        url = "jdbc:postgresql://127.0.0.1:5432/usersdao";
        name = "postgres";
        password = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS " + tablename + ";" + "CREATE TABLE " + tablename + "( id INT, name VARCHAR(10));");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void save(User user) {
        users.add(user);
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO users VALUES (" + user.getId() + ", '" + user.getName() + "');");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public User find(int id) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users WHERE id = " + id + ";");

            while (result.next()) {
                System.out.println(result.getInt("id") + " " + result.getString("name"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return users.get(id);
    }

    public ArrayList<User> findAll() {
        return users;
    }
}