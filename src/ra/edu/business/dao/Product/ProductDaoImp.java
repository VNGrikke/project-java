package ra.edu.business.dao.Product;

import ra.edu.business.config.ConnectionDB;
import java.sql.*;

public class ProductDaoImp implements ProductDAO {
    @Override
    public void displayPhoneList() {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            if (conn == null) return;

            callSt = conn.prepareCall("{call get_phone_list()}");
            ResultSet rs = callSt.executeQuery();
            System.out.println("Danh sách điện thoại:");
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                System.out.println("ID: " + id + " | Tên: " + name + " | Giá: $" + price);
            }
            if (!hasData) {
                System.out.println("Danh sách trống!");
            }
        } catch (SQLException e) {
            System.out.println("\u001B[31m" + "Lỗi lấy danh sách: " + e.getMessage() + "\u001B[0m");
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public void addPhone(String name, double price, String brand, int stock) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            if (conn == null) return;

            callSt = conn.prepareCall("{call add_phone(?, ?, ?, ?)}");
            callSt.setString(1, name);
            callSt.setDouble(2, price);
            callSt.setString(3, brand);
            callSt.setInt(4, stock);
            callSt.execute();
            System.out.println("Thêm điện thoại thành công!");
        } catch (SQLException e) {
            System.out.println("\u001B[31m" + "Lỗi thêm điện thoại: " + e.getMessage() + "\u001B[0m");
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }


}