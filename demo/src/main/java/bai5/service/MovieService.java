package bai5.service;

import bai5.entities.Movie;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieService implements IService{

    @Override
    public void insert() {
        List<Movie> movies = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                        .create();

        // Đọc dữ liệu từ file JSON
        try (FileReader fileReader = new FileReader("movies.json")) {
            movies = gson.fromJson(fileReader, new TypeToken<List<Movie>>(){}.getType());
            if (movies == null) {
                movies = new ArrayList<>();
            }
        } catch (Exception e) {
            movies = new ArrayList<>();
        }

        // Lấy ID lớn nhất hiện tại
        int maxId = movies.stream()
                .mapToInt(Movie::getId)
                .max()
                .orElse(0);  // Nếu danh sách trống, maxId sẽ là 0
        int newId = maxId + 1;
        System.out.println("ID mới của phim là: " + newId);

        //Nhap thong tin moi
        System.out.println("Nhap ten bo phim: ");
        String name = scanner.nextLine();

        System.out.println("Nhap the loai phim: ");
        String genre = scanner.nextLine();

        System.out.println("Nhap ten dao dien: ");
        String director = scanner.nextLine();

        System.out.println("Nhap thoi luong (phut): ");
        int duration = Integer.parseInt(scanner.nextLine());

        System.out.println("Nhap mo ta: ");
        String description = scanner.nextLine();

        System.out.println("Nhap ngay phat hanh (yyyy-MM-dd): ");
        LocalDate publishedDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Nhap danh muc (16+,18+): ");
        String category = scanner.nextLine();

        // Tao doi tuong movie moi
        Movie newMovie = new Movie(newId, name, genre, director, duration, description, publishedDate, category);
        movies.add(newMovie);

        //Ghi lai vao file json
        try(FileWriter fileWriter = new FileWriter("movies.json")) {
            gson.toJson(movies, fileWriter);
            System.out.println("Them phim thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi ghi vao file!" + e.getMessage());
        }
    }

    @Override
    public void update() {
        List<Movie> movies = new ArrayList<>();
        Gson gson = new GsonBuilder()
                                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                                    .setPrettyPrinting()
                                    .create();
        //Doc du lieu tu file
        try(FileReader fileReader = new FileReader("movies.json")) {
            movies = gson.fromJson(fileReader, new TypeToken<List<Movie>>(){}.getType());
            if (movies == null) {
                System.out.println("Danh sach phim trong!");
                return;
            }
        } catch (Exception e) {
            System.out.println("Doc file bi loi: " + e.getMessage());
            return;
        }
        //Nhap id bo phim can cap nhat
        Scanner scanner = new Scanner(System.in);
        System.out.println("Id bo phim can cap nhat: ");
        int idUpdate = Integer.parseInt(scanner.nextLine());
        Movie movieToUpdate = null;
        for (Movie movie : movies) {
            if (movie.getId() == idUpdate) {
                movieToUpdate = movie;
                break;
            }
        }
        if (movieToUpdate == null) {
            System.out.println("Khong the tim thay bo phim voi id: " + idUpdate);
            return;
        }
        //Nhap thong tin moi
        System.out.println("Nhap ten bo phim(nhan enter de giu nguyen): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            movieToUpdate.setName(name);
        }

        System.out.println("Nhap the loai phim (nhan enter de giu nguyen): ");
        String genre = scanner.nextLine();
        if (!genre.isEmpty()) {
            movieToUpdate.setGenre(genre);
        }

        System.out.println("Nhap ten dao dien (nhan enter de giu nguyen): ");
        String director = scanner.nextLine();
        if (!director.isEmpty()) {
            movieToUpdate.setDirector(director);
        }

        System.out.println("Nhap thoi luong (phut) (nhan enter de giu nguyen): ");
        String durationStr = scanner.nextLine();
        if (!durationStr.isEmpty()) {
            try {
                int duration = Integer.parseInt(durationStr);
                movieToUpdate.setDuration(duration);
            } catch (NumberFormatException e) {
                System.out.println("Thời lượng không hợp lệ, giữ nguyên giá trị cũ: " + movieToUpdate.getDuration());
            }
        }

        System.out.println("Nhap mo ta (nhan enter de giu nguyen): ");
        String description = scanner.nextLine();
        if(!description.isEmpty()) {
            movieToUpdate.setDescription(description);
        }

        System.out.println("Nhập ngày phát hành (yyyy-MM-dd) (nhấn Enter để giữ nguyên): ");
        String publishedDateStr = scanner.nextLine();
        if (!publishedDateStr.isEmpty()) {
            movieToUpdate.setPublishedDate(LocalDate.parse(publishedDateStr));
        }

        System.out.println("Nhập danh mục (16+, 18+, thiếu nhi) (nhấn Enter để giữ nguyên): ");
        String category = scanner.nextLine();
        if (!category.isEmpty()) {
            movieToUpdate.setCategory(category);
        }

        //Ghi lai vao file json
        try(FileWriter fileWriter = new FileWriter("movies.json")) {
            gson.toJson(movies, fileWriter);
            System.out.println("Cap nhat phim thanh cong");
        } catch (IOException e) {
            System.out.println("Loi ghi vao file!" + e.getMessage());
        }
    }

    @Override
    public void delete() {
        List<Movie> movies = new ArrayList<>();
        Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(LocalDate.class,new LocalDateAdapter())
                    .create();
        //Doc du lieu tu file
        try(FileReader fileReader = new FileReader("movies.json")) {
            movies = gson.fromJson(fileReader, new TypeToken<List<Movie>>(){}.getType());
            if (movies == null) {
                System.out.println("Danh sach phim trong!");
                return;
            }
        } catch (Exception e) {
            System.out.println("Loi doc file!" + e.getMessage());
            return;
        }
        //Nhap id can xoa
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap id bo phim can xoa: ");
        int idToDel = Integer.parseInt(scanner.nextLine());
        // Tim va xoa phim co id tuong ung
        boolean isRemoved = movies.removeIf(movie -> movie.getId() == idToDel);
        if (isRemoved) {
            //Ghi lai thay doi vao danh sach moi
            try(FileWriter fileWriter = new FileWriter("movies.json")) {
                gson.toJson(movies, fileWriter);
                System.out.println("Xoa phim thanh cong");
            } catch (IOException e) {
                System.out.println("Loi ghi file!" + e.getMessage());
            }
        } else {
            System.out.println("Khong tim thay bo phim co id la: " + idToDel);
        }
    }

    @Override
    public void getData() {
        Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDate.class,new LocalDateAdapter())
                        .setPrettyPrinting()
                        .create();
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
