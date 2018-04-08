package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Passenger;

import java.util.List;

public interface PassengersService {
    Passenger addPassenger(Passenger passenger);

    Passenger findById(Long id);

    void update(Passenger passenger);

    List<Passenger> findAll();
}
