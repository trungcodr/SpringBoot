package bai1;

import bai1.view.Menu;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Bai1Application implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello World");
        while (true) {
            Menu menu = new Menu();
            menu.displayMenu();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Bai1Application.class, args);
    }
}
