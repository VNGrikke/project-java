package ra.edu.presentation;

import ra.edu.business.service.Admin.AdminService;
import ra.edu.business.service.Admin.AdminServiceImp;
import ra.edu.business.service.Customer.CustomerService;
import ra.edu.business.service.Customer.CustomerServiceImp;
import ra.edu.business.service.Product.ProductService;
import ra.edu.business.service.Product.ProductServiceImp;

import ra.edu.validate.Validator;

import java.io.Console;

public class DisplayMenu {
    private AdminService adminService;
    private ProductService productService;
    private CustomerService customerService;
    private ProductMenu productMenu;
    private CustomerMenu customerMenu;

    public DisplayMenu() {
        this.adminService = new AdminServiceImp();
        this.productService = new ProductServiceImp();
        this.customerService = new CustomerServiceImp();
        this.productMenu = new ProductMenu(productService, this);
        this.customerMenu = new CustomerMenu(customerService, this);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=== MENU HỆ THỐNG QUẢN LÝ CỬA HÀNG ĐIỆN THOẠI ===");
            System.out.println("1. Đăng nhập");
            System.out.println("0. Thoát");

            int choice = getChoice();
            switch (choice) {
                case 1:
                    if (login()) {
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

    public void menuManager() {
        while (true) {
            System.out.println("\n=== DANH SÁCH QUẢN LÝ ===");
            System.out.println("1. Quản lý điện thoại");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý đơn hàng");
            System.out.println("4. Thống kê doanh thu");
            System.out.println("0. Đăng xuất");

            int choice = getChoice();
            switch (choice) {
                case 1:
                    productMenu.displayMenu();
                    break;
                case 2:
                    customerMenu.displayMenu();
                    break;
                case 3:
                    System.out.println("Chức năng chưa được triển khai!");
                    break;
                case 4:
                    System.out.println("Chức năng chưa được triển khai!");
                    break;
                case 0:
                    adminService.logout();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private int getChoice() {
        try {
            return Integer.parseInt(Validator.promptForNotEmpty("Nhập lựa chọn: ", "Lựa chọn"));
        } catch (NumberFormatException e) {
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    private boolean login() {
        Console console = System.console();
        if (console == null) {
            System.out.println("Không thể sử dụng Console. Vui lòng chạy trong terminal.");
            return false;
        }

        System.out.print("Username: ");
        String username = console.readLine();

        System.out.print("Password: ");
        char[] passwordArray = console.readPassword();
        String password = new String(passwordArray);

        return adminService.login(username, password);
    }
}