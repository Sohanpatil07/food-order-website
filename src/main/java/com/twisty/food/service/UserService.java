package com.twisty.food.service;

import com.twisty.food.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

   public User findUserByJwtToken(String token)throws Exception;

   public User findUserByEmail(String email)throws Exception;




}
