package repository;

import model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Find all orders by user ID
    List<Order> findByUserId(Long userId);

    // Find all orders by service provider ID
    List<Order> findByServiceProviderId(Long serviceProviderId);
}
