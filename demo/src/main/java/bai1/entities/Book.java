package bai1.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Book {
    private int id;
    private String name;
    private int quantity;
    private double price;
}
