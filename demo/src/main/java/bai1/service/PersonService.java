package bai1.service;

import bai1.entities.Person;
import bai1.entities.TicketBook;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class PersonService implements IService{
    @Override
    public void insert() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Scanner scanner = new Scanner(System.in);

        try {
            //1. Doc danh sach hien tai
            FileReader reader = new FileReader("Person.json");
            Type personType = new TypeToken<ArrayList<Person>>(){}.getType();
            List<Person> persons = gson.fromJson(reader, personType);
            reader.close();

            if (persons == null) {
                persons = new ArrayList<>();
            }
            //2. Nhap thong tin doc gia moi
            System.out.println("Nhap ma doc gia: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.println("Nhap ten doc gia: ");
            String name = scanner.nextLine();

            System.out.println("Nhap sdt doc gia: ");
            String phone = scanner.nextLine();

            //3. Them vao danh sach
            Person person = new Person(id, name, phone);
            persons.add(person);

            //4. Ghi vao file
            FileWriter writer = new FileWriter("Person.json");
            gson.toJson(persons, writer);
            writer.close();
            System.out.println("Them doc gia thanh cong!");
        } catch (NumberFormatException e) {
            System.out.println("Nhap du lieu khong hop le: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Loi khi thao tac voi file: " + e.getMessage());
        }
    }

    @Override
    public void getList() {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader("Person.json");
            Type personListType = new TypeToken<ArrayList<Person>>() {}.getType();
            List<Person> persons = gson.fromJson(reader,personListType);
            System.out.println("Danh sach cac doc gia: ");
            for (Person person : persons) {
                System.out.println("- " + person.getId() + " " + person.getName() + " " + person.getPhone());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



}
