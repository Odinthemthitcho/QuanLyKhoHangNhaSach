package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    // NOTE: Thay đổi tên database tại đây (ví dụ: hungnd_demo hoặc Quan_Ly_Kho_Hang)
    private static final String URL = "jdbc:mysql://localhost:3306/hungnd_demo?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";     // Tên đăng nhập MySQL (thường là root)
    private static final String PASSWORD = "ndh@3976"; // Mật khẩu của bạn

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // NOTE: Load Driver MySQL (Dành cho phiên bản Connector/J cũ hoặc mới)
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // In ra console để kiểm tra khi chạy
            // System.out.println("Kết nối MySQL thành công!"); 
        } catch (Exception e) {
            System.err.println("Lỗi kết nối MySQL: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}