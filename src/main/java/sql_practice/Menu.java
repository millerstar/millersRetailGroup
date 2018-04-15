package sql_practice;

import java.util.Scanner;

public class Menu {
    private static Scanner userSelection;

    public static void start() {
        boolean exit=false;
        System.out.println("Welcome to the Miller's Shops DB Manager, please enter your choice, 0 to exit");
        printMenu();
        while (exit == false) {
            switch (getUserSelection()) {
                case 0: exit=true;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                default:

            }
        }
    }


    private static void printMenu() {
        System.out.println();
        System.out.println("\t0. EXIT manager");
        System.out.println("\t1. Create a new Chain");
        System.out.println("\t2. Add a store to a Chain");
        System.out.println("\t3. Add Employee to Chain / to Group Management");
        System.out.println("\t4. Present all shops that are in a certain Shopping Mall");
        System.out.println("\t5. Present all shops that are in a certain Shopping Mall Group");
        System.out.println("\t6. Present all Employees of a certain Chain");
        System.out.println("\t7. Present all details of a Shop");
    }

    private static int getUserSelection() {
        userSelection = new Scanner(System.in);
        return userSelection.nextInt();
    }

    public static void main (String[] args){
        start();
    }


}


