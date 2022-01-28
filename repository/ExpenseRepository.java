package repository;

import pojos.Expense;
import pojos.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ExpenseRepository extends DBAccess {
    public void recordExpense(Expense expense) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();
            statement.execute("INSERT INTO expense (user_id, type, category, amount) VALUES(" + expense.getUserID() + ", '" +
                    expense.getExpenseType() + "', '" + expense.getCategory() + "', " + expense.getAmount() + ");");
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

    public ArrayList<Expense> read(User user) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        ArrayList<Expense> expenses = new ArrayList<>();
        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT amount, date FROM expense WHERE user_id = '"
                    + user.getUserID() + "';");

            while (resultSet.next()) {
                Expense expense = new Expense(user.getUserID());
                expense.setAmount(BigDecimal.valueOf(resultSet.getDouble("amount")));
                expense.setDateAdded(resultSet.getDate("date"));
                expenses.add(expense);
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

        return expenses;
    }
}
