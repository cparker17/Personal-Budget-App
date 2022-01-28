package service;

import pojos.Account;
import pojos.User;

import java.util.Scanner;

public class RetirementService {
    public void runRetirementPlanning(User user) {
        Scanner input = new Scanner(System.in);

        //requests from user information needed to calculate their retirement goal
        System.out.println("RETIREMENT PLANNING\n");
        System.out.println("   Let's see if you are on track to meet your retirement goal.  Please enter below information: \n");
        System.out.print("Current Age: \n");
        int currentAge = input.nextInt();
        System.out.print("Age of Retirement: \n");
        int retirementAge = input.nextInt();
        System.out.print("Amount Needed To Retire: \n");
        double amount = input.nextDouble();

        int years = calculateYearsToRetirement(user, currentAge, retirementAge, amount);

        //lets user know if they will meet their goal or how many more years they need to work
        if (years == 0) {
            System.out.println("Great job!  You are on track to meet your retirement amount.");
        }
        else if (years == -1) {
            System.out.println("You currently do not have a retirement budget in place.  Please set this up in " +
                    "your budget before proceeding with Retirement Planning.");
        }
        else {
            System.out.println("Looks like you will come up short.  You will need to work approximately " + (int) years
                    + " more years to reach your goal.");

            boolean continueLoop;
            //asks user if they want to cycle through the retirement planning again
            do {
                continueLoop = false;
                System.out.println("\nWould you like to try again?");
                System.out.println("   1 - Yes");
                System.out.println("   2 - No");
                System.out.print("Enter selection:  ");

                int menuSelection = input.nextInt();

                if (menuSelection == 1) {
                    runRetirementPlanning(user);
                } else if (menuSelection == 2) {
                    return;
                } else {
                    System.out.println("Input entered out of bounds.  Please try again.");
                    continueLoop = true;
                }
            }while(continueLoop);
        }
    }

    public int calculateYearsToRetirement(User user, int currentAge, int retirementAge, double amount) {
        double amountSavedByRetirementAge = 0;
        double monthlyRetirementBudget = user.getBudget().getSavingsGoals().get("Retirement Savings").doubleValue();

        //get retirement account balance to calculate years to retirement
        for (Account account : user.getUserAccounts()) {
            if (account.getAccountType().equals("Retirement Savings")) {
                if (account.getCurrentBalance() != null) {
                    amountSavedByRetirementAge = monthlyRetirementBudget * 12 *
                            (retirementAge - currentAge) + account.getCurrentBalance().doubleValue();
                }
                else {
                    amountSavedByRetirementAge = monthlyRetirementBudget * 12 *
                            (retirementAge - currentAge);
                }
            }
        }

        if (amountSavedByRetirementAge - amount >= 0) {
            return 0;
        }
        else if (amountSavedByRetirementAge == 0) {
            return -1;
        }
        else {
            return (int) ((amount - amountSavedByRetirementAge) / (monthlyRetirementBudget * 12));
        }
    }
}
