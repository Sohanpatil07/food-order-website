package com.twisty.food.repository;

import com.twisty.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByRestaurantId(Long restaurantId);
    @Query("SELECT * FROM food WHERE f.name LIKE %:keyword% OR f.foodCategory.name LIKE %:keyword%")
    List<Food> searchFood(@Param("keyword")String keyword);

}