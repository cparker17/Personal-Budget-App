package pojos;

import java.math.BigDecimal;

public class Loan {
    private String lenderName;
    private int acctNumber;
    private BigDecimal startingBal;
    private double interestRate;
    private BigDecimal currentBal;
    private int monthlyPaymentDate;
    private BigDecimal monthlyPaymentAmt;
    private String loanType;
    private int userID;

    public Loan() {}

    public Loan(int userID) {
        this.userID = userID;
    }

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public int getAcctNumber() {
        return acctNumber;
    }

    public void setAcctNumber(int acctNumber) {
        this.acctNumber = acctNumber;
    }

    public BigDecimal getStartingBal() {
        return startingBal;
    }

    public void setStartingBal(BigDecimal startingBal) {
        this.startingBal = startingBal;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getCurrentBal() {
        return currentBal;
    }

    public void setCurrentBal(BigDecimal currentBal) {
        this.currentBal = currentBal;
    }

    public int getMonthlyPaymentDate() {
        return monthlyPaymentDate;
    }

    public void setMonthlyPaymentDate(int monthlyPaymentDate) {
        this.monthlyPaymentDate = monthlyPaymentDate;
    }

    public BigDecimal getMonthlyPaymentAmt() {
        return monthlyPaymentAmt;
    }

    public void setMonthlyPaymentAmt(BigDecimal monthlyPaymentAmt) {
        this.monthlyPaymentAmt = monthlyPaymentAmt;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
