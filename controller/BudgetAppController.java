package controller;

import pojos.*;
import service.*;
import java.math.BigDecimal;

public class BudgetAppController {
    public void run() {
        AccountService accountService = new AccountService();
        BudgetService budgetService = new BudgetService();
        CustomGoalService customGoalService = new CustomGoalService();
        DepositService depositService = new DepositService();
        ExpenseService expenseService = new ExpenseService();
        LoanService loanService = new LoanService();
        MenuService menuService = new MenuService();
        UserService userService = new UserService();
        User user = new User();

        switch (menuService.displayStartUpMenu()) {
            case 1:
                //user will set up username, password, user info, and budget
                user = userService.userNamePasswordSetUp();
                userService.setUpUserInfo(user);
                accountService.setUpAccounts(user);
                loanService.loansSetUp(user);
                menuService.displayBudgetIntroMenu();
                budgetService.setUpBudget(user);
                budgetService.balanceBudget(user);
                break;
            case 2:
                //user will enter username and password and then move into main menu if correct
                user = userService.requestUsernamePassword();
                userService.loadUserData(user);
                accountService.loadAccountData(user);
                budgetService.loadBudgetData(user);
                customGoalService.loadCustomGoalData(user);
                depositService.loadDepositData(user);
                expenseService.loadExpenseData(user);
                loanService.loadLoanData(user);
                break;
            default:
                System.exit(0);
        }

        //display the main menu of application features and move into main functionality of application based
        //on user's menu selection
        do {
            switch (menuService.displayMainMenu()) {
                case 1:  //record expense
                    //check to make sure the user has a budget set up
                    if (user.getBudget().getFixedExpenses().isEmpty() || user.getBudget().getFlexExpenses().isEmpty() ||
                            user.getBudget().getSavingsGoals().isEmpty() ) {
                        System.out.println("You haven't set up your budget yet.  We need to get that set up before we can " +
                                "record an expense.");
                        budgetService.setUpBudget(user);
                    }

                    //once user's budget is set up, they can record an expense
                    Expense expense = expenseService.recordExpense(user);

                    if (expense != null) {
                        //update loan balance when user adds a loan payment expense
                        if (expense.getCategory().equals("Car Payment")) {
                            loanService.updateLoanBalance(user, expense.getAmount(), "Auto Loan");
                        }
                        if (expense.getCategory().equals("Mortgage")) {
                            loanService.updateLoanBalance(user, expense.getAmount(), "Mortgage Loan");
                        }
                        //update the user's checking account by subtracting the amount of the expense from their account balance
                        accountService.updateAccountBalance(user, expense.getAmount().multiply(BigDecimal.valueOf(-1)), "Checking");
                    }
                    break;
                case 2:
                    Deposit deposit = depositService.recordDeposit(user);

                    if (deposit != null) {
                        accountService.updateAccountBalance(user, deposit.getAmount(), deposit.getAccountType());
                    }
                    break;
                case 3:
                    ReportService reportService = new ReportService();

                    switch (menuService.displayReportsMenu()) {
                        case 1:
                            reportService.runBudgetActualReport(user);
                            break;
                        case 2:
                            reportService.runAccountsSummaryReport(user);
                            break;
                        case 3:
                            reportService.runLoansSummaryReport(user);
                            break;
                        case 4:
                            reportService.runExpenseSummaryReport(user);
                            break;
                        case 5:
                            reportService.runDepositSummaryReport(user);
                            break;
                        default:
                            break;
                    }
                    break;
                case 4:
                    switch(menuService.displayCustomGoalsMenu()) {
                        case 1:
                            customGoalService.createCustomGoal(user);
                            break;
                        case 2:
                            customGoalService.modifyCustomGoal(user);
                            break;
                        case 3:
                            customGoalService.checkGoalStatus(user);
                            break;
                        default:
                            break;
                    }
                    break;
                case 5:
                    RetirementService retirementService = new RetirementService();
                    retirementService.runRetirementPlanning(user);
                    break;
                case 6:
                    userService.updateUserInfo(user);
                    break;
                case 7:
                    accountService.updateAccountsInfo(user);
                    break;
                case 8:
                    loanService.updateLoansInfo(user);
                    break;
                case 9:
                    budgetService.updateMonthlyIncome(user);
                    break;
                case 10:
                    budgetService.updateBudget(user);
                    budgetService.balanceBudget(user);
                    break;
                default:
                    System.exit(0);
            }
        } while (true);
    }
}
