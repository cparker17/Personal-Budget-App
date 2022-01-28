package pojos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BudgetActualReport {
    private Budget budget;
    private HashMap<String, BigDecimal> budgetFixedExpTotals;
    private HashMap<String, BigDecimal> budgetFlexExpTotals;
    private HashMap<String, BigDecimal> budgetSavTotals;
    private ExpenseHistory expenseHistory;
    private HashMap<String, BigDecimal> fixedExpTotals;
    private HashMap<String, BigDecimal> flexExpTotals;
    private DepositHistory depositHistory;
    private HashMap<String, BigDecimal> depositTotals;

    public BudgetActualReport(Budget budget) {
        this.budget = budget;
        expenseHistory = new ExpenseHistory();

        fixedExpTotals = new HashMap<>();
        for(Map.Entry entry : budget.getFixedExpenses().entrySet()) {
            fixedExpTotals.put((String) entry.getKey(), BigDecimal.valueOf(0));
        }

        flexExpTotals = new HashMap<>();
        for(Map.Entry entry : budget.getFlexExpenses().entrySet()) {
            flexExpTotals.put((String) entry.getKey(), BigDecimal.valueOf(0));
        }

        depositHistory = new DepositHistory();
        depositTotals = new HashMap<>();
        for(Map.Entry entry : budget.getSavingsGoals().entrySet()) {
            depositTotals.put((String) entry.getKey(), BigDecimal.valueOf(0));
        }
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public ExpenseHistory getExpenseHistory() {
        return expenseHistory;
    }

    public void setExpenseHistory(ExpenseHistory expenseHistory) {
        this.expenseHistory = expenseHistory;
    }

    public HashMap<String, BigDecimal> getFixedExpTotals() {
        return fixedExpTotals;
    }

    public void setFixedExpTotals(HashMap<String, BigDecimal> fixedExpTotals) {
        this.fixedExpTotals = fixedExpTotals;
    }

    public HashMap<String, BigDecimal> getFlexExpTotals() {
        return flexExpTotals;
    }

    public void setFlexExpTotals(HashMap<String, BigDecimal> flexExpTotals) {
        this.flexExpTotals = flexExpTotals;
    }

    public HashMap<String, BigDecimal> getDepositTotals() {
        return depositTotals;
    }

    public void setDepositTotals(HashMap<String, BigDecimal> depositTotals) {
        this.depositTotals = depositTotals;
    }

    public void addExpense(Expense expense){
        expenseHistory.addExpense(expense);
        String expenseCategory = expense.getCategory();
        BigDecimal currentTotal;

        if (expenseCategory.equals("fixed_exp")) {
            currentTotal = fixedExpTotals.get(expenseCategory);
            fixedExpTotals.replace(expenseCategory, currentTotal.add(expense.getAmount()));
        }
        else {
            currentTotal = flexExpTotals.get(expenseCategory);
            flexExpTotals.replace(expenseCategory, currentTotal.add(expense.getAmount()));
        }
    }

    public void addDeposit(Deposit deposit) {
        depositHistory.addDeposit(deposit);
        String depositType = deposit.getAccountType();
        BigDecimal currentTotal;

        currentTotal = depositTotals.get(depositType);
        depositTotals.replace(depositType, currentTotal.add(deposit.getAmount()));
    }

    public void totalBudgetCategories(int timePeriod) {
        BigDecimal total;

        //sums all of the fixed expenses for given time period
        for(Map.Entry fixedExp : budget.getFixedExpenses().entrySet()) {
            total = (BigDecimal) fixedExp.getValue();
            total = total.multiply(BigDecimal.valueOf(timePeriod));
            budgetFixedExpTotals.put((String) fixedExp.getKey(),total);
        }

        //sums all of the flexible expenses for given time period
        for(Map.Entry flexExp : budget.getFlexExpenses().entrySet()) {
            total = (BigDecimal) flexExp.getValue();
            total = total.multiply(BigDecimal.valueOf(timePeriod));
            budgetFixedExpTotals.put((String) flexExp.getKey(),total);
        }

        //sums all of the savings for given time period
        for(Map.Entry saving : budget.getSavingsGoals().entrySet()) {
            total = (BigDecimal) saving.getValue();
            total = total.multiply(BigDecimal.valueOf(timePeriod));
            budgetFixedExpTotals.put((String) saving.getKey(),total);
        }
    }
}
