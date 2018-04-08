package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Review;

import java.util.List;

public interface ReviewsService {

    Review addReview(Review review);

    void update(Review review);

    List<Review> findAll();

    Review findById(Long id);

}
