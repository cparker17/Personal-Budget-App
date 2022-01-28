package service;

import pojos.Expense;
import pojos.User;
import repository.ExpenseRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExpenseService {
    ExpenseRepository expenseRepo = new ExpenseRepository();


    public void loadExpenseData(User user) {
        user.setUserExpenses(expenseRepo.read(user));
    }
    public Expense recordExpense(User user) {
        Expense expense = new Expense(user.getUserID());
        String expenseType = promptExpenseType();
        String expenseCategory = promptExpenseCategory(user, expenseType);

        //set up new expense object
        if (!expenseType.equals("Return")) {
            expense.setExpenseType(expenseType);
        } else return null;
        if (!expenseCategory.equals("Return")) {
            expense.setCategory(expenseCategory);
        } else return null;
        expense.setAmount(promptExpenseAmount());

        //persist new expense to DB
        expenseRepo.recordExpense(expense);

        return expense;
    }

    public String promptExpenseType() {
        Scanner input = new Scanner(System.in);
        int menuSelection;

        do {
            System.out.println("Please select which type of EXPENSE you would like to record:\n");
            System.out.println("   1 - Fixed Expense");
            System.out.println("   2 - Flexible Expense");
            System.out.println("   3 - Savings Goals");
            System.out.println("   4 - Return to Previous Menu\n");
            System.out.println("Enter selection: ");

            menuSelection = input.nextInt();
            switch (menuSelection) {
                case 1:
                    return "Fixed Expense";
                case 2:
                    return "Flexible Expense";
                case 3:
                    return "Savings Goals";
                case 4:
                    return "Return";
                default:
                    System.out.println("Input entered out of bounds.  Please try again.\n");
            }
        } while(true);
    }

    public String promptExpenseCategory(User user, String expenseType) {
        Scanner input = new Scanner(System.in);
        int menuSelection;
        HashMap<String, Integer> temp = new HashMap<>();

        boolean continueLoop;
        do {
            continueLoop = false;
            System.out.println("Please select which type of CATEGORY you would like to record:\n");

            int menuOption = 1;
            //loop through and print out all the expense categories for the selected expense type
            if (expenseType.equals("Fixed Expenses")) {
                for (String fixedExp : user.getBudget().getFixedExpenses().keySet()) {
                    temp.put(fixedExp, menuOption);
                    System.out.println("   " + menuOption++ + " - " + fixedExp);
                }
            }
            else if (expenseType.equals("Flexible Expenses")) {
                for (String flexExp : user.getBudget().getFlexExpenses().keySet()) {
                    temp.put(flexExp, menuOption);
                    System.out.println("   " + menuOption++ + " - " + flexExp);
                }
            }
            else {
                for (String savingsExp : user.getBudget().getSavingsGoals().keySet()) {
                    temp.put(savingsExp, menuOption);
                    System.out.println("   " + menuOption++ + " - " + savingsExp);
                }
            }

            System.out.println("   " + menuOption + " - Return to Previous Menu\n");

            //get which category user wants to record to
            System.out.print("Enter menu selection: ");
            menuSelection = input.nextInt();
            if (menuSelection == temp.size()) {
                return null;
            }
            else if (menuSelection < 1 || menuSelection > temp.size()) {
                System.out.println("Input entered out of bounds.  Please try again.");
                continueLoop = true;
            }
        } while (continueLoop);


        for (Map.Entry<String, Integer> entry : temp.entrySet()) {
            if (entry.getValue().equals(menuSelection-1)) {
                return entry.getKey();
            }
        }

        return "Return";
    }

    public BigDecimal promptExpenseAmount() {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter the amount of the expense: $");
        BigDecimal expenseAmount = input.nextBigDecimal();

        return expenseAmount;
    }
}
