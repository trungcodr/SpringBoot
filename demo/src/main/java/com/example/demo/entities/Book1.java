package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Book1 {
    private int id;
    private String name;
    private String description;
    private String author;
    private int publishYear;
}
