package repository;

import pojos.CustomGoal;
import pojos.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class CustomGoalRepository extends DBAccess {
    public void create(CustomGoal customGoal) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            statement.execute("INSERT INTO custom_goals (name, amount, user_id) VALUES('" +
                    customGoal.getName() + "', " + customGoal.getAmountNeeded().doubleValue() + " , " + customGoal.getUserID() + ");");
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

    public ArrayList<CustomGoal> read(User user) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        ArrayList<CustomGoal> customGoals = new ArrayList<>();
        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT name, amount FROM custom_goals WHERE user_id =" + user.getUserID() + ";");

            while(resultSet.next()) {
                CustomGoal customGoal = new CustomGoal(user.getUserID());
                customGoal.setName(resultSet.getString("name"));
                customGoal.setAmountNeeded(BigDecimal.valueOf(resultSet.getInt("amount")));
                customGoals.add(customGoal);
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

        return customGoals;
    }

    public void updateAmount(CustomGoal customGoal) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            statement.execute("UPDATE custom_goals SET amount = " + customGoal.getAmountNeeded() +
                    " WHERE user_id = " + customGoal.getUserID() + " and name = " + customGoal.getName() + ";");
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
