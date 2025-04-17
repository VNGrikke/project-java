package ra.edu.business.service.Admin;

import ra.edu.business.dao.Admin.AdminDAO;
import ra.edu.business.dao.Admin.AdminDaoImp;

public class AdminServiceImp implements AdminService {
    private AdminDAO adminDAO;
    private boolean isLoggedIn;

    public AdminServiceImp() {
        this.adminDAO = new AdminDaoImp();
        this.isLoggedIn = false;
    }

    @Override
    public boolean login(String username, String password) {
        boolean authenticated = adminDAO.authenticate(username, password);
        if (authenticated) {
            isLoggedIn = true;
            System.out.println("Đăng nhập thành công!");
        } else {
            System.out.println("Lỗi: Username hoặc password không đúng!");
        }
        return authenticated;
    }

    @Override
    public void logout() {
        isLoggedIn = false;
        System.out.println("Đăng xuất thành công!");
    }

    @Override
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}