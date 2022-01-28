package service;

import java.util.Scanner;

public class MenuService {
    public int displayStartUpMenu() {
        Scanner input = new Scanner(System.in);
        int menuSelection;

        boolean continueLoop;
        do {
            continueLoop = false;
            //display opening menu
            System.out.println("Welcome to your personal budget application!!!\n");
            System.out.println("   1 - New User");
            System.out.println("   2 - Existing User");
            System.out.println("   3 - Exit\n");
            System.out.print("Enter menu selection: ");

            menuSelection = input.nextInt();
            if(menuSelection < 1 || menuSelection > 3) {
                System.out.println("Input entered out of bounds.  Please try again.");
                continueLoop = true;
            }
        }while(continueLoop);

        return menuSelection;
    }

    public int displayMainMenu() {
        Scanner input = new Scanner(System.in);
        int menuSelection;

        //displays main menu of the application to the user
        boolean continueLoop;
        do {
            continueLoop = false;
            System.out.println("\nMAIN MENU\n");
            System.out.println("   1 - Record Expense");
            System.out.println("   2 - Record Deposit");
            System.out.println("   3 - View Reports");
            System.out.println("   4 - Custom Goal Planning");
            System.out.println("   5 - Retirement Planning");
            System.out.println("   6 - Update User Information");
            System.out.println("   7 - Update Account Information");
            System.out.println("   8 - Update Loan Information");
            System.out.println("   9 - Update Monthly Income");
            System.out.println("   10 - Update Budget");
            System.out.println("   11 - Exit\n");
            System.out.print("Enter menu selection: ");

            menuSelection = input.nextInt();
            if(menuSelection < 1 || menuSelection > 11) {
                System.out.println("Input entered out of bounds.  Please try again.");
                continueLoop = true;
            }
        }while(continueLoop);

        return menuSelection;
    }

    public void displayBudgetIntroMenu() {
        //display info to user about setting up their budget following the 50/30/20 rule
        System.out.println("\nLet's get started setting up your budget.  There are three types of expenses you need");
        System.out.println(" to consider when setting up a balanced budget:\n");
        System.out.println("   1 - Fixed expenses are things such as a mortgage payment or car payment.  These are expenses");
        System.out.println("       that do not change from month to month.\n");
        System.out.println("   2 - Flexible expenses are things such as personal care or entertainment.  There are expenses");
        System.out.println("       that tend to change from month to month.\n");
        System.out.println("   3 - Savings expenses are things such as college savings or retirement savings.\n");
        System.out.println("Your goal throughout the use of this application is to keep your budget well balanced");
        System.out.println(" following the 50/30/20 rule:\n");
        System.out.println("   Fixed Expenses - 50%");
        System.out.println("   Flexible Expenses - 30%");
        System.out.println("   Savings - 20%\n");
        System.out.println("Let's get started setting up your budget!!!\n");
    }

    public int displayCustomGoalsMenu() {
        Scanner input = new Scanner(System.in);
        int menuSelection;

        //asks user if they want to create, modify, or check status of a custom goal
        boolean continueLoop;
        do {
            continueLoop = false;
            System.out.println("CUSTOM GOAL MENU\n");
            System.out.println("   1 - Create Custom Goal");
            System.out.println("   2 - Modify Custom Goal");
            System.out.println("   3 - Check Status of Custom Goal");
            System.out.println("   4 - Return to Previous Menu\n");
            System.out.print("Enter menu selection: ");

            menuSelection = input.nextInt();
            if(menuSelection < 1 || menuSelection > 4) {
                System.out.println("Input entered out of bounds.  Please try again.");
                continueLoop = true;
            }
        }while(continueLoop);

        return menuSelection;
    }

    public int displayReportsMenu() {
        Scanner input = new Scanner(System.in);
        int menuSelection;

        boolean continueLoop;
        do {
            continueLoop = false;
            System.out.println("REPORTS MENU\n");
            System.out.println("   1 - Budget vs Actual");
            System.out.println("   2 - Accounts Summary");
            System.out.println("   3 - Loans Summary");
            System.out.println("   4 - Expense Summary");
            System.out.println("   5 - Deposit Summary");
            System.out.println("   6 - Return to previous menu\n");
            System.out.println("Enter menu selection:  ");

            menuSelection = input.nextInt();
            if(menuSelection < 1 || menuSelection > 6) {
                System.out.println("Input entered out of bounds.  Please try again.");
                continueLoop = true;
            }
        }while(continueLoop);

        return menuSelection;
    }
}
