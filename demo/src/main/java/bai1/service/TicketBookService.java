package bai1.service;

import bai1.entities.Book;
import bai1.entities.TicketBook;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TicketBookService {
    public void borrowBook() {
        Scanner scanner = new Scanner(System.in);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Đọc danh sách sách từ file Book.json
        ArrayList<Book> books;
        try (FileReader reader = new FileReader("Book.json")) {
            Type bookListType = new TypeToken<ArrayList<Book>>() {}.getType();
            books = gson.fromJson(reader, bookListType);
            if (books == null) books = new ArrayList<>();
        } catch (IOException e) {
            books = new ArrayList<>();
        }

        // Đọc danh sách phiếu mượn từ file TicketBook.json
        ArrayList<TicketBook> tickets;
        try (FileReader reader = new FileReader("TicketBook.json")) {
            Type ticketListType = new TypeToken<ArrayList<TicketBook>>() {}.getType();
            tickets = gson.fromJson(reader, ticketListType);
            if (tickets == null) tickets = new ArrayList<>();
        } catch (IOException e) {
            tickets = new ArrayList<>();
        }

        try {
            // Nhập ticketId và kiểm tra trùng
            int ticketId;
            while (true) {
                System.out.print("Nhập mã phiếu mượn: ");
                ticketId = Integer.parseInt(scanner.nextLine());

                boolean exists = false;
                for (int i = 0; i < tickets.size(); i++) {
                    if (tickets.get(i).getId() == ticketId) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
                    System.out.println(" ID đã tồn tại, nhập lại.");
                } else {
                    break;
                }
            }

            // Nhập bookId và tìm sách
            int bookId;
            Book selectedBook = null;

            while (true) {
                System.out.print("Nhập mã sách muốn mượn: ");
                bookId = Integer.parseInt(scanner.nextLine());

                for (int i = 0; i < books.size(); i++) {
                    if (books.get(i).getId() == bookId) {
                        selectedBook = books.get(i);
                        break;
                    }
                }

                if (selectedBook == null) {
                    System.out.println(" Không tìm thấy sách.");
                } else if (selectedBook.getQuantity() <= 0) {
                    System.out.println(" Sách đã hết.");
                    return;
                } else {
                    break;
                }
            }

            // Nhập thông tin còn lại
            System.out.print("Nhập mã người mượn: ");
            int personId = Integer.parseInt(scanner.nextLine());

            System.out.print("Ghi chú (nếu có): ");
            String note = scanner.nextLine();

            Date borrowDate = new Date(); // ngày hiện tại
            Calendar cal = Calendar.getInstance();
            cal.setTime(borrowDate);
            cal.add(Calendar.DATE, 7); // hạn trả 7 ngày sau
            Date returnDate = cal.getTime();

            // Tạo phiếu mượn
            TicketBook ticket = new TicketBook(ticketId, bookId, personId, note, borrowDate, returnDate);
            tickets.add(ticket);

            // Trừ số lượng sách
            selectedBook.setQuantity(selectedBook.getQuantity() - 1);

            // Ghi lại Book.json
            try (FileWriter writer = new FileWriter("Book.json")) {
                gson.toJson(books, writer);
            }

            // Ghi lại TicketBook.json
            try (FileWriter writer = new FileWriter("TicketBook.json")) {
                gson.toJson(tickets, writer);
            }

            System.out.println(" Mượn sách thành công!");

        } catch (NumberFormatException e) {
            System.out.println(" Vui lòng nhập đúng định dạng số.");
        } catch (IOException e) {
            System.out.println(" Lỗi ghi file: " + e.getMessage());
        }
    }

}
