package com.twisty.food.service;

import com.twisty.food.config.JwtProvider;
import com.twisty.food.entity.User;
import com.twisty.food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
      @Autowired
      private UserRepository userRepository;

      @Autowired
      private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String token) throws Exception {
        String emailFromJwtToken = jwtProvider.getEmailFromJwtToken(token);
        User user = findUserByEmail(emailFromJwtToken);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new Exception("User not found");
        }
        return user;
    }
}
