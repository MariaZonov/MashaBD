package dbConnect;

import propSettings.PropSettings;

import java.sql.*;
import java.util.Map;

public class DBConnector implements IDBConnector {
    private static Connection connection = null;
    private static Statement statement = null;

    private void open() {
        PropSettings confReader = new PropSettings();
        Map<String, String> confData = confReader.getPropSettings();
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(
                        String.format("%s/%s", confData.get("url"), confData.get("db_name")),
                        confData.get("db_username"),
                        confData.get("db_password"));
            }
            if (statement == null) {
                statement = connection.createStatement();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void close() {
        if (statement != null) {
            try {
                statement.close();
                statement = null;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void execute(String sqlRequest) {
        this.open();
        try {
            statement.execute(sqlRequest);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ResultSet executeQuery(String sqlRequest) {
        this.open();
        try {
            return statement.executeQuery(sqlRequest);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
