package SQL;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MySQLProject {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream("src/main/resources/sql.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Connection connection = DriverManager.getConnection(
                properties.getProperty("database.url"),
                properties.getProperty("database.user"),
                properties.getProperty("database.password")
        );

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM phone");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
