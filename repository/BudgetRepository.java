package repository;

import pojos.Budget;
import pojos.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Map;

public class BudgetRepository {
    public void create(Budget budget) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            //persist budgeted fixed expenses to DB
            for (Map.Entry entry : budget.getFixedExpenses().entrySet()) {
                String category = (String) entry.getKey();
                BigDecimal amount = (BigDecimal) entry.getValue();
                double amt = amount.doubleValue();
                statement.execute("INSERT INTO fixed_exp (user_id, category, amount) VALUES(" +
                        budget.getUserID() + ", '" + category + "' , " + amt + ");");
            }

            //persist budgeted flexible expenses to DB
            for (Map.Entry entry : budget.getFlexExpenses().entrySet()) {
                String category = (String) entry.getKey();
                BigDecimal amount = (BigDecimal) entry.getValue();
                double amt = amount.doubleValue();
                statement.execute("INSERT INTO flex_exp (user_id, category, amount) VALUES(" +
                        budget.getUserID() + ", '" + category + "' , " + amt + ");");
            }

            //persist budgeted savings goals to DB
            for (Map.Entry entry : budget.getSavingsGoals().entrySet()) {
                String category = (String) entry.getKey();
                BigDecimal amount = (BigDecimal) entry.getValue();
                double amt = amount.doubleValue();
                statement.execute("INSERT INTO savings_goals (user_id, category, amount) VALUES(" +
                        budget.getUserID() + ", '" + category + "' , " + amt + ");");
            }

            //persist budgeted monthly income to DB
            statement.execute("INSERT INTO budget (user_id, monthly_income) VALUES('" +
                    budget.getUserID() + "', '" + budget.getMonthlyIncome() + "');");

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

    public void update(User user, String expenseType, BigDecimal amount, String category) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            statement.execute("UPDATE " + expenseType + " SET amount = " + amount.doubleValue() +
                    " WHERE user_id = " + user.getUserID() + " and category = '" + category + "';");
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
    public void updateMonthlyIncome(User user) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            statement.execute("UPDATE budget SET monthly_income = " + user.getBudget().getMonthlyIncome() + " WHERE user_id = " +
                    user.getUserID() + ";");
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

    public Budget read(Budget budget) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            //get all fixed expenses for user from database
            resultSet = statement.executeQuery("SELECT * FROM fixed_exp WHERE user_id =" + budget.getUserID() + ";");
            while(resultSet.next()) {
                budget.getFixedExpenses().put(resultSet.getString("category"),
                        BigDecimal.valueOf(resultSet.getDouble("amount")));
            }
            //get all flexible expenses for user from database
            resultSet = statement.executeQuery("SELECT * FROM flex_Exp WHERE user_id =" + budget.getUserID() + ";");
            while(resultSet.next()) {
                budget.getFlexExpenses().put(resultSet.getString("category"),
                        BigDecimal.valueOf(resultSet.getDouble("amount")));
            }
            //get all savings goals for user from database
            resultSet = statement.executeQuery("SELECT * FROM savings_goals WHERE user_id =" + budget.getUserID() + ";");
            while(resultSet.next()) {
                budget.getSavingsGoals().put(resultSet.getString("category"),
                        BigDecimal.valueOf(resultSet.getDouble("amount")));
            }

            //get monthly income for user from database
            resultSet = statement.executeQuery("SELECT * FROM budget WHERE user_id =" + budget.getUserID() + ";");
            while(resultSet.next()) {
                budget.setMonthlyIncome(BigDecimal.valueOf(resultSet.getDouble("monthly_income")));
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

        return budget;
    }
}
