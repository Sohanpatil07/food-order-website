package com.twisty.food.controller;

import com.twisty.food.entity.User;
import com.twisty.food.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
      @Autowired
      private UserServiceImpl userService;

      public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwtToken) throws Exception {
          return ResponseEntity.ok(userService.findUserByJwtToken(jwtToken));
      }

}
