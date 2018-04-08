package ru.itis.kpfu.dao;


import ru.itis.kpfu.model.Credentials;

import java.util.List;

public interface CredentialsDao {

    public void add(Credentials credentials);

    public void update(Credentials credentials);

    public void delete(Long id);

    public List findAll();

    public Credentials findByPrimaryKey(Long id) ;
}
