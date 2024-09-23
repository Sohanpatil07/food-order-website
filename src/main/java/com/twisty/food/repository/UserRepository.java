package com.twisty.food.repository;

import com.twisty.food.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    public User findByEmail(String username);



}