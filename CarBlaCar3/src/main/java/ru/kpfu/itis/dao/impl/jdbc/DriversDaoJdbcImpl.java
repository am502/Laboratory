package ru.kpfu.itis.dao.impl.jdbc;

import ru.kpfu.itis.dao.DriversDao;
import ru.kpfu.itis.model.Driver;

import java.util.List;

public class DriversDaoJdbcImpl implements DriversDao {
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
        return null;
    }
}
