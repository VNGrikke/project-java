package ra.edu.business.service.Product;

import ra.edu.business.dao.Product.ProductDAO;
import ra.edu.business.dao.Product.ProductDaoImp;
import ra.edu.exception.ValidationException;
import ra.edu.validate.Validator;

public class ProductServiceImp implements ProductService {
    private ProductDAO productDAO;

    public ProductServiceImp() {
        this.productDAO = new ProductDaoImp();
    }

    @Override
    public void displayPhoneList() {
        productDAO.displayPhoneList();
    }

    @Override
    public void addPhone(String name, double price, String brand, int stock) {
        try {
            Validator.validateNotEmpty(name, "Tên điện thoại");
            Validator.validatePositiveNumber(price, "Giá điện thoại");
            Validator.promptForNotEmpty(brand, "Hãng điên thoại");
            Validator.validatePositiveNumber(stock, "Số lượng");
            productDAO.addPhone(name, price, brand ,stock);
        } catch (ValidationException e) {
            System.out.println("\u001B[31m" + "Lỗi validation: " + e.getMessage() + "\u001B[0m");
        }
    }

}