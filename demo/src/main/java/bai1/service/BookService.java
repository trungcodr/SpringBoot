package bai1.service;

import bai1.entities.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class BookService implements IService{
    @Override
    public void insert() {
        Scanner scanner = new Scanner(System.in);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            // 1. Đọc danh sách hiện tại
            FileReader reader = new FileReader("Book.json");
            Type bookListType = new TypeToken<ArrayList<Book>>() {}.getType();
            ArrayList<Book> books = gson.fromJson(reader, bookListType);
            reader.close(); // đóng reader sau khi dùng

            if (books == null) {
                books = new ArrayList<>();
            }
            // 2. Nhap thong tin sach moi
            System.out.println("Nhap ma sach: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.println("Nhap ten sach: ");
            String name = scanner.nextLine();

            System.out.println("Nhap so luong: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            System.out.println("Nhap gia sach: ");
            Double price = Double.parseDouble(scanner.nextLine());

            // 3. Them vao danh sach
            Book newBook = new Book(id, name, quantity, price);
            books.add(newBook);

            //4. Ghi lai vao file
            FileWriter writer = new FileWriter("Book.json");
            gson.toJson(books, writer);
            writer.close();
            System.out.println("Them sach thanh cong!");

        } catch (NumberFormatException e) {
            System.out.println("Du lieu nhap khong hop le: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Loi khi thao tac voi file: " + e.getMessage());
        }
    }

    @Override
    public void getList() {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader("Book.json");
            Type bookList = new TypeToken<ArrayList<Book>>() {}.getType();
            ArrayList<Book> books = gson.fromJson(reader, bookList);
            System.out.println("Danh sach cac tac pham: ");
            for (Book book : books) {
                System.out.println("- " + book.getId() + " " + book.getName() + " " + book.getQuantity() + " "
                + book.getPrice());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
