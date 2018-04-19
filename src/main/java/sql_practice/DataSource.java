package sql_practice;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class DataSource {

    // class members
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sqlStatement = null;
    private ResultSetMetaData resultSetMetaData = null;

    // constructor
    public DataSource() {
        conn = setMySqlConnection();
    }

    // insert & select methods
    public void addShopToChain(String name, int city, String street, int employee, int chain) {

    }

    public void addStoreToChain(String storeName, String chainName) {

    }

    public void addEmploeeToChain(String name, String LastName, int city, String street, String postal_code, int shop, int group_managment, Date birthDay) {

    }

    public ResultSetMetaData selectShopsInMall(String shoppingMallName) {
        sqlStatement = "SELECT shop.name FROM shop INNER JOIN shops_in_mall " +
                "ON shop.idshop = shops_in_mall.shop INNER JOIN mall " +
                "ON shops_in_mall.mall = mall.idmall " +
                "WHERE mall.name like '" + shoppingMallName.trim() + "'";
        resultSetMetaData = getResultSetMetaData(sqlStatement);
        return resultSetMetaData;
    }

    public ResultSetMetaData selectShopsInMallGroup(String shoppingMallGroupName) {
        sqlStatement = "SELECT shop.name from shop INNER JOIN shops_in_mall " +
                "ON shop.idshop = shops_in_mall.shop INNER JOIN mall " +
                "ON shops_in_mall.mall = mall.idmall INNER JOIN mall_group " +
                "ON mall.group = mall_group.idmall_group " +
                "where mall_group.name like '" + shoppingMallGroupName + "'";
        resultSetMetaData = getResultSetMetaData(sqlStatement);
        return resultSetMetaData;
    }

    // service methods
    private Connection setMySqlConnection() {
        Connection connection = null;
        try {
            connection = getMySqlDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private MysqlDataSource getMySqlDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();

        // set data source properties
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306);
        dataSource.setDatabaseName("millers");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    private void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
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

    private ResultSetMetaData getResultSetMetaData(String sqlStatement) {
        try {
            conn = setMySqlConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlStatement);
            resultSetMetaData = rs.getMetaData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return resultSetMetaData;
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
