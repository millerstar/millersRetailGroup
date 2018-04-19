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
                "WHERE mall_group.name like '" + shoppingMallGroupName.trim() + "'";
        resultSetMetaData = getResultSetMetaData(sqlStatement);
        return resultSetMetaData;
    }

    public ResultSetMetaData selectEmployeesByChain(String chainName) {
        sqlStatement = "SELECT employee.name, employee.last_name, employee.city, " +
                "employee.street, employee.birthDay FROM employee INNER JOIN shop " +
                "ON employee.shop = shop.idshop INNER JOIN chain " +
                "ON shop.chain = chain.idchain WHERE chain.name like '" + chainName.trim() + "'";
        resultSetMetaData = getResultSetMetaData(sqlStatement);
        return  resultSetMetaData;
    }

    public ResultSetMetaData selectShopDetails(String shopName) {
        sqlStatement = "SELECT shop.name, city.name, shop.street, employee.name as employee_name, " +
                "employee.last_name as employee_last_name, chain.name " +
                "FROM shop inner join city " +
                "ON shop.city = city.idcity inner join chain " +
                "ON shop.chain = chain.idchain inner join employee " +
                "ON shop.employee = employee.idemployee " +
                "WHERE shop.name like '" + shopName.trim() + "'";
        resultSetMetaData = getResultSetMetaData(sqlStatement);
        return  resultSetMetaData;
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
