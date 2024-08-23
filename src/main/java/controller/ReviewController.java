package controller;

import model.Review;
import service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Endpoint to create a review
    @PostMapping("/create")
    public ResponseEntity<?> createReview(@RequestParam Long userId,
                                          @RequestParam Long serviceProviderId,
                                          @RequestParam int rating,
                                          @RequestParam String comment) {
        Review review = reviewService.createReview(userId, serviceProviderId, rating, comment);
        return ResponseEntity.ok(review);
    }

    // Endpoint to get reviews for a specific service provider
    @GetMapping("/provider/{serviceProviderId}")
    public ResponseEntity<?> getReviewsForServiceProvider(@PathVariable Long serviceProviderId) {
        List<Review> reviews = reviewService.getReviewsByServiceProvider(serviceProviderId);
        return ResponseEntity.ok(reviews);
    }

    // Endpoint to get reviews by a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getReviewsByUser(@PathVariable Long userId) {
        List<Review> reviews = reviewService.getReviewsByUser(userId);
        return ResponseEntity.ok(reviews);
    }

    // Endpoint to get a review by its ID
    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getReviewById(@PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(review);
    }

    // Endpoint to delete a review by its ID
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("Review deleted successfully");
    }
}
