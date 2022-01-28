package pojos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private int userID;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private int zip;
    private String email;
    private Budget budget;
    private ArrayList<CustomGoal> customGoals;
    private ArrayList<Account> userAccounts;
    private ArrayList<Loan> userLoans;
    private ArrayList<Expense> userExpenses;
    private ArrayList<Deposit> userDeposits;

    public User() {
        userAccounts = new ArrayList<>();
        userLoans = new ArrayList<>();
        userExpenses = new ArrayList<>();
        userDeposits = new ArrayList<>();
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUserID() {
        return userID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public int getUserID(int loginID) {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public ArrayList<Account> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(ArrayList<Account> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public ArrayList<Loan> getUserLoans() {
        return userLoans;
    }

    public void setUserLoans(ArrayList<Loan> userLoans) {
        this.userLoans = userLoans;
    }

    public ArrayList<Expense> getUserExpenses() {
        return userExpenses;
    }

    public void setUserExpenses(ArrayList<Expense> userExpenses) {
        this.userExpenses = userExpenses;
    }

    public ArrayList<Deposit> getUserDeposits() {
        return userDeposits;
    }

    public void setUserDeposits(ArrayList<Deposit> userDeposits) {
        this.userDeposits = userDeposits;
    }

    public ArrayList<CustomGoal> getCustomGoals() {
        return customGoals;
    }

    public void setCustomGoals(ArrayList<CustomGoal> customGoals) {
        this.customGoals = customGoals;
    }
}
