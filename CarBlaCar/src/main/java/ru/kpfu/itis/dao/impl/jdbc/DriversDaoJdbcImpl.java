package ru.kpfu.itis.dao.impl.jdbc;

import org.apache.log4j.Logger;
import ru.kpfu.itis.dao.DriversDao;
import ru.kpfu.itis.dao.factory.JDBCConnectionFactory;
import ru.kpfu.itis.dao.impl.hibernate.AutosDaoHibernateImpl;
import ru.kpfu.itis.model.Driver;
import ru.kpfu.itis.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriversDaoJdbcImpl implements DriversDao {

    private static final Logger logger = Logger.getLogger(AutosDaoHibernateImpl.class);

    Connection con = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;

    private Connection getConnection() throws SQLException {
        return JDBCConnectionFactory.getInstance().getConnection();
    }

    @Override
    public void save(Driver driver) {

    }

    @Override
    public Driver findOne(Long id) {
        return null;
    }

    @Override
    public List<Driver> findAll() {
        return null;
    }

    @Override
    public List<Driver> findTop9ByOrderByRatingDesc() {
        List drivers = new ArrayList();
        Driver driver = null;
        try {
            String querystring = "SELECT * FROM drivers NATURAL JOIN users;";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("user_id"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("password"));
                user.setFirstname(rs.getString("firstname"));
                user.setSurname(rs.getString("surname"));
                user.setAvatar(rs.getString("avatar"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));

                driver = new Driver();
                driver.setId(rs.getLong("id"));
                driver.setExperience(rs.getInt("experience"));
                driver.setRating(rs.getInt("rating"));
                driver.setUser(user);
                drivers.add(driver);
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
        return drivers;
    }
}
