package ru.kpfu.itis.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.repository.BookingsRepository;
import ru.kpfu.itis.service.BookingService;

import java.util.List;

@Service
public class BookingsServiceImpl implements BookingService {

    @Autowired
    BookingsRepository bookingsRepository;

    public Booking addBooking(Booking booking) {
        bookingsRepository.save(booking);
        return booking;
    }
    @Transactional
    public void update(Booking booking) {
        bookingsRepository.save(booking);
    }

    public List<Booking> findAll() {
        return bookingsRepository.findAll();
    }

    public Booking findById(Long id) {
        return bookingsRepository.findOne(id);
    }
}
