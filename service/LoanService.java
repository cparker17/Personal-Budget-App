package service;

import pojos.Loan;
import pojos.User;
import repository.LoanRepository;

import java.math.BigDecimal;
import java.util.Scanner;

public class LoanService {
    LoanRepository loanRepo = new LoanRepository();

    public void loadLoanData(User user) {
        user.setUserLoans(loanRepo.read(user));
    }

    public void loansSetUp(User user) {
        Scanner input = new Scanner(System.in);
        Loan loan = new Loan(user.getUserID());
        int menuSelection;

        //asks user which loan they want to set up
        System.out.println("\nLet's get your loans set up:");
        System.out.println("   \nPlease select which type of loan you want to set up:\n");
        loan.setLoanType(promptLoanType());

        //get loan info from user
        promptLoanInfo(loan);
        loanRepo.create(loan);

    }

    public String updateLoansInfo(User user) {
        Loan loan = new Loan(user.getUserID());

        System.out.println("UPDATE LOAN INFORMATION MENU\n");
        System.out.println("   \nPlease select which type of loan you want to update:\n");
        loan.setLoanType(promptLoanType());

        //get loan info from user
        promptLoanInfo(loan);
        loanRepo.update(loan);

        return "previousMenu";
    }

    public void updateLoanBalance(User user, BigDecimal amount, String loanType) {
        for(Loan loan : user.getUserLoans()) {
            if(loan.getLoanType().equals(loanType)) {
                loan.setCurrentBal(loan.getCurrentBal().subtract(amount));
                loanRepo.updateBalance(loan);
            }
        }
    }

    public String promptLoanType() {
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("   1 - Mortgage Loan");
            System.out.println("   2 - Auto Loan");
            System.out.println("   3 - Student Loan");
            System.out.println("   4 - Other Loan");
            System.out.println("   5 - Return to Previous Menu\n");
            System.out.print("Enter menu selection: ");

            //returns the type of loan based on user input
            switch (input.nextInt()) {
                case 1:
                    return "Mortgage";
                case 2:
                    return "Auto";
                case 3:
                    return "Student";
                case 4:
                    return "Other";
                case 5:
                    return null;
                default:
                    System.out.println("Input entered out of bounds.  Please try again.");
            }
        }while(true);
    }

    public void promptLoanInfo(Loan loan) {
        Scanner input = new Scanner(System.in);

        //gets loan info from user
        System.out.println("  Please enter the following information:\n");
        System.out.print("Lender Name:  ");
        input.skip("\n");
        loan.setLenderName(input.next());
        System.out.print("Account #:  ");
        loan.setAcctNumber(input.nextInt());
        System.out.print("Starting Balance: $");
        loan.setStartingBal(input.nextBigDecimal());
        loan.setCurrentBal(loan.getStartingBal());
        System.out.print("Interest Rate: ");
        loan.setInterestRate(input.nextDouble() / 100);
        System.out.print("Monthly Payment Amount: $");
        loan.setMonthlyPaymentAmt(input.nextBigDecimal());
        System.out.print("Day of Month Payment Due: ");
        loan.setMonthlyPaymentDate(input.nextInt());
    }
}
