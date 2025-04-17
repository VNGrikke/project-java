package ra.edu.business.dao.Admin;

import ra.edu.business.config.ConnectionDB;

import java.sql.*;

public class AdminDaoImp implements AdminDAO {
    @Override
    public boolean authenticate(String username, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            // Sử dụng ConnectionDB để mở kết nối
            conn = ConnectionDB.openConnection();
            if (conn == null) {
                return false;
            }

            // Chuẩn bị gọi stored procedure admin_login
            callSt = conn.prepareCall("{call admin_login(?, ?, ?)}");
            callSt.setString(1, username);
            callSt.setString(2, password);
            callSt.registerOutParameter(3, Types.INTEGER);

            // Thực thi stored procedure
            callSt.execute();

            // Lấy kết quả từ tham số đầu ra
            int result = callSt.getInt(3);
            return result > 0; // Trả về true nếu tìm thấy Admin (result = 1), false nếu không (result = 0)
        } catch (SQLException e) {
            System.out.println("\u001B[31m" + "Lỗi truy vấn CSDL: " + e.getMessage() + "\u001B[0m");
            return false;
        } finally {
            // Đóng kết nối và statement
            ConnectionDB.closeConnection(conn, callSt);
        }
    }
}