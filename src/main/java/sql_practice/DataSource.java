package sql_practice;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSource {

    // class members
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    // constructor
    public DataSource() {
        conn = setMySqlConnection();
    }

    // methods
    public MysqlDataSource getMySqlDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();

        // set data source properties
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306);
        dataSource.setDatabaseName("millers");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    public Connection setMySqlConnection() {
        Connection connection = null;
        try {
            connection = getMySqlDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void addNewChain(String name, String description) {
        conn = null;
        try {
            conn = getMySqlDataSource().getConnection();
            stmt = conn.createStatement();
            String newRecord = "INSERT INTO chain (name, description) " + "VALUES ('" + name + "','" + description + "')";
            stmt.executeUpdate(newRecord);
            System.out.printf("New record inserted");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close connection
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
