package ru.kpfu.itis.service.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.repository.ReviewsRepository;
import ru.kpfu.itis.service.ReviewsService;

import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {

    @Autowired
    ReviewsRepository reviewsRepository;

    public Review addReview(Review review) {
        reviewsRepository.save(review);
        return review;
    }

    public void update(Review review) {
        reviewsRepository.save(review);
    }

    public List<Review> findAll() {
        return reviewsRepository.findAll();
    }

    public Review findById(Long id) {
        return reviewsRepository.findOne(id);
    }
}
