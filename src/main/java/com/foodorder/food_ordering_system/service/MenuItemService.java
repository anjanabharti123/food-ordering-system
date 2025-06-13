package com.foodorder.food_ordering_system.service;

import com.foodorder.food_ordering_system.entity.MenuItem;
import com.foodorder.food_ordering_system.entity.Restaurant;
import com.foodorder.food_ordering_system.repository.MenuItemRepository;
import com.foodorder.food_ordering_system.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<MenuItem> getMenuItemsByRestaurant(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    public Optional<MenuItem> getMenuItemById(Long id) {
        return menuItemRepository.findById(id);
    }

    public Optional<MenuItem> createMenuItem(MenuItem menuItem, Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isPresent()) {
            menuItem.setRestaurant(restaurant.get());
            return Optional.of(menuItemRepository.save(menuItem));
        }
        return Optional.empty();
    }

    public Optional<MenuItem> updateMenuItem(Long id, MenuItem updatedMenuItem) {
        Optional<MenuItem> existing = menuItemRepository.findById(id);
        if (existing.isPresent()) {
            MenuItem menuItem = existing.get();
            menuItem.setName(updatedMenuItem.getName());
            menuItem.setPrice(updatedMenuItem.getPrice());
            menuItem.setCategory(updatedMenuItem.getCategory());
            return Optional.of(menuItemRepository.save(menuItem));
        }
        return Optional.empty();
    }

    public boolean deleteMenuItem(Long id) {
        if (menuItemRepository.existsById(id)) {
            menuItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
