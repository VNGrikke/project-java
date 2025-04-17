package ra.edu.business.dao.Admin;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.dao.AppDAO;

import java.sql.*;

public interface AdminDAO extends AppDAO {
    public default boolean authenticate(String username, String password) {
        return false;
    }
}