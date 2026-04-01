package cyberGaming.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/cyber_center?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "141248690405Zt.";

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("ket noi da thanh cong");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: handle exception
            return null;
        }
    }
    
}
