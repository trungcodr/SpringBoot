package bai5.service;

import bai5.entities.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class MovieService implements IService{

    @Override
    public void insert() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void getData() {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class,new LocalDateAdapter()).setPrettyPrinting().create();
        try {
            FileReader fileReader = new FileReader("movies.json");
            Type movieType = new TypeToken<List<Movie>>(){}.getType();
            List<Movie> movies = gson.fromJson(fileReader, movieType);
            System.out.println("Danh sach phim: ");
            for (Movie movie : movies) {
                System.out.println(movie);
            }
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }

    }
}
