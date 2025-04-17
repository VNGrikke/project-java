package ra.edu.business.config;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/manager_phone_store?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root1";
    private static final String PASSWORD = "a@1234";

    public static Connection openConnection() {
        Connection conn = null;
        try {
            // Đăng ký driver thủ công (dự phòng)
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("\u001B[31m" + "Lỗi kết nối CSDL do: " + e.getMessage() + "\u001B[0m");
        } catch (ClassNotFoundException e) {
            System.out.println("\u001B[31m" + "Không tìm thấy driver MySQL: " + e.getMessage() + "\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + "Lỗi không xác định khi kết nối CSDL: " + e.getMessage() + "\u001B[0m");
        }
        return conn;
    }

    public static void closeConnection(Connection conn, CallableStatement callst) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (callst != null) {
            try {
                callst.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}