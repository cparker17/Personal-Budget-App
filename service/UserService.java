package service;

import pojos.User;
import repository.UserRepository;

import java.util.Scanner;

public class UserService {
    UserRepository userRepo = new UserRepository();

    public User userNamePasswordSetUp() {
        Scanner input = new Scanner((System.in));

        //will loop through asking user for a new username and password until they enter a username that does not exist
        System.out.println("Let's get you set up.  Please create a username and password: \n");
        System.out.print("   Username: ");
        String username = input.next();

        //loop through prompting for a username that doesn't exist already

        while (userRepo.isUsernameTaken(username)) {
            System.out.println("We're sorry, but that username is taken.  Please try again.\n");
            System.out.print("   Username: ");
            username = input.next();
        }

        //prompt user for a password
        System.out.print("   Password: ");
        String password = input.next();

        return new User(username, password);
    }

    public void setUpUserInfo(User user) {
        Scanner input = new Scanner(System.in);

        //prompt the user to set up their personal info
        System.out.println("\nPlease enter the following:  \n");
        System.out.print("   First Name: ");
        user.setFirstName(input.next());
        System.out.print("   Last Name: ");
        input.nextLine();
        user.setLastName(input.nextLine());
        System.out.print("   Street Address: ");
        user.setStreet(input.nextLine());
        System.out.print("   City: ");
        user.setCity(input.nextLine());
        System.out.print("   State: ");
        user.setState(input.nextLine());
        System.out.print("   Zip Code: ");
        user.setZip(input.nextInt());
        System.out.print("   Email: ");
        user.setEmail(input.next());

        userRepo.create(user);
    }

    public void loadUserData(User user) {
        userRepo.read(user);
    }

    public User requestUsernamePassword()  {
        Scanner input = new Scanner(System.in);
        User user = new User();

        //prompt user to enter their username and password and then verify
        do {
            System.out.println("\nPlease enter your username and password: \n");
            System.out.print("   Username: ");
            String username = input.next();
            System.out.print("   Password: ");
            String password = input.next();

            //verify and set username
            if (!userRepo.isUsernameTaken(username)) {
                System.out.println("Username entered incorrect.  Please try again.\n");
                continue;
            }
            user.setUserName(username);

            //verify and set password
            if (!userRepo.isPasswordCorrect(user, password)) {
                System.out.println("Password entered incorrect.  Please try again.");
                continue;
            }
            user.setPassword(password);

            return user;
        } while(true);
    }

    public void updateUserInfo(User user) {
        Scanner input = new Scanner(System.in);
        int userInfoCategory;

        boolean continueLoop;
        do {
            continueLoop = false;
            //requests from user which info item they want to update
            System.out.println("UPDATE USER INFORMATION MENU\n");
            System.out.println("   1 - Name");
            System.out.println("   2 - Street Address");
            System.out.println("   3 - City");
            System.out.println("   4 - State");
            System.out.println("   5 - Zip Code");
            System.out.println("   6 - Email");
            System.out.println("   7 - Return to Previous Menu\n");
            System.out.println("Enter menu selection: ");

            userInfoCategory = input.nextInt();

            //updates information for item selected by user
            switch (userInfoCategory) {
                case 1:
                    System.out.print("Please enter your first name: ");
                    user.setFirstName(input.next());
                    System.out.print("Please enter your last name: ");
                    user.setLastName(input.next());
                    userRepo.updateName(user);
                    break;
                case 2:
                    System.out.println("Please enter your street address: ");
                    user.setStreet(input.nextLine());
                    userRepo.updateStreet(user);
                    break;
                case 3:
                    System.out.println("Please enter your city: ");
                    user.setCity(input.nextLine());
                    userRepo.updateCity(user);
                    break;
                case 4:
                    System.out.println("Please enter your state: ");
                    user.setState(input.nextLine());
                    userRepo.updateState(user);
                    break;
                case 5:
                    System.out.println("Please enter your zip code: ");
                    user.setZip(input.nextInt());
                    userRepo.updateZipCode(user);
                    break;
                case 6:
                    System.out.println("Please enter your email: ");
                    user.setEmail(input.next());
                    userRepo.updateEmail(user);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Input entered out of bounds.  Please try again.");
                    continueLoop = true;
            }
        }while(continueLoop);
    }
}
