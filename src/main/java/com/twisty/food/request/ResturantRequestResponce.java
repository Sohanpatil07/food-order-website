package com.twisty.food.request;

import com.twisty.food.entity.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class ResturantRequestResponce {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private String address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;

}
