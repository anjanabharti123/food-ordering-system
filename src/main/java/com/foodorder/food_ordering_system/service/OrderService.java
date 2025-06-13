package com.foodorder.food_ordering_system.service;

import com.foodorder.food_ordering_system.entity.Order;
import com.foodorder.food_ordering_system.entity.OrderItem;
import com.foodorder.food_ordering_system.entity.Restaurant;
import com.foodorder.food_ordering_system.entity.User;
import com.foodorder.food_ordering_system.repository.OrderRepository;
import com.foodorder.food_ordering_system.repository.RestaurantRepository;
import com.foodorder.food_ordering_system.repository.UserRepository;
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
    private RestaurantRepository restaurantRepository;

    public Optional<Order> createOrder(Order order, Long userId, Long restaurantId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (user.isPresent() && restaurant.isPresent()) {
            order.setUser(user.get());
            order.setRestaurant(restaurant.get());
            return Optional.of(orderRepository.save(order));
        }
        return Optional.empty();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Optional<Order> updateOrderStatus(Long id, Order.Status status) {
        Optional<Order> existing = orderRepository.findById(id);
        if (existing.isPresent()) {
            Order order = existing.get();
            order.setStatus(status);
            return Optional.of(orderRepository.save(order));
        }
        return Optional.empty();
    }
}
