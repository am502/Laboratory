package ru.kpfu.itis.service.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.repository.TripsRepository;
import ru.kpfu.itis.service.TripsService;

import java.util.List;

@Service
public class TripsServiceImpl implements TripsService {

    @Autowired
    TripsRepository tripsRepository;

    public Trip addTrip(Trip trip) {
        tripsRepository.save(trip);
        return trip;
    }

    public void update(Trip trip) {
        tripsRepository.save(trip);
    }

    public List<Trip> findAll() {
        return tripsRepository.findAll();
    }

    public List<Trip> findAllOrderDate() {
        return tripsRepository.findAllByOrderByDateDesc();
    }

    public List<Trip> findByStatusOrderDate() {
        return tripsRepository.findByStatusOrderByDateDesc("Ожидание");
    }

    public List<Trip> findBySearch(String departure, String destination) {
        return tripsRepository.findByDepartureAndDestinationOrderByDateDesc(departure, destination);
    }

    public Trip findById(Long id) {
        return tripsRepository.findOne(id);
    }

    public List<Trip> lastTrips() {
        return tripsRepository.findTop10ByOrderByDateDesc();
    }
}
