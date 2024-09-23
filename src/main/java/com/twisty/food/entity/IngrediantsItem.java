package com.twisty.food.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngrediantsItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ingrediants_name", nullable = false, length = 160)
    private String ingrediantsName;

    @ManyToOne
    private IngrediansCategory ingrediansCategory;


}
