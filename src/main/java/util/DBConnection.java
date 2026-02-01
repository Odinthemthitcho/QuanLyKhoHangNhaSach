package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            String url =
                    "jdbc:sqlserver://localhost:1433;"
                            + "databaseName=Quan_Ly_Kho_Hang;"
                            + "encrypt=true;trustServerCertificate=true";


            String user = "sa";        // user SQL
            String password = "123456"; // mật khẩu SQL

            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
