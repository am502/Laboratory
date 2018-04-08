package ru.kpfu.itis.service;

import ru.kpfu.itis.model.Trip;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.UserRole;

import java.util.List;

public interface TripsService {

    Trip addTrip(Trip trip);

    void update(Trip trip);

    List<Trip> findAll();

    List<Trip> findAllOrderDate();

    List<Trip> findByStatusOrderDate();

    List<Trip> findBySearch(String departure, String destination);

    Trip findById(Long id);

    List<Trip> lastTrips();

    List<Trip> lastEndsTripsForMonth(User user);


}
