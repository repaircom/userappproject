package service;

import model.Review;
import model.User;
import model.ServiceProvider;
import repository.ReviewRepository;
import repository.UserRepository;
import repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    // Create a new review
    public Review createReview(Long userId, Long serviceProviderId, int rating, String comment) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<ServiceProvider> serviceProviderOpt = serviceProviderRepository.findById(serviceProviderId);

        if (userOpt.isPresent() && serviceProviderOpt.isPresent()) {
            User user = userOpt.get();
            ServiceProvider serviceProvider = serviceProviderOpt.get();

            Review review = new Review();
            review.setUser(user);
            review.setServiceProvider(serviceProvider);
            review.setRating(rating);
            review.setComment(comment);

            return reviewRepository.save(review);
        } else {
            throw new RuntimeException("User or Service Provider not found");
        }
    }

    // Get all reviews for a specific service provider
    public List<Review> getReviewsByServiceProvider(Long serviceProviderId) {
        return reviewRepository.findByServiceProviderId(serviceProviderId);
    }

    // Get all reviews by a specific user
    public List<Review> getReviewsByUser(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    // Get a specific review by ID
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    // Delete a specific review by ID
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
