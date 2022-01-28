package pojos;

import java.math.BigDecimal;
import java.util.HashMap;

public class Budget {
    private BigDecimal monthlyIncome;
    private HashMap<String, BigDecimal> fixedExpenses;
    private HashMap<String, BigDecimal> flexExpenses;
    private HashMap<String, BigDecimal> savingsGoals;
    private int budgetID;
    private HashMap<String, BigDecimal> budgetItems;
    private int userID;

    public Budget() {
        fixedExpenses = new HashMap<>();
        flexExpenses = new HashMap<>();
        savingsGoals = new HashMap<>();
    }

    public Budget(int userID) {
        this.userID = userID;
        fixedExpenses = new HashMap<>();
        flexExpenses = new HashMap<>();
        savingsGoals = new HashMap<>();
    }

    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public HashMap<String, BigDecimal> getFixedExpenses() {
        return fixedExpenses;
    }

    public void setFixedExpenses(HashMap<String, BigDecimal> fixedExpenses) {
        this.fixedExpenses = fixedExpenses;
    }

    public HashMap<String, BigDecimal> getFlexExpenses() {
        return flexExpenses;
    }

    public void setFlexExpenses(HashMap<String, BigDecimal> flexExpenses) {
        this.flexExpenses = flexExpenses;
    }

    public HashMap<String, BigDecimal> getSavingsGoals() {
        return savingsGoals;
    }

    public void setSavingsGoals(HashMap<String, BigDecimal> savingsGoals) {
        this.savingsGoals = savingsGoals;
    }

    public int getBudgetID() {
        return budgetID;
    }

    public void setBudgetID(int budgetID) {
        this.budgetID = budgetID;
    }

    public HashMap<String, BigDecimal> getBudgetItems() {
        return budgetItems;
    }

    public void setBudgetItems(HashMap<String, BigDecimal> budgetItems) {
        this.budgetItems = budgetItems;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
