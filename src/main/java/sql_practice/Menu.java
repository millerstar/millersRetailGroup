package sql_practice;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.util.Scanner;

public class Menu {
    private static Scanner userSelection;

    public static void start() {
        boolean exit=false;
        System.out.println("Welcome to the Miller's Shops DB Manager, please enter your choice, 0 to exit");
        printMenu();

        DataSource ds = new DataSource();
        while (exit == false) {
            switch (getUserSelectionInt()) {
                case 0: exit=true;
                case 1:
                        System.out.println("\nEnter chain name");
                        String chainName = getUserSelectionString();
                        System.out.println("\ndescription");
                        String chainDescription = getUserSelectionString();
                        ds.addNewChain(chainName, chainDescription);
                case 2:
                    System.out.print("\nEnter store name");
                    String storeName = getUserSelectionString();
                    System.out.print("\nchain name:");
                    chainName = getUserSelectionString();
                    //ds.addNewStoreToChain(chainName, chainDescription);
                case 3:
                    System.out.print("\nEnter employee name");
                    String employeeName = getUserSelectionString();
                    System.out.print("\nchain name:");
                    chainName = getUserSelectionString();
                    //ds.addNewStoreToChain(chainName, chainDescription);
                case 4:
                    System.out.println("all shops that are in a certain Shopping Mall");
                case 5:
                    System.out.println("\t5. all shops that are in a certain Shopping Mall Group:");

                case 6:
                    System.out.println("\t6. Present all Employees of a certain Chain");

                case 7:
                    System.out.println("\t7. Present all details of a Shop");
                default:

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
        System.out.print("Your Selection: ");


    }

    private static int getUserSelectionInt() {
        userSelection = new Scanner(System.in);
        return userSelection.nextInt();
    }

    private static String getUserSelectionString() {
        userSelection = new Scanner(System.in);
        return userSelection.nextLine();
    }

    public static void main (String[] args){
        start();
    }


}


