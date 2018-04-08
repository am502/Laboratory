package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.model.Passenger;

import java.util.List;

public interface PassengersRepository extends JpaRepository<Passenger, Long> {
}
