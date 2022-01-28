package service;

import pojos.Deposit;
import pojos.User;
import repository.DepositRepository;
import java.util.Scanner;

public class DepositService {
    DepositRepository depositRepo = new DepositRepository();

    public void loadDepositData(User user) {
        user.setUserDeposits(depositRepo.read(user));
    }

    public Deposit recordDeposit(User user) {
        Scanner input = new Scanner(System.in);
        Deposit deposit = new Deposit(user.getUserID());

        //asks user which type of account they want to deposit into

        boolean continueLoop;
        do {
            continueLoop = false;
            System.out.println("\nRECORD DEPOSIT MENU\n");
            System.out.println("   1 - Checking Account");
            System.out.println("   2 - Savings Account");
            System.out.println("   3 - College Fund");
            System.out.println("   4 - Retirement Fund");
            System.out.println("   5 - Return to previous menu\n");
            System.out.print("Enter menu selection: ");

            switch (input.nextInt()) {
                case 1:
                    deposit.setAccountType("Checking");
                    break;
                case 2:
                    deposit.setAccountType("Savings");
                    break;
                case 3:
                    deposit.setAccountType("College");
                    break;
                case 4:
                    deposit.setAccountType("Retirement");
                    break;
                case 5:
                    return null;
                default:
                    System.out.println("\nInput entered out of bounds.  Please try again.");
                    continueLoop = true;
            }
        }while(continueLoop);

        System.out.print("Please enter the amount you would like to deposit: $");
        deposit.setAmount(input.nextBigDecimal());

        depositRepo.create(deposit);

        return deposit;
    }
}
