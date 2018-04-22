package sql_practice;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataSource {

    // class members
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sqlStatement = null;
    private ResultSetMetaData resultSetMetaData = null;
    private ResultSet resultSet = null;

    // constructor
    public DataSource() {
        conn = setMySqlConnection();
    }

    // insert & select methods
    public void addShopToChain(String name, int city, String street, int employee, int chain) {
        String values = "VALUES (" + wrapWithCommas(name) + ", " + city + ", " + wrapWithCommas(street) + ", " + employee + ", " + chain + ")";
        sqlStatement = "INSERT INTO shop (name, city, street, employee, chain) " +  values;
        runUpdate(sqlStatement);
    }

    // format for birthDay - yyyy-mm-dd
    public void addEmploeeToChain(String name, String lastName, int city, String street, String postal_code, int shop, int group_managment, String birthDay) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date javaDate = null;
        try {
            javaDate = formatter.parse(birthDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date convertedBirthDay = new java.sql.Date(javaDate.getTime());

        String values = "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        sqlStatement = "INSERT INTO employee (name, last_name, city, street, postal_code, shop, `group`, birthDay) " +  values;
        try {
            PreparedStatement preparedStatement;
            conn = setMySqlConnection();
            preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, city);
            preparedStatement.setString(4, street);
            preparedStatement.setString(5, postal_code);
            preparedStatement.setInt(6, shop);
            preparedStatement.setInt(7, group_managment);
            preparedStatement.setDate(8, convertedBirthDay);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }

    }

    public ResultSet selectShopsInMall(String shoppingMallName) {
        sqlStatement = "SELECT shop.name FROM shop INNER JOIN shops_in_mall " +
                "ON shop.idshop = shops_in_mall.shop INNER JOIN mall " +
                "ON shops_in_mall.mall = mall.idmall " +
                "WHERE mall.name like '" + shoppingMallName.trim() + "'";
        resultSet = getResultSet(sqlStatement);
        return resultSet;
    }

    public ResultSet selectShopsInMallGroup(String shoppingMallGroupName) {
        sqlStatement = "SELECT shop.name from shop INNER JOIN shops_in_mall " +
                "ON shop.idshop = shops_in_mall.shop INNER JOIN mall " +
                "ON shops_in_mall.mall = mall.idmall INNER JOIN mall_group " +
                "ON mall.group = mall_group.idmall_group " +
                "WHERE mall_group.name like '" + shoppingMallGroupName.trim() + "'";
        resultSet = getResultSet(sqlStatement);
        return resultSet;
    }

    public ResultSet selectEmployeesByChain(String chainName) {
        sqlStatement = "SELECT employee.name, employee.last_name, employee.city, " +
                "employee.street, employee.birthDay FROM employee INNER JOIN shop " +
                "ON employee.shop = shop.idshop INNER JOIN chain " +
                "ON shop.chain = chain.idchain WHERE chain.name like '" + chainName.trim() + "'";
        resultSet = getResultSet(sqlStatement);
        return  resultSet;
    }

    public ResultSet selectShopDetails(String shopName) {
        sqlStatement = "SELECT shop.name, city.name, shop.street, employee.name as employee_name, " +
                "employee.last_name as employee_last_name, chain.name " +
                "FROM shop inner join city " +
                "ON shop.city = city.idcity inner join chain " +
                "ON shop.chain = chain.idchain inner join employee " +
                "ON shop.employee = employee.idemployee " +
                "WHERE shop.name like '" + shopName.trim() + "'";
        resultSet = getResultSet(sqlStatement);
        return  resultSet;
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

    public void closeConnection() {
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

    private ResultSet getResultSet(String sqlStatement) {
        try {
            conn = setMySqlConnection();
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sqlStatement);
            resultSetMetaData = resultSet.getMetaData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
//            closeConnection();
        }
        return resultSet;
    }

    private void runUpdate(String sqlStatement) {
        try {
            conn = setMySqlConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStatement);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
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

    private String wrapWithCommas(String str){
        return "'" + str + "'";
    }

/*    // tests for inserts
    public static void main(String[] args) throws SQLException {

        DataSource dataSource = new DataSource();
        Menu menu = new Menu();
        menu.printResuls(dataSource.selectShopsInMall("G Mall"));
        dataSource.addShopToChain("koko", 1, "Rotshild 1", 1, 1);
        dataSource.addEmploeeToChain("andrey", "litvinsky", 1, "Rotshild", "12345", 1, 0, "2010-11-10" );
    }*/
}
