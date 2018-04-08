package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.model.Review;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Review, Long> {

}
