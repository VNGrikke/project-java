package ra.edu.presentation;

import ra.edu.business.service.Product.ProductService;
import ra.edu.validate.Validator;

public class ProductMenu {
    private ProductService productService;

    public ProductMenu(ProductService productService) {
        this.productService = productService;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=== QUẢN LÝ ĐIỆN THOẠI ===");
            System.out.println("1. Danh sách điện thoại");
            System.out.println("2. Thêm mới điện thoại");
            System.out.println("3. Sửa điện thoại");
            System.out.println("4. Xóa điện thoại");
            System.out.println("5. Quay lại");
            System.out.print("Chọn chức năng (1-5): ");

            int choice = getChoice();
            switch (choice) {
                case 1:
                    productService.displayPhoneList();
                    break;
                case 2:
                    addPhone();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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

    private void addPhone() {
        System.out.println("Thêm mới điện thoại:");
        String name = Validator.promptForNotEmpty("Nhập tên điện thoại: ", "Tên điện thoại");
        double price = Validator.promptForPositiveNumber("Nhập giá (USD): ", "Giá điện thoại");
        String brand = Validator.promptForNotEmpty("Nhập hãng điện thoại: ", "Hãng điện thoại");
        int stock = (int) Validator.promptForPositiveNumber("Nhập số lượng trong kho: ", "Số lượng");
        productService.addPhone(name, price, brand, stock);
    }
}
