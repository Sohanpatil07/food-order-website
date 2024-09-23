package com.twisty.food.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User customer;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Restaurant resturant;

    private Long totalAmount;

    private String orderStatus;

    private Date createdAt;

    @ManyToOne
    private Address diliveryAddress;

    @OneToMany
    private List<OrderItem> orderItems;

    private int totalItem;

    private int totalPrice;

    @OneToMany
    private List<Food> food;





}
