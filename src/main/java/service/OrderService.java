package service;

import model.Order;
import model.User;
import model.ServiceProvider;
import repository.OrderRepository;
import repository.UserRepository;
import repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    // Create a new order
    public Order createOrder(Long userId, Long serviceProviderId, String serviceDescription, String status) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<ServiceProvider> serviceProviderOpt = serviceProviderRepository.findById(serviceProviderId);

        if (userOpt.isPresent() && serviceProviderOpt.isPresent()) {
            User user = userOpt.get();
            ServiceProvider serviceProvider = serviceProviderOpt.get();

            Order order = new Order();
            order.setUser(user); // Set the user
            order.setServiceProvider(serviceProvider); // Set the service provider
            order.setServiceDescription(serviceDescription); // Set the service description
            order.setStatus(status); // Set the order status

            return orderRepository.save(order);
        } else {
            throw new RuntimeException("User or Service Provider not found");
        }
    }

    // Update the status of an existing order
    public Order updateOrderStatus(Long orderId, String status) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);

        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus(status);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    // Get all orders for a specific user
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // Get all orders for a specific service provider
    public List<Order> getOrdersByServiceProvider(Long serviceProviderId) {
        return orderRepository.findByServiceProviderId(serviceProviderId);
    }

    // Get a specific order by ID
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
