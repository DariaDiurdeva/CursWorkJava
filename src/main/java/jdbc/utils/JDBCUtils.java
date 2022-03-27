package jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    public static Connection getNewConnection() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/hospital";
        String user = "root";
        String password = "dd18";
        Connection connection = DriverManager.getConnection(dbURL,user,password);
        if(connection.isValid(1)) {
            System.out.println("Connection successful");
        }
        return connection;
    }
}