package repository;

import pojos.*;
import java.sql.*;

public class UserRepository {
    public void create(User user) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            statement.execute("INSERT INTO user (username, password, first_name, last_name, street_address," +
                    " city, state, zip_code," +
                    " email) VALUES('" + user.getUserName() + "', '" + user.getPassword() + "', '" +
                    user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getStreet() + "', '" + user.getCity() + "', '"  + user.getState() + "', '" + user.getZip() +
                    "', '" + user.getEmail() + "');");
            resultSet = statement.executeQuery("SELECT user_id FROM user WHERE userName = '" + user.getUserName() + "';");
            resultSet.next();
            user.setUserID(resultSet.getInt("user_id"));
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

    public void read(User user) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM user WHERE user_id = " + user.getUserID() + ";");

            resultSet.next();
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setStreet(resultSet.getString("street_address"));
            user.setCity(resultSet.getString("city"));
            user.setState(resultSet.getString("state"));
            user.setZip(resultSet.getInt("zip_code"));
            user.setEmail(resultSet.getString("email"));
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
    }

    public boolean isUsernameTaken(String username) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT username FROM user;");
            while(resultSet.next()) {
                if (username == resultSet.getString("username")) {
                    return true;
                }
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

        return false;
    }

    public boolean isPasswordCorrect(User user, String password) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT password FROM user WHERE username = '" + user.getUserName() + "';");
            while(resultSet.next()) {
                if (password == resultSet.getString("password")) {
                    return true;
                }
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

        return false;
    }


    public void updateName(User user) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();
            statement.execute("UPDATE user SET first_name = '" + user.getFirstName() + "', last_name = '" + user.getLastName() +
                    "' WHERE user_id = " + user.getUserID() + ";");
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

    public void updateStreet (User user) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            statement.execute("UPDATE user SET street = '" + user.getStreet() + "' WHERE user_id = '" + user.getUserID() + "';");
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

    public void updateCity(User user) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            statement.execute("UPDATE user SET city = '" + user.getCity() + "' WHERE user_id = '" + user.getUserID() + "';");
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

    public void updateState (User user) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            statement.execute("UPDATE user SET state = '" + user.getState() + "' WHERE user_id = '" + user.getUserID() + "';");
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

    public void updateZipCode(User user) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            statement.execute("UPDATE user SET zip_code = '" + user.getZip() + "' WHERE user_id = '" + user.getUserID() + "';");
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

    public void updateEmail (User user) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBAccess.getConnection();
            statement = connection.createStatement();

            statement.execute("UPDATE user SET email = '" + user.getEmail() + "' WHERE user_id = '" + user.getUserID() + "';");
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


