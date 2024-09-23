package com.twisty.food.controller;

import com.twisty.food.dto.RestaurantDto;
import com.twisty.food.entity.Restaurant;
import com.twisty.food.entity.User;
import com.twisty.food.request.ResturantRequestResponce;
import com.twisty.food.service.ResturantService;
import com.twisty.food.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {
     @Autowired
     private ResturantService service;
     @Autowired
     private UserServiceImpl userService;

     @PostMapping("/addRestaurants")
     public ResponseEntity<Restaurant> createRestaurant(@RequestBody ResturantRequestResponce req, User user) throws Exception {
         Restaurant restaurant = service.CreateResturant(req, user);
         return ResponseEntity.ok(restaurant);
     }
     @PutMapping("/updateRestaurant/{id}")
     public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long restaurantId,
                                                        @RequestBody ResturantRequestResponce updateResturantRequest) throws Exception {
         Restaurant restaurant = service.UpdateResturantById(restaurantId, updateResturantRequest);
         return ResponseEntity.ok(restaurant);
     }

     @DeleteMapping("/deleteRestaurant/{id}")
     public ResponseEntity<String> deleteRestaurant(@PathVariable Long restaurantId) throws Exception {
         service.DeleteRestaurantById(restaurantId);
         return new ResponseEntity<>(" Restaurant deleted successfully", HttpStatus.NO_CONTENT);
     }

     @GetMapping("/getRestaurant/{id}")
     public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long restaurantId) throws Exception {
         Restaurant restaurant = service.getRestaurantById(restaurantId);
         return ResponseEntity.ok(restaurant);
     }

     @GetMapping("/getAllRestaurants")
     public ResponseEntity<Iterable<Restaurant>> getAllRestaurants() throws Exception {
         Iterable<Restaurant> restaurants = service.getAllRestaurants();
         return ResponseEntity.ok(restaurants);
     }

     @GetMapping("/searchRestaurants/{userId}")
     public ResponseEntity<Iterable<Restaurant>> searchRestaurants(@PathVariable String query) throws Exception {
         Iterable<Restaurant> restaurants = service.SerchRestaurant(query);
         return ResponseEntity.ok(restaurants);
     }
    @GetMapping("/addFavriotesRestaurant/{userId}")
    public ResponseEntity<RestaurantDto> addFavroiteRestaurant(@PathVariable Long restaurantId,
                                                               @RequestBody User user) throws Exception {
        RestaurantDto restaurantDto = service.addFavorites(restaurantId, user);
        return ResponseEntity.ok(restaurantDto);
    }

    @GetMapping("/updateRestaurantStatus/{userId}")
    public ResponseEntity<Restaurant> updateRestaurantStatus(@PathVariable Long restaurantId
                                                               ) throws Exception {
        Restaurant restaurant = service.updateRestaurantStatus(restaurantId);
        return ResponseEntity.ok(restaurant);
    }



}
