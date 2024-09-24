package com.twisty.food.service;

import com.twisty.food.entity.Category;
import com.twisty.food.entity.Food;
import com.twisty.food.entity.Restaurant;
import com.twisty.food.request.FoodRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodService {

    public Food CreateFood(FoodRequest req, Restaurant restaurant, Category category) throws Exception;

    public Food UpdateFoodById(Long foodId, FoodRequest updateFoodRequest) throws Exception;

    public void DeleteFoodById(Long foodId) throws Exception;

    public Food getFoodById(Long foodId) throws Exception;

    public List<Food> getReasturantFoods(Long restaurantId,
                                         Category category,
                                         boolean isVeg,
                                         boolean isSeasonable,
                                         boolean isNonVeg,
                                         String foodCategory) throws Exception;
    public List<Food> searchFood(String keyword)throws Exception;

    public Food updateAvailablityStatus(Long foodId) throws Exception;


}
