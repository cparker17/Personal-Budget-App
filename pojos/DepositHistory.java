package pojos;

import java.util.ArrayList;

public class DepositHistory {
    private ArrayList<Deposit> checkingDeposits;
    private ArrayList<Deposit> savingsDeposits;
    private ArrayList<Deposit> collegeDeposits;
    private ArrayList<Deposit> retirementDeposits;

    public DepositHistory() {
        checkingDeposits = new ArrayList<>();
        savingsDeposits = new ArrayList<>();
        collegeDeposits = new ArrayList<>();
        retirementDeposits = new ArrayList<>();
    }

    public ArrayList<Deposit> getCheckingDeposits() {
        return checkingDeposits;
    }

    public void setCheckingDeposits(ArrayList<Deposit> checkingDeposits) {
        this.checkingDeposits = checkingDeposits;
    }

    public ArrayList<Deposit> getSavingsDeposits() {
        return savingsDeposits;
    }

    public void setSavingsDeposits(ArrayList<Deposit> savingsDeposits) {
        this.savingsDeposits = savingsDeposits;
    }

    public ArrayList<Deposit> getCollegeDeposits() {
        return collegeDeposits;
    }

    public void setCollegeDeposits(ArrayList<Deposit> collegeDeposits) {
        this.collegeDeposits = collegeDeposits;
    }

    public ArrayList<Deposit> getRetirementDeposits() {
        return retirementDeposits;
    }

    public void setRetirementDeposits(ArrayList<Deposit> retirementDeposits) {
        this.retirementDeposits = retirementDeposits;
    }

    public void addDeposit(Deposit deposit) {
        switch (deposit.getAccountType()) {
            case "Checking":
                checkingDeposits.add(deposit);
                break;
            case "Savings":
                savingsDeposits.add(deposit);
                break;
            case "College":
                collegeDeposits.add(deposit);
                break;
            default:
                retirementDeposits.add(deposit);
        }
    }
}
