package ru.kpfu.itis.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.model.Trip;

import java.util.List;

public interface TripsRepository extends CrudRepository<Trip, Long>, JpaSpecificationExecutor<Trip> {

    List<Trip> findTop10ByOrderByDateDesc();

    List<Trip> findAllByOrderByDateDesc();

    List<Trip> findByStatusOrderByDateDesc(final String status);

    List<Trip> findByDepartureAndDestinationOrderByDateDesc(String destination, String departure);
}
