package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// 1. Import đúng class DBConnection từ package util của bạn
import util.DBConnection; 

public class TaiKhoanDao {

    /**
     * Hàm kiểm tra thông tin đăng nhập từ cơ sở dữ liệu.
     * @param userOrId Tên đăng nhập hoặc Mã tài khoản
     * @param password Mật khẩu
     * @return true nếu tài khoản và mật khẩu hợp lệ, ngược lại trả về false.
     */
    public boolean checkLogin(String userOrId, String password) {
        
        String sql = "SELECT * FROM TAIKHOAN WHERE (MATK = ? OR TENDANGNHAP = ?) AND MATKHAU = ?";
        
        // 2. Gọi đúng DBConnection.getConnection() của bạn
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            // Truyền giá trị vào các dấu ? trong chuỗi SQL
            ps.setString(1, userOrId);
            ps.setString(2, userOrId);
            ps.setString(3, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true; // Có dữ liệu -> Đăng nhập thành công
                }
            }
            
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn CSDL trong TaiKhoanDao: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false; // Sai thông tin hoặc lỗi kết nối
    }
}