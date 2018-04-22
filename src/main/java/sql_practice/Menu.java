package sql_practice;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private static Scanner userSelection;

    public static void start() throws SQLException {
        boolean exit = false;
        System.out.println("Welcome to the Miller's Shops DB Manager, please enter your choice, 0 to exit");

        DataSource ds = new DataSource();
        while (exit == false) {
            printMenu();
            switch (getUserSelectionInt()) {
                case 0:
                    exit = true;
                    System.out.println("\n Thanks for using Miller's Shops DB Manager, bye bye!");
                    ds.closeConnection();
                    break;
                case 1:
                    System.out.println("\nEnter chain name");
                    String chainName = getUserSelectionString();
                    System.out.println("\ndescription");
                    String chainDescription = getUserSelectionString();
                    ds.addNewChain(chainName, chainDescription);
                    //TODO: add method to print the updated table to the user
                    System.out.println("\n " + chainName + " have been successfully added ");
                    break;

                case 2:
                    System.out.println("\nEnter store name");
                    String shopName = getUserSelectionString();

                    System.out.println("\nCity code:");
                    int cityCode = getUserSelectionInt();

                    System.out.println("\nStreet name:");
                    String streetName = getUserSelectionString();

                    System.out.println("\nEmployee code:");
                    int employeeCode = getUserSelectionInt();

                    System.out.println("\nchain code:");
                    int chainCode = getUserSelectionInt();

                    ds.addShopToChain(shopName, cityCode, streetName,  employeeCode,  chainCode);
                    //TODO: add method to print the updated table to the user
                    System.out.println("\n " + shopName + " have been successfully added ");
                    break;

                case 3:
                    System.out.println("\nEnter employee first name");
                    String employeeFirstName = getUserSelectionString();

                    System.out.println("\nEnter employee last name");
                    String employeeLastName = getUserSelectionString();

                    System.out.println("\nCity code:");
                    cityCode = getUserSelectionInt();

                    System.out.println("\nStreet name:");
                    streetName = getUserSelectionString();

                    System.out.println("\nPostal code:");
                    String postal_code = getUserSelectionString();

                    System.out.println("\nShop code:");
                    int shopCode = getUserSelectionInt();

                    System.out.println("\nGroup managment code:");
                    int groupManagmentCode = getUserSelectionInt();

                    System.out.println("\nBirth day");
                    String birthDay = getUserSelectionString();

                    ds.addEmployeeToChain(employeeFirstName, employeeLastName, cityCode, streetName, postal_code, shopCode, groupManagmentCode,  birthDay);
                    //TODO: add method to print the updated table to the user

                    System.out.println("\n " + employeeFirstName + " have been successfully added ");
                    break;

                case 4:
                    System.out.println("\nEnter shopping Mall name");
                    String shoppingMall = getUserSelectionString();
                    printResuls(ds.selectShopsInMall(shoppingMall));
                    break;

                case 5: //all shops that are in a certain Shopping Mall Group
                    System.out.println("\n " + " Enter shopping Mall group name");
                    String shoppingMallGroup = getUserSelectionString();
                    printResuls(ds.selectShopsInMallGroup(shoppingMallGroup));
                    break;

                case 6: //Present all Employees of a certain Chain
                    printResuls(ds.selectAvilableChains());
                    System.out.println("\n " + " Please choose the desired chain name from the list above");
                    String retailChainName = getUserSelectionString();
                    printResuls(ds.selectEmployeesByChain(retailChainName));
                    break;

                case 7: //Present all details of a Shop
                    System.out.println("\nEnter shop name");
                    shopName = getUserSelectionString();
                    printResuls(ds.selectShopDetails(shopName));
                    break;

                default:
                    exit = true;
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("\t0. EXIT manager");
        System.out.println("\t1. Create a new chain");
        System.out.println("\t2. Add a store to a chain");
        System.out.println("\t3. Add Employee to chain / to Group Management");
        System.out.println("\t4. Present all shops that are in a certain Shopping Mall");
        System.out.println("\t5. Present all shops that are in a certain Shopping Mall Group");
        System.out.println("\t6. Present all Employees of a certain Chain");
        System.out.println("\t7. Present all details of a Shop");
        System.out.println();
        System.out.println("Your Selection: ");
        System.out.println();

    }

    private static int getUserSelectionInt () {
        userSelection = new Scanner(System.in);
        return userSelection.nextInt();
    }

    private static String getUserSelectionString () {
        userSelection = new Scanner(System.in);
        return userSelection.nextLine();
    }

    private static void printResuls(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        System.out.println();
        int cols = metaData.getColumnCount();
        // print table headers
        for (int i = 1; i <= cols; i++) {
            System.out.print(metaData.getColumnName(i) + "\t");
        }

        System.out.println();
        // print table rows
        while (rs.next()) {
            for (int i = 1; i <= cols; i++) {
                System.out.printf("\t" + rs.getObject(i));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main (String[]args) throws SQLException {
        start();
    }


}



