package com.twisty.food.request;

import com.twisty.food.entity.Category;
import com.twisty.food.entity.IngrediantsItem;
import com.twisty.food.entity.Restaurant;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FoodRequest {

    private Long restaurantId;
    private String foodName;
    private Double price;
    private String description;
    private Category category;
    private Restaurant restaurant;
    private List<String> images;
    private boolean availability;
    private boolean isVegetarian;
    private boolean isSeasonable;
    private Date creationDate;
    private List<IngrediantsItem> ingrediants;
}
