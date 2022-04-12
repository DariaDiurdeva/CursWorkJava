package jdbc.utils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    public static Connection getNewConnection() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/hospital";
        String user = null;
        String password = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("source.txt")));
            user = reader.readLine();
            password = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection connection = DriverManager.getConnection(dbURL, user, password);
        if (connection.isValid(1)) {
            System.out.println("Connection successful");
        }
        return connection;

    }
}