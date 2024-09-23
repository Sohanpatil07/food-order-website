package com.twisty.food.responce;

import com.twisty.food.entity.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponce {

    private String jwt;

    private String message;

    private USER_ROLE userRole;;

}
