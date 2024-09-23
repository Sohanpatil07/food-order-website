package com.twisty.food.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngrediansCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "category_name", nullable = false, length = 160)
    private String categoryName;

    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

    @OneToMany
    private List<IngrediantsItem> ingrediantsItems = new ArrayList<>();

    @ManyToOne
    private Restaurant restaurants;

    private boolean inStock=true;

}
