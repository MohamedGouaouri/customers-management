package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseController {
    private static boolean connectionEstablished = false;
    private static Connection connection;

    private static Connection establishConnection(){
        String url = "jdbc:sqlite:D:\\dev\\java\\person_store\\src\\database\\db.sqlite";
        try {
            connection =  DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection != null) connectionEstablished = true;
        return connection;
    }

    public static boolean createCustomerTable() throws SQLException {
        boolean result;
        String sql = "CREATE TABLE IF NOT EXISTS customers(" +
                "id INTEGER PRIMARY KEY,"+
                "name varchar(30) NOT NULL,"+
                "email varchar(30) NOT NULL," +
                "phone varchar(10) NOT NULL" + ")";

        if (connectionEstablished){
            Statement statement = connection.createStatement();
            result = statement.execute(sql);
        }else {
            connection = establishConnection();
            Statement statement = connection.createStatement();
            result = statement.execute(sql);
        }
        return result;
    }

    public static Connection getConnection() {
        return connection;
    }
}
