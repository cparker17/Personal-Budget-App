package pojos;

import java.util.HashMap;

public class ExpenseHistory {
    private HashMap<String, Expense> fixedExpenseHistory;
    private HashMap<String, Expense> flexExpenseHistory;

    public ExpenseHistory() {
        fixedExpenseHistory = new HashMap<>();
        flexExpenseHistory = new HashMap<>();
    }

    public HashMap<String, Expense> getFixedExpenseHistory() {
        return fixedExpenseHistory;
    }

    public void setFixedExpenseHistory(HashMap<String, Expense> fixedExpenseHistory) {
        this.fixedExpenseHistory = fixedExpenseHistory;
    }

    public HashMap<String, Expense> getFlexExpenseHistory() {
        return flexExpenseHistory;
    }

    public void setFlexExpenseHistory(HashMap<String, Expense> flexExpenseHistory) {
        this.flexExpenseHistory = flexExpenseHistory;
    }

    public void addExpense(Expense expense) {
        if (expense.getExpenseType().equals("fixed_exp")) {
            fixedExpenseHistory.put(expense.getCategory(), expense);
        }
        else {
            flexExpenseHistory.put(expense.getCategory(), expense);
        }
    }
}
