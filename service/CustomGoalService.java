package service;

import pojos.Account;
import pojos.Budget;
import pojos.CustomGoal;
import pojos.User;
import repository.CustomGoalRepository;

import java.math.BigDecimal;
import java.util.Scanner;

public class CustomGoalService {
    CustomGoalRepository customGoalRepo = new CustomGoalRepository();

    public void loadCustomGoalData(User user) {
        user.setCustomGoals(customGoalRepo.read(user));
    }

    public void createCustomGoal(User user) {
        Scanner input = new Scanner(System.in);

        CustomGoal customGoal = new CustomGoal(user.getUserID());
        System.out.println("\nPlease enter the following information: \n");
        System.out.print("   Custom Goal Name:  ");
        customGoal.setName(input.next());
        System.out.print("   Amount Needed: $");
        customGoal.setAmountNeeded(input.nextBigDecimal());
        customGoalRepo.create(customGoal);
        System.out.println("Custom Goal Added!!!");
    }

    public void modifyCustomGoal(User user) {
        Scanner input = new Scanner(System.in);

        System.out.println("\nPlease select which Custom Goal you would like to update:\n");
        int menuOption = 1;
        for (CustomGoal goal : user.getCustomGoals()) {
            System.out.println("   " + menuOption + " - " + goal.getName());
        }
        System.out.print("\nEnter selection:  ");
        int menuSelection = input.nextInt();
        System.out.print("\nPlease enter the new amount:  ");
        CustomGoal customGoal = user.getCustomGoals().get(menuSelection - 1);
        customGoal.setAmountNeeded(input.nextBigDecimal());
        customGoalRepo.updateAmount(customGoal);
        System.out.println("Custom Goal Updated!!!");
    }

    public void checkGoalStatus(User user) {
        Scanner input = new Scanner(System.in);

        System.out.println("\nPlease select which Custom Goal you would like a status report for: \n");
        int menuOption = 1;
        for (CustomGoal goal : user.getCustomGoals()) {
            System.out.println("   " + menuOption + " - " + goal.getName());
        }
        System.out.print("\nEnter selection:  ");
        int menuSelection = input.nextInt();
        CustomGoal goal = user.getCustomGoals().get(menuSelection - 1);
        for (Account account : user.getUserAccounts()) {
            if (account.getAccountType().equals("Savings")) {
                BigDecimal status = goal.getAmountNeeded().subtract(account.getCurrentBalance());
                if (status.doubleValue() > 0) {
                    System.out.println("\n" + (int) status.doubleValue() / user.getBudget().getSavingsGoals().get("General Savings").intValue() +
                            " more months to reach your goal.");
                } else {
                    System.out.println("\nGreat job!  You have reached the necessary amount for this Custom Goal!");
                }
            }
        }
    }
}
