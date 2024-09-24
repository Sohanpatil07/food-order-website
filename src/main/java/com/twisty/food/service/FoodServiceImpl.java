package com.twisty.food.service;

import com.twisty.food.entity.Category;
import com.twisty.food.entity.Food;
import com.twisty.food.entity.Restaurant;
import com.twisty.food.repository.FoodRepository;
import com.twisty.food.request.FoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;
    @Override
    public Food CreateFood(FoodRequest req, Restaurant restaurant, Category category) throws Exception {
        Food food = new Food();
        food.setFoodName(req.getFoodName());
        food.setPrice(req.getPrice());
        food.setDescription(req.getDescription());
        food.setResturants(req.getRestaurant());
        food.setImages(req.getImages());
        food.setCategory(req.getCategory());
        food.setAvailability(req.isAvailability());
        food.setIngrediants(req.getIngrediants());
        food.setVegetarian(req.isVegetarian());
        food.setCreationDate(LocalDateTime.now());
        Food save = foodRepository.save(food);
        restaurant.getFoods().add(food);
        return save;
    }

    @Override
    public Food UpdateFoodById(Long foodId, FoodRequest updateFoodRequest) throws Exception {
        return null;
    }

    @Override
    public void DeleteFoodById(Long foodId) throws Exception {

    }

    @Override
    public Food getFoodById(Long foodId) throws Exception {
        return null;
    }

    @Override
    public List<Food> getReasturantFoods(Long restaurantId, Category category, boolean isVeg, boolean isSeasonable, boolean isNonVeg, String foodCategory) throws Exception {
        return null;
    }

    @Override
    public List<Food> searchFood(String keyword) throws Exception {
        return null;
    }

    @Override
    public Food updateAvailablityStatus(Long foodId) throws Exception {
        return null;
    }
}
