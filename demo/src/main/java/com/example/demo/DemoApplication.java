package com.example.demo;

import com.example.demo.entities.Ingredient;
import com.example.demo.entities.Recipe;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Gson gson = new Gson();
//        try (FileReader reader = new FileReader("DataBook.json")) {
//            // Chuyển đổi JSON thành mảng các đối tượng Person
//            Type personListType = new TypeToken<List<Book1>>(){}.getType();
//            List<Book1> book1s = gson.fromJson(reader, personListType);
//            System.out.println(book1s);
//        }

        // Bai 2
       try {
           FileReader reader = new FileReader("Pho.json");
           Recipe recipe = gson.fromJson(reader, Recipe.class);
           System.out.println(recipe);

           // Tinh tong thoi gian chuan bi va nau
           int totalTime = recipe.getPrepTimeMinutes() + recipe.getCookTimeMinutes();
           System.out.println("Tổng thời gian chuẩn bị và nấu: " + totalTime + " phút");

           // In danh sach nguyen lieu
           for (Ingredient ingredient : recipe.getIngredients()) {
               String quantity;
               if (ingredient.getQuantity() != -1) {
                    quantity = String.valueOf(ingredient.getQuantity());
               } else {
                   quantity = "0";
               }
               System.out.println("- " + ingredient.getItem() + " " + quantity + " " + ingredient.getUnit());
           }
       } catch (IOException e) {
           System.out.println(e.getMessage());
       }

    }



}
