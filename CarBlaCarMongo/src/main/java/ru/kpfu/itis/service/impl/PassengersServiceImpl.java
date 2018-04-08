package ru.kpfu.itis.service.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.repository.PassengersRepository;
import ru.kpfu.itis.service.PassengersService;

import java.util.List;

@Service
public class PassengersServiceImpl implements PassengersService {

    @Autowired
    PassengersRepository passengersRepository;

    public Passenger addPassenger(Passenger passenger) {
        passengersRepository.save(passenger);
        return passenger;
    }

    public Passenger findById(Long id) {
        return passengersRepository.findOne(id);
    }

    public void update(Passenger passenger) {
        passengersRepository.save(passenger);

    }

    public List<Passenger> findAll() {
        return passengersRepository.findAll();
    }
}
