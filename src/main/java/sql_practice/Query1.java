package sql_practice;

import java.sql.*;


public class Query1 {
    public static void main(String[] args) {
       /* DataSource dataSource = new DataSource();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        dataSource.addNewChain("Casual wear","family clothes");

        try {
            // get connection to database
            conn = dataSource.setMySqlConnection();
            stmt = conn.createStatement();
//            rs = stmt.executeQuery("SELECT version()");
            rs = stmt.executeQuery("SELECT * FROM employee");
            ResultSetMetaData metaData = rs.getMetaData();
            int cols = metaData.getColumnCount();
            for (int i = 1; i <= cols; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }

            System.out.println();
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    System.out.printf("\t" + rs.getObject(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }
}
