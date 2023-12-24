package com.bellintegrator.kubrak.task_6.service;

import com.bellintegrator.kubrak.task_6.model.User;

import java.sql.*;

public class Service {

    private static final String query = "SELECT customer.user_email.login AS login, customer.user_email.email AS email, customer.user_email.date AS date, customer.user_password.password AS password " +
            "FROM customer.user_email " +
            "JOIN customer.user_password " +
            "ON customer.user_email.login=customer.user_password.login " +
            "WHERE customer.user_email.login = ";

    public static User getUser(String login) throws SQLException {

        Connection connection = open();

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query + "'" + login + "';");
        connection.close();
        rs.next();
        User user = new User(rs.getString("login"), rs.getString("email"), rs.getString("password"));
        user.date = rs.getTimestamp("date").toLocalDateTime();
        statement.close();
        return user;

    }

    public static int saveUser(User user) throws SQLException {
        try (Connection connection = open();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO customer.user_email VALUES (?,?,?); INSERT INTO customer.user_password VALUES (?,?)")) {

            statement.setString(1, user.login);
            statement.setString(2, user.email);
            statement.setTimestamp(3, Timestamp.valueOf(user.date));
            statement.setString(4, user.login);
            statement.setString(5, user.password);
            return statement.executeUpdate();
        }
    }

    public static Connection open() {

        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://host.docker.internal:5436/task5db",
                    "konstantin",
                    "kubrak");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
