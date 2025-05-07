package bai5.view;

import bai5.service.MovieService;

import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    MovieService movieService = new MovieService();
    public void displayMenu() {
        System.out.println("-----------------------------------");
        System.out.println("MENU");
        System.out.println("1. Hiển thị danh sách các bộ phim");
        System.out.println("2. Thêm mới phim");
        System.out.println("3. Xóa phim");
        System.out.println("4. Cập nhật phim");
        System.out.println("-----------------------------------");
        System.out.print("Moi ban nhap lua chon: ");
        int choose  = Integer.parseInt(sc.nextLine());
        switch (choose) {
            case 1:
                movieService.getData();
                break;
            case 2:
                movieService.insert();
                break;
            case 3:
                movieService.delete();
                break;
            case 4:
                movieService.update();
                break;
        }
    }
}
