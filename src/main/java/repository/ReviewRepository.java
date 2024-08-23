package repository;

import model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Find reviews by service provider ID
    List<Review> findByServiceProviderId(Long serviceProviderId);

    // Find reviews by user ID
    List<Review> findByUserId(Long userId);
}
