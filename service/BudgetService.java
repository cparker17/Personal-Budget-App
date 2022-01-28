package service;

import pojos.*;
import pojos.User;
import repository.BudgetRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BudgetService {
    BudgetRepository budgetRepo = new BudgetRepository();

    public void loadBudgetData(User user) {
        Budget budget = new Budget(user.getUserID());
        user.setBudget(budgetRepo.read(budget));
    }

    public void setUpBudget(User user) {
        Budget budget = new Budget(user.getUserID());
        Scanner input = new Scanner(System.in);

        //get input from user to set up their fixed expenses
        HashMap<String, BigDecimal> fixedExp = new HashMap<>();
        System.out.println("Please enter the monthly amount you want to budget for each category:\n");
        System.out.println("FIXED EXPENSES");
        System.out.print("   Mortgage Payment: ");
        fixedExp.put("Mortgage", input.nextBigDecimal());
        System.out.print("   Child Care: ");
        fixedExp.put("Child Care", input.nextBigDecimal());
        System.out.print("   Life Insurance: ");
        fixedExp.put("Life Insurance", input.nextBigDecimal());
        System.out.print("   Health Insurance: ");
        fixedExp.put("Health Insurance", input.nextBigDecimal());
        System.out.print("   Car Payment: ");
        fixedExp.put("Car Payment", input.nextBigDecimal());
        System.out.print("   Car Insurance: ");
        fixedExp.put("Car Insurance", input.nextBigDecimal());
        System.out.print("   TV / Internet: ");
        fixedExp.put("TV / Internet", input.nextBigDecimal());
        System.out.print("   Gym Membership: ");
        fixedExp.put("Gym Membership", input.nextBigDecimal());
        System.out.print("   House Cleaning Service: ");
        fixedExp.put("House Cleaning Service", input.nextBigDecimal());
        System.out.print("   Other Fixed Expense: ");
        fixedExp.put("Other Fixed Expense", input.nextBigDecimal());
        budget.setFixedExpenses(fixedExp);

        //get input from user to set up their flexible expenses
        HashMap<String, BigDecimal> flexExp = new HashMap<>();
        System.out.println("\nFLEXIBLE EXPENSES");
        System.out.print("   Groceries: ");
        flexExp.put("Groceries", input.nextBigDecimal());
        System.out.print("   House Supplies: ");
        flexExp.put("House Supplies", input.nextBigDecimal());
        System.out.print("   Utilities: ");
        flexExp.put("Utilities", input.nextBigDecimal());
        System.out.print("   Cell Phone: ");
        flexExp.put("Cell Phone", input.nextBigDecimal());
        System.out.print("   Gas: ");
        flexExp.put("Gas", input.nextBigDecimal());
        System.out.print("   Auto Maintenance: ");
        flexExp.put("Auto Maintenance", input.nextBigDecimal());
        System.out.print("   Alcohol: ");
        flexExp.put("Alcohol", input.nextBigDecimal());
        System.out.print("   Personal Care: ");
        flexExp.put("Personal Care", input.nextBigDecimal());
        System.out.print("   Pet Food: ");
        flexExp.put("Pet Food", input.nextBigDecimal());
        System.out.print("   Veterinary: ");
        flexExp.put("Veterinary", input.nextBigDecimal());
        System.out.print("   Entertainment: ");
        flexExp.put("Entertainment", input.nextBigDecimal());
        System.out.print("   Vacation: ");
        flexExp.put("Vacation", input.nextBigDecimal());
        System.out.print("   Other Flexible Expense: ");
        flexExp.put("Other Flexible Expense", input.nextBigDecimal());
        budget.setFlexExpenses(flexExp);

        //set up users savings amounts
        HashMap<String, BigDecimal> savingsGoals = new HashMap<>();
        System.out.println("\nSAVINGS");
        System.out.print("   General Savings: ");
        savingsGoals.put("General Savings", input.nextBigDecimal());
        System.out.print("   College Savings: ");
        savingsGoals.put("College Savings", input.nextBigDecimal());
        System.out.print("   Retirement Savings: ");
        savingsGoals.put("Retirement Savings", input.nextBigDecimal());
        budget.setSavingsGoals(savingsGoals);

        //set up user's monthly income
        System.out.println("\nTo complete your budget, please enter your monthly income.");
        System.out.print("   Monthly Income: ");
        budget.setMonthlyIncome(input.nextBigDecimal());

        user.setBudget(budget);
        budgetRepo.create(budget);
    }

    //user will loop through setting up their budget until it is balanced to 50/30/20 (+/- 5%)
    public void balanceBudget(User user) {
        Scanner input = new Scanner(System.in);
        double[] budgetRatios;

        //user will loop through setting up their budget until it is balanced to 50/30/20 (+/- 5%)
        boolean continueLoop;
        do {
            continueLoop = false;
            budgetRatios = getBudgetRatios(user.getBudget());

            //checks if user's entered expenses exceeds their monthly income
            double sum = Arrays.stream(budgetRatios).sum();
            if(sum > 1) {
                System.out.println("\nYour expenses exceed your monthly income.  Would you like to fix it before proceeding?\n");
                System.out.println("   1 - Yes");
                System.out.println("   2 - No\n");
                System.out.print("Enter selection:  ");
                int menuSelection = input.nextInt();
                if (menuSelection == 1) {
                    updateBudget(user);
                }
                if (menuSelection < 1 || menuSelection > 2) {
                    System.out.println("Input entered out of bounds.  Please try again.");
                    continueLoop = true;
                }
            }
            else if ((budgetRatios[0] > .50 && budgetRatios[0] < .55) && (budgetRatios[1] > .30 && budgetRatios[1] < .35)) {
                System.out.println("\n   Fixed Expenses = " + (int) (budgetRatios[0]*100) + "%");
                System.out.println("Flexible Expenses = " + (int) (budgetRatios[1]*100) + "%");
                System.out.println("    Savings Goals = " + (int) (budgetRatios[2]*100) + "%");
                System.out.println("\nLooks great!!!");
            }
            else {
                System.out.println("\n   Fixed Expenses = " + (int) budgetRatios[0]*100 + "%");
                System.out.println("Flexible Expenses = " + (int) budgetRatios[0]*100 + "%");
                System.out.println("    Savings Goals = " + (int) budgetRatios[0]*100 + "%");
                System.out.println("\nYour budget is out of balance.  Would you like to fix it before proceeding?\n");
                System.out.println("   1 - Yes");
                System.out.println("   2 - No\n");
                System.out.print("Enter selection:  ");
                int menuSelection = input.nextInt();
                if (menuSelection == 1) {
                    updateBudget(user);
                }
                if (menuSelection < 1 || menuSelection > 2) {
                    System.out.println("Input entered out of bounds.  Please try again.");
                    continueLoop = true;
                }
            }
        } while (continueLoop);
    }

    public void updateBudget(User user) {
        Scanner input = new Scanner(System.in);
        boolean continueLoop;
        int menuSelection;

        //ask user which category of their budget they want to update
        do {
            continueLoop = false;
            System.out.println("\nEnter the category you would like to update: \n");
            System.out.println("   1 - Fixed Expenses");
            System.out.println("   2 - Flexible Expenses");
            System.out.println("   3 - Savings");
            System.out.println("   4 - Monthly Income");
            System.out.println("   5 - Previous Menu");
            System.out.print("Enter menu selection: ");

            switch (input.nextInt()) {
                case 1:
                    String fixedExpCategory = getFixedExpenseCategory(user);
                    if (fixedExpCategory.equals("previousMenu")) {
                        continueLoop = true;
                    }
                    else {
                        System.out.print("\nPlease enter the new amount to budget:  $");
                        BigDecimal amount = input.nextBigDecimal();
                        user.getBudget().getFixedExpenses().replace(fixedExpCategory, amount);
                        budgetRepo.update(user, "Fixed Expenses", amount, fixedExpCategory);
                    }
                    break;
                case 2:
                    String flexExpCategory = getFlexibleExpenseCategory(user);
                    if (flexExpCategory.equals("previousMenu")) {
                        continueLoop = true;
                    }
                    else {
                        System.out.print("\nPlease enter the new amount to budget:  ");
                        BigDecimal amount = input.nextBigDecimal();
                        user.getBudget().getFlexExpenses().replace(flexExpCategory, amount);
                        budgetRepo.update(user, "Flexible Expenses", amount, flexExpCategory);
                    }
                    break;
                case 3:
                    String savingsCategory = getSavingsCategory(user);
                    if (savingsCategory.equals("previousMenu")) {
                        continueLoop = true;
                    }
                    else {
                        System.out.print("\nPlease enter the new amount to budget:  ");
                        BigDecimal amount = input.nextBigDecimal();
                        user.getBudget().getSavingsGoals().replace(savingsCategory, amount);
                        budgetRepo.update(user, "Savings Goals", amount, savingsCategory);
                    }
                    break;
                case 4:
                    updateMonthlyIncome(user);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Input entered out of bounds.  Please try again.");
                    continueLoop = true;
            }

        }while (continueLoop);
    }

    public String getFixedExpenseCategory(User user) {
        Scanner input = new Scanner(System.in);
        boolean continueLoop;
        int menuSelection;
        int menuOption = 1;
        HashMap<String, Integer> temp = new HashMap<>();

        do {
            continueLoop = false;
            System.out.println("Please select which FIXED EXPENSE category you would like to update:\n");

            //loop through and print out the fixed expense options
            for (String fixedExp : user.getBudget().getFixedExpenses().keySet()) {
                temp.put(fixedExp, menuOption);
                System.out.println("   " + menuOption++ + " - " + fixedExp);
            }
            System.out.println("   " + menuOption + " - Return to Previous Menu\n");

            //get which category user wants to update
            System.out.print("Enter menu selection: ");
            menuSelection = input.nextInt();
            if (menuSelection < 1 || menuSelection > temp.size()) {
                System.out.println("Input entered out of bounds.  Please try again.");
                continueLoop = true;
            }
        }while(continueLoop);

        //returns the category (key) that the user wants to update
        for (Map.Entry<String, Integer> entry : temp.entrySet()) {
            if (entry.getValue().equals(menuSelection)) {
                return entry.getKey();
            }
        }

        return "previousMenu";
    }

    public String getFlexibleExpenseCategory(User user) {
        Scanner input = new Scanner(System.in);
        boolean continueLoop;
        int menuSelection;
        int menuOption = 1;
        HashMap<String, Integer> temp = new HashMap<>();

        do {
            continueLoop = false;
            System.out.println("Please select which FLEXIBLE EXPENSE category you would like to update:\n");

            //loop through and print out the flexible expense options
            for (String flexExp : user.getBudget().getFlexExpenses().keySet()) {
                temp.put(flexExp, menuOption);
                System.out.println("   " + menuOption++ + " - " + flexExp);
            }
            System.out.println("   " + menuOption + " - Return to Previous Menu\n");

            //get which category user wants to update
            System.out.print("Enter menu selection: ");
            menuSelection = input.nextInt();
            if (menuSelection < 1 || menuSelection > temp.size()) {
                System.out.println("Input entered out of bounds.  Please try again.");
                continueLoop = true;
            }
        }while(continueLoop);

        //returns the category (key) that the user wants to update
        for (Map.Entry<String, Integer> entry : temp.entrySet()) {
            if (entry.getValue().equals(menuSelection)) {
                return entry.getKey();
            }
        }

        return "previousMenu";
    }

    public String getSavingsCategory(User user) {
        Scanner input = new Scanner(System.in);
        boolean continueLoop;
        int menuSelection;
        int menuOption = 1;
        HashMap<String, Integer> temp = new HashMap<>();

        do {
            continueLoop = false;
            System.out.println("Please select which SAVINGS category you would like to update:\n");

            //loop through and print out the savings expense options
            for (String savingsExp : user.getBudget().getSavingsGoals().keySet()) {
                temp.put(savingsExp, menuOption);
                System.out.println("   " + menuOption++ + " - " + savingsExp);
            }
            System.out.println("   " + menuOption + " - Return to Previous Menu\n");

            //get which category user wants to update
            System.out.print("Enter menu selection: ");
            menuSelection = input.nextInt();
            if (menuSelection < 1 || menuSelection > temp.size()) {
                System.out.println("Input entered out of bounds.  Please try again.");
                continueLoop = true;
            }
        }while(continueLoop);

        //returns the category (key) that the user wants to update
        for (Map.Entry<String, Integer> entry : temp.entrySet()) {
            if (entry.getValue().equals(menuSelection)) {
                return entry.getKey();
            }
        }

        return "previousMenu";
    }

    public double[] getBudgetRatios(Budget budget) {
        double[] budgetRatios = new double[3];

        //calculate the sum of fixed expenses
        budgetRatios[0] = budget.getFixedExpenses()
                .values()
                .stream()
                .mapToDouble(BigDecimal::doubleValue)
                .sum();

        //calculate the sum of flexible expenses
        budgetRatios[1] = budget.getFlexExpenses()
                .values()
                .stream()
                .mapToDouble(BigDecimal::doubleValue)
                .sum();

        //calculate the sum of savings goals
        budgetRatios[2] = budget.getSavingsGoals()
                .values()
                .stream()
                .mapToDouble(BigDecimal::doubleValue)
                .sum();

        //calculate the ratio of each type of expense based on the monthly income
        for(int i = 0; i < budgetRatios.length; i++) {
            budgetRatios[i] = budgetRatios[i] / budget.getMonthlyIncome().doubleValue();
        }

        return budgetRatios;
    }

    public void updateMonthlyIncome(User user) {
        Scanner input = new Scanner(System.in);

        System.out.println("UPDATE MONTHLY INCOME\n");
        System.out.println("   Please enter the new amount: ");
        user.getBudget().setMonthlyIncome(input.nextBigDecimal());

        budgetRepo.updateMonthlyIncome(user);
    }
}
