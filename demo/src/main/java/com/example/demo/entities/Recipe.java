package com.example.demo.entities;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Recipe {
    private String recipeName;
    private String description;
    private int servings;
    private List<Ingredient> ingredients;
    private int prepTimeMinutes;
    private int cookTimeMinutes;

}
