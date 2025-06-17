package com.foodorder.food_ordering_system.controller;


import com.foodorder.food_ordering_system.entity.MenuItem;
import com.foodorder.food_ordering_system.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;

    @GetMapping("/restaurants/{restaurantId}/menu")
    public List<MenuItem> getMenuItemsByRestaurant(@PathVariable Long restaurantId) {
        return menuItemService.getMenuItemsByRestaurant(restaurantId);
    }

    @PostMapping("/menu")
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem, @RequestParam Long restaurantId) {
        Optional<MenuItem> created = menuItemService.createMenuItem(menuItem, restaurantId);
        return created.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/menu/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        Optional<MenuItem> updated = menuItemService.updateMenuItem(id, menuItem);
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        if (menuItemService.deleteMenuItem(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
