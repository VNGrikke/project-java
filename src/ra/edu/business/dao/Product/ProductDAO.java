package ra.edu.business.dao.Product;

public interface ProductDAO {
    void displayPhoneList();
    void addPhone(String name, double price, String brand, int stock);
}