package ra.edu.presentation;


import ra.edu.business.service.Admin.AdminService;
import ra.edu.business.service.Admin.AdminServiceImp;
import ra.edu.business.service.Product.ProductService;
import ra.edu.business.service.Product.ProductServiceImp;
import ra.edu.validate.Validator;

import java.io.Console;

public class DisplayMenu {
    private static AdminService adminService;
    private static ProductService productService;
    private final ProductMenu productMenu;

    public DisplayMenu() {
        this.adminService = new AdminServiceImp();
        this.productService = new ProductServiceImp();
        this.productMenu = new ProductMenu(productService);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=== MENU HỆ THỐNG QUẢN LÝ CỬA HÀNG ĐIỆN THOẠI ===");
            System.out.println("1. Đăng nhập");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = getChoice();
            switch (choice) {
                case 1:
                    login();
                    if (adminService.isLoggedIn()) {
                        menuManager();
                    }
                    break;
                case 0:
                    System.out.println("Thoát chương trình!");
                    adminService.logout();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    public void menuManager(){
        while (true) {
            System.out.println("\n=== DANH SÁCH QUẢN LÝ ===");
            System.out.println("1. Quản lý điện thoại");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý đơn hàng");
            System.out.println("4. Thống kê doanh thu");
            System.out.println("0. Đăng xuất");
            System.out.print("Chọn chức năng : ");
            int choice = getChoice();
            switch (choice) {
                case 1:
                    productMenu.displayMenu();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    adminService.logout();
                    return;
                default:
                    System.out.print("Sai cú pháp! \nNhập lại: ");
                    break;
            }
        }
    }


    // Hàm hỗ trợ: Lấy lựa chọn từ người dùng
    private int getChoice() {
        try {
            return Integer.parseInt(Validator.promptForNotEmpty("Nhập lựa chọn: ", "Lựa chọn"));
        } catch (NumberFormatException e) {
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    // Hàm hỗ trợ: Đăng nhập
    private void login() {
        Console console = System.console();
        if (console == null) {
            System.out.println("Không thể sử dụng Console. Vui lòng chạy trong terminal.");
            return;
        }

        System.out.print("Username: ");
        String username = console.readLine();

        System.out.print("Password: ");
        char[] passwordArray = console.readPassword();
        String password = new String(passwordArray);

        adminService.login(username, password);
    }
}