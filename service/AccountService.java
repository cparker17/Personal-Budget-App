package service;

import pojos.Account;
import pojos.User;
import repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Scanner;

public class AccountService {
    AccountRepository accountRepo = new AccountRepository();

    public void loadAccountData(User user) {
        user.setUserAccounts(accountRepo.read(user));
    }

    public void setUpAccounts(User user) {
        Scanner input = new Scanner(System.in);
        int menuSelection;

        Account account = new Account(user.getUserID());

        //asks user which account they want to set up
        System.out.println("\nLet's get your accounts set up:");

        boolean continueLoop;
        do {
            continueLoop = false;
            System.out.println("   \nPlease select which type of account you want to set up:\n");

            //get the account type from the user
            String accountType = promptAccountType();
            if (accountType == null) {
                return;
            } else {
                account.setAccountType(accountType);
            }

            //gets account info from user to create the account with
            promptAccountInfo(account);
            accountRepo.create(account);

            boolean continueLoop2;
            do {
                continueLoop2 = false;
                System.out.println("Would you like to set up another account?\n");
                System.out.println("   1 - Yes");
                System.out.println("   2 - No");
                System.out.print("Enter selection: ");
                menuSelection = input.nextInt();
                if (menuSelection == 1) {
                    continueLoop = true;
                } else if (menuSelection == 2) {
                    break;
                } else {
                    System.out.println("Input entered out of bounds.  Please try again.");
                    continueLoop2 = true;
                }
            }while(continueLoop2);

        }while(continueLoop);
    }

    public void updateAccountsInfo(User user) {
        Scanner input = new Scanner(System.in);
        Account account = new Account(user.getUserID());
        //requests from user which type of account they want to update
        System.out.println("Please select which type of account you want to update:\n");
        String accountType = promptAccountType();
        if (accountType == null) {
            return;
        }
        else {
            account.setAccountType(accountType);
        }

        //get account info from user to update account with
        promptAccountInfo(account);
        accountRepo.update(account);
    }

    public String promptAccountType() {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("   1 - Checking Account");
            System.out.println("   2 - Savings Account");
            System.out.println("   3 - College Fund");
            System.out.println("   4 - Retirement Fund");
            System.out.println("   5 - Return to Previous Menu\n");
            System.out.println("Enter menu selection: ");

            //sets the type of account based on user input
            switch (input.nextInt()) {
                case 1:
                    return "Checking";
                case 2:
                    return "Savings";
                case 3:
                    return "College Fund";
                case 4:
                    return "Retirement Fund";
                case 5:
                    return null;
                default:
                    System.out.println("Input entered out of bounds.  Please try again.\n");
            }
        }while(true);
    }

    public Account promptAccountInfo(Account account) {
        Scanner input = new Scanner(System.in);

        //cycles through requesting all data from user to set up new account
        System.out.println("Please enter the new bank name, account number, and starting balance: ");
        System.out.print("   Bank Name: ");
        account.setBankName(input.next());
        System.out.print("   Account Number: ");
        account.setAcctNumber(input.nextInt());
        System.out.print("   Starting Balance: $");
        account.setStartBalance(input.nextBigDecimal());
        account.setCurrentBalance(account.getStartBalance());

        return account;
    }

    public void updateAccountBalance(User user, BigDecimal amount, String accountType) {
        for(Account account : user.getUserAccounts()) {
           if(account.getAccountType().equals(accountType)) {
               account.setCurrentBalance(account.getCurrentBalance().add(amount));
               accountRepo.updateCurrentBalance(account);
           }
       }
    }
}
