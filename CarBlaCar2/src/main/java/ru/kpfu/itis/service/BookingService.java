package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Booking;

import java.util.List;

public interface BookingService {
    Booking addBooking(Booking booking);

    void update(Booking booking);

    List<Booking> findAll();

    Booking findById(Long id);

}
