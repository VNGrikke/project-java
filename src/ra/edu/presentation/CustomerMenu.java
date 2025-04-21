package ra.edu.presentation;

import ra.edu.business.model.Customer;
import ra.edu.business.service.Customer.CustomerService;
import ra.edu.exception.ValidationException;
import ra.edu.validate.CustomerValidator;
import ra.edu.validate.Validator;

import java.util.List;

public class CustomerMenu {
    private CustomerService customerService;
    private DisplayMenu displayMenu;

    public CustomerMenu(CustomerService customerService, DisplayMenu displayMenu) {
        this.customerService = customerService;
        this.displayMenu = displayMenu;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=== QUẢN LÝ KHÁCH HÀNG ===");
            System.out.println("1. Danh sách khách hàng");
            System.out.println("2. Thêm mới khách hàng");
            System.out.println("3. Cập nhật khách hàng");
            System.out.println("4. Xóa khách hàng");
            System.out.println("0. Quay lại");

            int choice = getChoice();
            switch (choice) {
                case 1:
                    displayCustomerList();
                    break;
                case 2:
                    addCustomer();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 0:
                    displayMenu.menuManager();
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

    private void displayCustomerList() {
        List<Customer> customers = customerService.getCustomerList();
        if (customers.isEmpty()) {
            System.out.println("Danh sách khách hàng trống!");
        } else {
            System.out.println("Danh sách khách hàng:");
            for (Customer c : customers) {
                System.out.println("ID: " + c.getCustomerId() + " | Tên: " + c.getCustomerName() +
                        " | Email: " + c.getCustomerEmail() + " | Số điện thoại: " + c.getCustomerPhone() +
                        " | Địa chỉ: " + c.getCustomerAddress());
            }
        }
    }

    private void addCustomer() {
        System.out.println("Thêm mới khách hàng:");

        // Nhập và validate tên
        String name = promptAndValidate("Nhập tên khách hàng: ", "Tên khách hàng", CustomerValidator::validateName);

        // Nhập và validate email
        String email = promptAndValidate("Nhập email: ", "Email", CustomerValidator::validateEmail);

        // Nhập và validate số điện thoại
        String phone = promptAndValidate("Nhập số điện thoại: ", "Số điện thoại", CustomerValidator::validatePhone);

        // Nhập và validate địa chỉ
        String address = promptAndValidate("Nhập địa chỉ: ", "Địa chỉ", CustomerValidator::validateAddress);

        // Sau khi tất cả thông tin hợp lệ, gọi service để thêm khách hàng
        customerService.addCustomer(name, email, phone, address);
    }

    private void updateCustomer() {
        System.out.println("Cập nhật khách hàng:");
        int id = (int) Validator.promptForPositiveNumber("Nhập ID khách hàng: ", "ID khách hàng");

        // Nhập và validate tên
        String name = promptAndValidate("Nhập tên mới: ", "Tên khách hàng", CustomerValidator::validateName);

        // Nhập và validate email
        String email = promptAndValidate("Nhập email mới: ", "Email", CustomerValidator::validateEmail);

        // Nhập và validate số điện thoại
        String phone = promptAndValidate("Nhập số điện thoại mới: ", "Số điện thoại", CustomerValidator::validatePhone);

        // Nhập và validate địa chỉ
        String address = promptAndValidate("Nhập địa chỉ mới: ", "Địa chỉ", CustomerValidator::validateAddress);

        // Sau khi tất cả thông tin hợp lệ, gọi service để cập nhật khách hàng
        customerService.updateCustomer(id, name, email, phone, address);
    }

    private void deleteCustomer() {
        System.out.println("Xóa khách hàng:");
        int id = (int) Validator.promptForPositiveNumber("Nhập ID khách hàng: ", "ID khách hàng");
        customerService.deleteCustomer(id);
    }

    // Phương thức hỗ trợ để nhập và validate từng trường
    private String promptAndValidate(String prompt, String fieldName, ValidatorFunction validator) {
        while (true) {
            try {
                String input = Validator.promptForNotEmpty(prompt, fieldName);
                validator.validate(input);
                return input;
            } catch (ValidationException e) {
                System.out.println("\u001B[31m" + "Lỗi: " + e.getMessage() + " Vui lòng nhập lại!" + "\u001B[0m");
            }
        }
    }

    // Interface chức năng để truyền các phương thức validate
    @FunctionalInterface
    private interface ValidatorFunction {
        void validate(String input) throws ValidationException;
    }
}