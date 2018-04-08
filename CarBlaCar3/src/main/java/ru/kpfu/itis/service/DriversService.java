package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Driver;

import java.util.List;

public interface DriversService {
    Driver addDriver(Driver driver);

    Driver findById(Long id);

    void update(Driver driver);

    List<Driver> findAll();

    List<Driver> getBest();

}
