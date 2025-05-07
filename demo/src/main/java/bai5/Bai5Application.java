package bai5;

import bai5.view.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Bai5Application implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Chao mung may con ga den voi bai tap cua anh!!!");
        while (true) {
            Menu menu = new Menu();
            menu.displayMenu();
        }
    }

    public static void main(String[] args) { SpringApplication.run(Bai5Application.class, args); }

}
