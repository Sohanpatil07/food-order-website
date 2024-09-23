package com.twisty.food.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "food_name", nullable = false)
    private String foodName;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    private Category category;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

    private boolean availability;

    @ManyToOne
    private Restaurant resturants;

    private boolean isVegetarian;

    private boolean isSeasonable;

    @ManyToMany
    private List<IngrediantsItem> ingrediants = new ArrayList<>();

    private Date creationDate;








}
