package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Trip;

import java.util.List;

@Repository
public interface TripsRepository extends CrudRepository<Trip, Long>, JpaSpecificationExecutor<Trip> {

    List<Trip> findTop10ByOrderByDateDesc();

    List<Trip> findAllByOrderByDateDesc();

    List<Trip> findByStatusOrderByDateDesc(final String status);

    List<Trip> findByDepartureAndDestinationOrderByDateDesc(String destination, String departure);
}
