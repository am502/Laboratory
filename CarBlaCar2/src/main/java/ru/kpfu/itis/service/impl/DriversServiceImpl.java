package ru.kpfu.itis.service.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.dao.DriversDao;
import ru.kpfu.itis.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.repository.DriversRepository;
import ru.kpfu.itis.service.DriversService;

import java.util.List;

@Service
public class DriversServiceImpl implements DriversService {

    private final DriversRepository driversRepository;

    @Autowired
    public DriversServiceImpl(DriversRepository driversRepository) {
        this.driversRepository = driversRepository;
    }

    public Driver addDriver(Driver driver) {
        driversRepository.save(driver);
        return driver;
    }

    public Driver findById(Long id) {
        return driversRepository.findOne(id);
    }

    public void update(Driver driver) {
        driversRepository.save(driver);
    }

    public List<Driver> findAll() {
        return driversRepository.findAll();
    }

    public List<Driver> getBest() {
        return driversRepository.findTop9ByOrderByRatingDesc();
    }

}
