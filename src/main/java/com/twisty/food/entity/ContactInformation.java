package com.twisty.food.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
public class ContactInformation {

    private String phoneNumber;

    private String emailAddress;

    private String twitter;

    private String instagram;
}
