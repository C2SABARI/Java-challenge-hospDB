package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load MySQL driver
                Class.forName("com.mysql.cj.jdbc.Driver");

              
                String url = "jdbc:mysql://localhost:3306/hospitaldb?useSSL=false&allowPublicKeyRetrieval=true";
                String user = "root";
                String password = "Sabari@123";

                // Establish connection
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                System.out.println("JDBC Driver not found.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Database connection failed.");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
