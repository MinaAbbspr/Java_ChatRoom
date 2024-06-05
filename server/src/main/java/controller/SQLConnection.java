package controller;

import java.sql.*;

public class SQLConnection {
    private static SQLConnection sqlConnection;
    private final String url = "jdbc:mysql://localhost/messenger";;

    private SQLConnection() {
    }

    public static SQLConnection getSqlConnection() {
        if (sqlConnection == null)
            sqlConnection = new SQLConnection();
        return sqlConnection;
    }

    public boolean execute(String sqlCommand) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, "root", "");

            Statement statement = connection.prepareStatement(sqlCommand);
            statement.execute(sqlCommand);
            connection.close();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet executeSelect(String sqlCommand) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, "root", "");

            Statement statement = connection.prepareStatement(sqlCommand);
            return statement.executeQuery(sqlCommand);
        } catch (Exception e) {
            return null;
        }
    }
}
