package service;

import pojos.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class ReportService {
    public void runBudgetActualReport(User user) {
        BudgetActualReport bar = new BudgetActualReport(user.getBudget());

        switch (promptTimePeriod()) {
            case 1:
                bar.totalBudgetCategories(12);
                Date lastYear = Date.from(ZonedDateTime.now().minusMonths(12).toInstant());
                for (Expense expense : user.getUserExpenses()) {
                    if (expense.getDateAdded().compareTo(lastYear) > 0) {
                        bar.addExpense(expense);
                    }
                }
                for (Deposit deposit : user.getUserDeposits()) {
                    if (deposit.getDateAdded().compareTo(lastYear) > 0) {
                        bar.addDeposit(deposit);
                    }
                }
                break;
            case 2:
                bar.totalBudgetCategories(1);
                Date lastMonth = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
                for (Expense expense : user.getUserExpenses()) {
                    if (expense.getDateAdded().compareTo(lastMonth) > 0) {
                        bar.addExpense(expense);
                    }
                }
                for (Deposit deposit : user.getUserDeposits()) {
                    if (deposit.getDateAdded().compareTo(lastMonth) > 0) {
                        bar.addDeposit(deposit);
                    }
                }
                break;
        }

        System.out.printf("%58s %35s", "BUDGET", "ACTUAL");

        //prints out budget vs actuals for all fixed expenses based on time period selected by user
        System.out.println("\nFIXED EXPENSES");
        for(Map.Entry budgetEntry : bar.getBudget().getFixedExpenses().entrySet()) {
            System.out.printf("%25s" , budgetEntry.getKey());
            System.out.printf("%35.2f" , (BigDecimal) budgetEntry.getValue());
            for(Map.Entry expenseEntry : bar.getFixedExpTotals().entrySet()) {
                if(budgetEntry.getKey().equals(expenseEntry.getKey())) {
                    System.out.printf("%35.2f", (BigDecimal) expenseEntry.getValue());
                    System.out.println();
                }
            }
        }

        //prints out budget vs actuals for all flexible expenses based on time period selected by user
        System.out.println("\nFLEXIBLE EXPENSES");
        for(Map.Entry budgetEntry : bar.getBudget().getFlexExpenses().entrySet()) {
            System.out.printf("%25s" , budgetEntry.getKey());
            System.out.printf("%35.2f" , (BigDecimal) budgetEntry.getValue());
            for(Map.Entry expenseEntry : bar.getFlexExpTotals().entrySet()) {
                if(budgetEntry.getKey().equals(expenseEntry.getKey())) {
                    System.out.printf("%35.2f", (BigDecimal) expenseEntry.getValue());
                    System.out.println();
                }
            }
        }

        //prints out budget vs actuals for all savings based on time period selected by user
        System.out.println("\nSAVINGS GOALS");
        for(Map.Entry budgetEntry : bar.getBudget().getSavingsGoals().entrySet()) {
            System.out.printf("%25s" , budgetEntry.getKey());
            System.out.printf("%35.2f" , (BigDecimal) budgetEntry.getValue());
            for(Map.Entry depositEntry : bar.getDepositTotals().entrySet()) {
                if(budgetEntry.getKey().equals((depositEntry.getKey()))) {
                    System.out.printf("%35.2f", (BigDecimal) depositEntry.getValue());
                    System.out.println();
                }
            }
        }
    }

    public void runAccountsSummaryReport(User user) {
        //prints out summary of all open accounts
        for(Account account : user.getUserAccounts()) {
            System.out.println("\n" + account.getAccountType() + " Account:");
            System.out.println("Account# " + account.getAcctNumber());
            System.out.println("Current Balance = " + account.getCurrentBalance() + "\n");
        }
    }

    public void runLoansSummaryReport(User user) {
        //prints out summary of all open loans
        for(Loan loan : user.getUserLoans()) {
            System.out.println(loan.getLoanType() + " Loan");
            System.out.println("Account# " + loan.getAcctNumber());
            System.out.println("Current Balance = " + loan.getCurrentBal() + "\n");
        }
    }

    public void runExpenseSummaryReport(User user) {
        ExpenseHistory expenseHistory = new ExpenseHistory();

        switch (promptTimePeriod()) {
            case 1:
                for(Expense expense : user.getUserExpenses()) {
                    expenseHistory.addExpense(expense);
                }
                break;
            case 2:
                Date lastYear = Date.from(ZonedDateTime.now().minusMonths(12).toInstant());
                for (Expense expense : user.getUserExpenses()) {
                    if (expense.getDateAdded().compareTo(lastYear) > 0) {
                        expenseHistory.addExpense(expense);
                    }
                }
                break;
            case 3:
                Date lastMonth = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
                for (Expense expense : user.getUserExpenses()) {
                    if (expense.getDateAdded().compareTo(lastMonth) > 0) {
                        expenseHistory.addExpense(expense);
                    }
                }
                break;
        }

        //prints out all fixed expenses recorded
        System.out.println("FIXED EXPENSES\n");
        expenseHistory.getFixedExpenseHistory().forEach((k, v) -> System.out.println(k +" \n   " +
                v.getDateAdded() + " - " + v.getAmount()));

        //prints out all flexible expenses recorded
        System.out.println("FLEXIBLE EXPENSES\n");
        expenseHistory.getFlexExpenseHistory().forEach((k, v) -> System.out.println(k +" \n   " +
                v.getDateAdded() + " - " + v.getAmount()));
    }

    public void runDepositSummaryReport(User user) {
        DepositHistory depositHistory = new DepositHistory();

        switch (promptTimePeriod()) {
            case 1:
                for(Deposit deposit : user.getUserDeposits()) {
                    depositHistory.addDeposit(deposit);
                }
                break;
            case 2:
                Date lastYear = Date.from(ZonedDateTime.now().minusMonths(12).toInstant());
                for (Deposit deposit : user.getUserDeposits()) {
                    if (deposit.getDateAdded().compareTo(lastYear) > 0) {
                        depositHistory.addDeposit(deposit);
                    }
                }
                break;
            case 3:
                Date lastMonth = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
                for (Deposit deposit : user.getUserDeposits()) {
                    if (deposit.getDateAdded().compareTo(lastMonth) > 0) {
                        depositHistory.addDeposit(deposit);
                    }
                }
                break;
        }

        //prints out all checking deposits
        System.out.println("\nCHECKING ACCOUNT");
        if(depositHistory.getCheckingDeposits().size() == 0) {
            System.out.println("   No deposits during time period.");
        }
        for(Deposit deposit : depositHistory.getCheckingDeposits()) {
            System.out.println("   " + deposit.getDateAdded() + " - " + deposit.getAmount());
        }

        //prints out all savings deposits
        System.out.println("\nSAVINGS ACCOUNT");
        if(depositHistory.getSavingsDeposits().size() == 0) {
            System.out.println("   No deposits during time period.");
        }
        for(Deposit deposit : depositHistory.getSavingsDeposits()) {
            System.out.println("   " + deposit.getDateAdded() + " - " + deposit.getAmount());
        }

        //prints out all college fund deposits
        System.out.println("\nCOLLEGE FUND");
        if(depositHistory.getCollegeDeposits().size() == 0) {
            System.out.println("   No deposits during time period.");
        }
        for(Deposit deposit : depositHistory.getCollegeDeposits()) {
            System.out.println("   " + deposit.getDateAdded() + " - " + deposit.getAmount());
        }

        //prints out all retirement fund deposits
        System.out.println("\nRETIREMENT FUND");
        if(depositHistory.getRetirementDeposits().size() == 0) {
            System.out.println("   No deposits during time period.");
        }
        for(Deposit deposit : depositHistory.getRetirementDeposits()) {
            System.out.println("   " + deposit.getDateAdded() + " - " + deposit.getAmount());
        }
    }

    public int promptTimePeriod() {
        Scanner input = new Scanner(System.in);
        int menuSelection;

        //gets the time period the user wants to see a report for
        boolean continueLoop;
        do {
            continueLoop = false;
            System.out.println("Please select which time period you would like to see the report for: \n");
            System.out.println("   1 - Last Year");
            System.out.println("   2 - Last Month\n");
            System.out.print("Enter selection:  ");

            menuSelection = input.nextInt();
            if (menuSelection < 1 || menuSelection > 3) {
                System.out.println("Input entered out of bounds.  Please try again.");
                continueLoop = true;
            }
        }while(continueLoop);

        return menuSelection;
    }
}
