package ru.itis.kpfu.model;

public class Credentials {
    private Long id;
    private String login;
    private String password;

    public Credentials(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Credentials(){ }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
