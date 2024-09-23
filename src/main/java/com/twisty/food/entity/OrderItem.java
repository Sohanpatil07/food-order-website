package com.twisty.food.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Food food;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    private Long totalPrice;

    private Long quntity;

    private List<String> ingredients;



}
