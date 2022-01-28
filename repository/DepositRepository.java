package repository;

import pojos.Deposit;
import pojos.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class DepositRepository extends DBAccess {
    public void create(Deposit deposit) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            statement.execute("INSERT INTO deposit (user_id, amount, type) VALUES(" + deposit.getUserID() + ", " + deposit.getAmount() +
                    ", '" + deposit.getAccountType() + "');");

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

    public ArrayList<Deposit> read(User user) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        ArrayList<Deposit> deposits = new ArrayList<>();
        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT amount, date FROM acct_deposit WHERE user_id =" +
                    user.getUserID() + ";");

            while(resultSet.next()) {
                Deposit deposit = new Deposit(user.getUserID());
                deposit.setAmount(BigDecimal.valueOf(resultSet.getDouble("amount")));
                deposit.setDateAdded(resultSet.getDate("date"));
                deposits.add(deposit);
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

        return deposits;
    }
}
