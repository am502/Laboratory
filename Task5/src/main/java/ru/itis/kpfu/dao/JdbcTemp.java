package ru.itis.kpfu.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.kpfu.config.WebAppContext;
import ru.itis.kpfu.model.Credentials;

public class JdbcTemp {
    private WebAppContext webAppContext = new WebAppContext();
    private JdbcTemplate jdbc = new JdbcTemplate(webAppContext.dataSource());

    private RowMapper<Credentials> credMapper(){
        return (resultSet, i) -> {
            Credentials cred = new Credentials(
                    resultSet.getLong("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password")
            );
            return cred;
        };
    }

    public void add(String login, String password) {
        String sql = "INSERT INTO credentials (login, password) VALUES (?, ?)";
        jdbc.update(sql, login, password);
    }

    public Credentials findById(long id){
        String sql = "SELECT * FROM credentials WHERE id = ?";
        Credentials cred = (Credentials) jdbc.queryForObject(sql, new Object[] { id }, credMapper());
        return cred;
    }

    public void update(long id, String login, String password) {
        String sql = "UPDATE credentials SET login = ?, password = ? WHERE id = ?";
        jdbc.update(sql, login, password, id);
    }

    public Credentials getByLogin(String login){
        String sql = "SELECT * FROM credentials WHERE login = ?";
        Credentials cred = (Credentials) jdbc.queryForObject(sql, new Object[] { login }, credMapper());
        return cred;
    }
}
