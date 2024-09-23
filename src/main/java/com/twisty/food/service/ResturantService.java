package com.twisty.food.service;

import com.twisty.food.dto.RestaurantDto;
import com.twisty.food.entity.Restaurant;
import com.twisty.food.entity.User;
import com.twisty.food.request.ResturantRequestResponce;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResturantService {

    public Restaurant CreateResturant(ResturantRequestResponce req, User user)throws Exception;

    public Restaurant UpdateResturantById(Long restaurantId, ResturantRequestResponce updateResturantRequest)throws Exception;

    public void DeleteRestaurantById(Long restaurantId)throws Exception;

    public Restaurant getRestaurantById(Long restaurantId)throws Exception;

    public List<Restaurant> getAllRestaurants() throws Exception;

    public List<Restaurant> SerchRestaurant(String query) throws Exception;

    public RestaurantDto addFavorites(Long restaurantId,User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;


}
