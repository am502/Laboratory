package ru.kpfu.itis.dao.impl.jdbc;

import org.apache.log4j.Logger;
import ru.kpfu.itis.dao.UsersDao;
import ru.kpfu.itis.dao.factory.JDBCConnectionFactory;
import ru.kpfu.itis.dao.impl.hibernate.AutosDaoHibernateImpl;
import ru.kpfu.itis.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbcImpl implements UsersDao {

    private static final Logger logger = Logger.getLogger(AutosDaoHibernateImpl.class);

    Connection con = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;

    private Connection getConnection() throws SQLException {
        return JDBCConnectionFactory.getInstance().getConnection();
    }

    @Override
    public void save(User user) {
        try {
            String querystring = "INSERT INTO users (nickname, password, firstname, surname, avatar, email, role) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            ptmt.setString(1, user.getNickname());
            ptmt.setString(2, user.getPassword());
            ptmt.setString(3, user.getFirstname());
            ptmt.setString(4, user.getSurname());
            ptmt.setString(5, user.getAvatar());
            ptmt.setString(6, user.getEmail());
            ptmt.setString(7, user.getRole());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ptmt != null)
                    ptmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User findOne(Long userId) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByNickname(String nickname) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findByNicknameIgnoreCase(String name) {
        name = name.toUpperCase();
        User user = null;
        try {
            String querystring = "SELECT * FROM users WHERE UPPER(nickname) = ?;";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            ptmt.setString(1, name);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("password"));
                user.setFirstname(rs.getString("firstname"));
                user.setSurname(rs.getString("surname"));
                user.setAvatar(rs.getString("avatar"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ptmt != null)
                    ptmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User findByEmailIgnoreCase(String email) {
        return null;
    }
}
