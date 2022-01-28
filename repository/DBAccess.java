package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String connectionString = "jdbc:mysql://localhost/budget?"
                + "user=root&password=ENTER_PASSWORD"
                + "&useSSL=false&allowPublicKeyRetrieval=true";
        Connection connection = DriverManager.getConnection(connectionString);

        return connection;
    }
}
