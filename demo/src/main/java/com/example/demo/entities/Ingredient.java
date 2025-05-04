package com.example.demo.entities;

import lombok.*;

@Getter
@Setter
@ToString
public class Ingredient {
    private String item;
    private int quantity;
    private String unit;
}
