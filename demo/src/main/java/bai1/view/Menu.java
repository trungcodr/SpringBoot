package bai1.view;

import bai1.service.BookService;
import bai1.service.PersonService;
import bai1.service.TicketBookService;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    BookService bookService = new BookService();
    PersonService personService = new PersonService();
    TicketBookService ticketBookService = new TicketBookService();
    public void displayMenu() throws IOException {
        System.out.println("--------------------------------------------");
        System.out.println("1- Hiển thị danh sách các độc giả\n" +
                "\n" +
                "2- Hiển thị danh sách Sách trong thư viện\n" +
                "\n" +
                "3- Thêm mới sách\n" +
                "\n" +
                "4- Thêm mới độc giả\n" +
                "\n" +
                "5- Thực hiện mượn sách");
        System.out.println("--------------------------------------------");
        System.out.print("Moi ban nhap lua chon: ");
        int choose = Integer.parseInt(sc.nextLine());
        switch (choose) {
            case 1:
                personService.getList();
                break;
            case 2:
                bookService.getList();
                break;
            case 3:
                bookService.insert();
                break;
            case 4:
                personService.insert();
                break;
            case 5:
                ticketBookService.borrowBook();
                break;

        }
    }
}
