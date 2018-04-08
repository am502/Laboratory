package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Driver;

import java.util.List;

@Repository
public interface DriversRepository extends JpaRepository<Driver, Long>{

    List<Driver> findTop9ByOrderByRatingDesc();

}
