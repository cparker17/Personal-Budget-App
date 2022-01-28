### Personal Budget Application

**PROJECT DESCRIPTION**
   
The purpose of the application is to help the user manage their personal finances by means of creating and maintaining a budget.  Their budget will
include three types of expenses to track:
      
      -Fixed Expenses
      -Flexible Expenses
      -Savings Goals
      
Fixed expenses are things like a mortgage payment or car payment.  Flexible expenses are things like entertainment and groceries.  Savings goals 
are things like a college fund or retirement fund.

The application will help the user create and maintain a budget based on the 50/30/20 rule of personal finance.  This rule proposes that anyone's 
expenses based on their income should be of the following proportions:

      50% - Fixed Expenses
      30% - Flexible Expenses
      20% - Savings 
      
Furthemore, the application will aid the user in retirement planning.

The application is developed in Java and makes use of mySQL for pojos storage.

It is written in a way that the application can have multiple users.  Each user would have their own unique username and password for accessing 
their pojos.  Currentlty, the application UI is only set up with a terminal interface, but could be updated as a web application that could maintain 
multiple users.  

In the future I hope to add the following functionality that I consider essential and is currently missing:

      -Regarding loans the user has entered in the system, the applcation would calculate the new payoff date if the user were to contribute more each month.
      -The retirement planning function currently does not account for an average rate of return. This should be updated with an historical average.
      -The application doesn't allow the user to go in and change any expenses or deposits they have already entered in.  
      -The application only allows for one account of each type (ie checking account, auto loan, etc)
      -The application should allow the user to add additional expenses other than the single use of "Other."
      
Aside from the functionality, I hope to update the application with some form of a graphical user interface.  This could be a desktop version or in the form of
a website.  


**INSTALLING AND RUNNING THE APPLICATION**

Users of the application will need to be running Java 11 at a minimum along with the latest version of mySQL and mySQL Workbench.  Since the application can 
only run on a local machine at this time, here is an outline of the steps necessary to prepare your computer to run the application successfully:

1 - Set up a new schema in mySQL Workbench titled "budget."  Then set up all the tables and their foreign keys.  Please reach out to me via Slack or email 
and I can send you an image of the schema model.
2 - Download the source code for this project.
3 - Inside the "pojos" package you will find all of the classes that interface with the database.  You will need to change the database host, username, and 
password inside the CRUD methods of each class for your particular mySQL information.
4 - You will also need to make sure you have the proper Driver installed for mySQL.
5 - Then you can compile all of the source code through the terminal or preferred IDE.
3 - The application can be executed from the terminal by entering the command: java BudgetApp.class or from your preferred IDE.

**HOW TO USE THE APPLICATION**

The opening screen presents the start up menu where you can can select if you are a new user or an existing user.  If you select "New User" then the application 
will guide you through setting up a username and password.  The application will also verify that the username you selected is not already taken.  After setting up
a username and password the application will guide you through the rest of the set up process which includes gathering your personal information, setting up all 
of your accounts and loans, and then setting up your budget.  If the budget you set up is not in balance based on the 50/30/20 rule (see Project Description above), 
application will ask if you want to modify the budget before proceeding.  

Once everything is set up the user will be provided the following options via the Main Menu which is the core of application's funcionality:

1 - Record Expense
2 - Record Deposit
3 - View Reports
4 - Custom Goal Planning
5 - Retirement Planning
6 - Update User Information
7 - Update Account Information
8 - Update Loan Information
9 - Update Monthly Income
10 - Update Budget

The "View Reports" menu item will then provide the user with the following reporting options:

1 - Budget cs Actual
2 - Accounts Summary
3 - Loans Summary
4 - Expense Summary
5 - Deposit Summary

The Budget vs Actual, Expense Summary and Deposit Summary can be ran considering two time periods:

1 - Last Year
2 - Last Month



