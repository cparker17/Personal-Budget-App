package repository;

import pojos.Loan;
import pojos.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class LoanRepository extends DBAccess {
    public void create(Loan loan) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            statement.execute("INSERT INTO loan (user_id, type, lender_name, acct_number, start_balance, current_balance, " +
                    "interest_rate, monthly_due_date, monthly_payment) VALUES(" + loan.getUserID() + ", '" + loan.getLoanType() + "', '" +
                    loan.getLenderName() + "', " + loan.getAcctNumber() + ", " + loan.getStartingBal() +  ", " + loan.getCurrentBal() + ", " + loan.getInterestRate() +
                    ", " + loan.getMonthlyPaymentDate() + ", " + loan.getMonthlyPaymentAmt() + ");");
        } catch (SQLException exc) {
            System.out.println("Exception occurred");
            exc.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Exception occurred - driver not found on classpath");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Loan> read(User user) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        ArrayList<Loan> loans = new ArrayList<>();
        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM account WHERE user_id = " + user.getUserID() + ";");

            while(resultSet.next()) {
                Loan loan = new Loan(user.getUserID());
                loan.setLenderName(resultSet.getString("lender_name"));
                loan.setAcctNumber(resultSet.getInt("acct_number"));
                loan.setStartingBal(BigDecimal.valueOf(resultSet.getDouble("start_balance")));
                loan.setCurrentBal(BigDecimal.valueOf(resultSet.getDouble("current_balance")));
                loan.setInterestRate(resultSet.getDouble("interest_rate"));
                loan.setMonthlyPaymentAmt(BigDecimal.valueOf(resultSet.getDouble("monthly_payment")));
                loan.setMonthlyPaymentDate(resultSet.getInt("monthly_due_date"));
                loans.add(loan);
            }
        } catch (SQLException exc) {
            System.out.println("Exception occurred");
            exc.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Exception occurred - driver not found on classpath");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                resultSet.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return loans;
    }

    public void update(Loan loan) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            statement.execute("UPDATE loan SET lender_name = '" + loan.getLenderName() + "', acct_number = " + loan.getAcctNumber() +
                    ", start_balance = " + loan.getStartingBal().doubleValue() + ", interest_rate = " + loan.getInterestRate() + ", monthly_due_date = " +
                    loan.getMonthlyPaymentDate() + ", monthly_payment = " + loan.getMonthlyPaymentAmt().doubleValue() +
                    ", current_balance = " + loan.getCurrentBal().doubleValue() + " WHERE user_id = " + loan.getUserID() +
                    " and type = '" + loan.getLoanType() + "';");
        } catch (SQLException exc) {
            System.out.println("Exception occurred");
            exc.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Exception occurred - driver not found on classpath");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateBalance(Loan loan) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            statement.execute("UPDATE loan SET current_balance = " + loan.getCurrentBal().doubleValue() + " WHERE user_id = " + loan.getUserID() +
                    " and type = '" + loan.getLoanType() + "';");
        } catch (SQLException exc) {
            System.out.println("Exception occurred");
            exc.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Exception occurred - driver not found on classpath");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
