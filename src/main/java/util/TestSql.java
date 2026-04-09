package util;

import java.sql.Connection;

public class TestSql {
    public static void main(String[] args) {
        try (Connection conn = util.DBConnection.getConnection()) {
            if (conn != null) {
                java.sql.Statement stmt = conn.createStatement();
                // Thay 'Persons' bằng tên bảng thật trong MySQL của bạn
                java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Persons LIMIT 5");
                
                while (rs.next()) {
                    System.out.println(rs.getString(1) + " - " + rs.getString(2));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
