package repository;

import pojos.Account;
import pojos.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class AccountRepository {
    public void create(Account account) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            statement.execute("INSERT INTO account (user_id, type, bank_name, acct_number, start_balance, current_balance) VALUES(" + account.getUserID() +
                    ", '" + account.getAccountType() + "', '" + account.getBankName() + "', " + account.getAcctNumber() + " , " + account.getStartBalance().doubleValue() + " , " +
                    account.getCurrentBalance().doubleValue() + ");");
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

    public ArrayList<Account> read(User user) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        ArrayList<Account> accounts = new ArrayList();
        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT bank_name, acct_number, start_balance, " +
                    "current_balance FROM account WHERE user_id = " + user.getUserID() + ";");

            while(resultSet.next()) {
                Account account = new Account(user.getUserID());
                account.setBankName(resultSet.getString("bank_name"));
                account.setAcctNumber(resultSet.getInt("acct_number"));
                account.setStartBalance(BigDecimal.valueOf(resultSet.getDouble("start_balance")));
                account.setCurrentBalance(BigDecimal.valueOf(resultSet.getDouble("current_balance")));
                accounts.add(account);
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

        return accounts;
    }

    public void update(Account account) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            statement.execute("UPDATE account SET bank_name = '" + account.getBankName() + "', acct_number = " + account.getAcctNumber() +
                    ", start_balance = " + account.getStartBalance() + ", current_balance = " + account.getCurrentBalance() +
                    " WHERE user_id = " + account.getUserID() + " and type = '" + account.getAccountType() + "';");
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

    public void updateCurrentBalance(Account account) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            statement.execute("UPDATE account SET current_balance = " + account.getCurrentBalance() +
                    " WHERE user_id = " + account.getUserID() + " and type = '" + account.getAccountType() + "';");
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
