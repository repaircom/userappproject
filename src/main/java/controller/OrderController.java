package controller;

import model.Order;
import service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Endpoint to create a new order
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestParam Long userId,
                                         @RequestParam Long serviceProviderId,
                                         @RequestParam String serviceDescription,
                                         @RequestParam String status) {
        Order order = orderService.createOrder(userId, serviceProviderId, serviceDescription, status);
        return ResponseEntity.ok(order);
    }

    // Endpoint to get orders by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getOrdersByUser(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }

    // Endpoint to get orders by service provider
    @GetMapping("/provider/{serviceProviderId}")
    public ResponseEntity<?> getOrdersByServiceProvider(@PathVariable Long serviceProviderId) {
        List<Order> orders = orderService.getOrdersByServiceProvider(serviceProviderId);
        return ResponseEntity.ok(orders);
    }

    // Endpoint to update order status
    @PutMapping("/update/{orderId}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long orderId,
                                               @RequestParam String status) {
        Order order = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(order);
    }
}
