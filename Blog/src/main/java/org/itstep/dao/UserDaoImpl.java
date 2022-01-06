package org.itstep.dao;

import lombok.SneakyThrows;
import org.itstep.domain.User;

import java.sql.*;
import java.util.List;
import java.util.Locale;

public class UserDaoImpl implements UserDao{

    @SneakyThrows
    @Override
    public Integer save(User user) {
        Connection connection = MysqlConnection.getInstance().getConnection();
        if (user == null) {
            System.out.println("Ошибка полученных данных");
            return -1;
        }
        String login = "";
        String login_check = "select * from users where login = \"" + user.getLogin() + "\"";

        try (PreparedStatement statement = connection.prepareStatement(login_check)) {
            ResultSet resultSet = statement.executeQuery();
            login = resultSet.getString(4);
            if (login.equals(user.getLogin())) {
                System.err.println("Такой пользователь уже существует , проверьте введенные данные , логин должен быть уникальным");
                return -1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (PreparedStatement statement = connection.prepareStatement(SQLUser.INSERT.QUERY)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getLogin());
            statement.executeQuery();
        } catch (SQLException e) {
            return -1;
        }
        System.out.println("Регистрация прошла успешно");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select last_insert_id() from users");
        if (resultSet.next()) {
            Integer id = resultSet.getInt(1);
            System.out.println(id + " - is ID");
            return id;
        }
        return -1;
    }

    @Override
    public List<User> findAll() {
        return null;
    }


    @Override
    public User update(User data) {
        return null;
    }

    @SneakyThrows
    @Override
    public User findById(Integer integer) {
        Connection connection = MysqlConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?");
        preparedStatement.setInt(1, integer);
        ResultSet set = preparedStatement.executeQuery();
        if (set.next()) {
            Long id = (long) set.getInt(1);
            String firstName = set.getString(2);
            String lastName = set.getString(3);
            String pass = set.getString(4);
            String log = set.getString(5);
            return new User(id, log, pass, firstName, lastName);
        }
        return null;
    }

    @SneakyThrows
    @Override
    public void delete(Integer integer) {
        Connection connection = MysqlConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id = ?");
        preparedStatement.setInt(1, integer);
        preparedStatement.executeQuery();
    }


    @SneakyThrows
    public User getUser(String login, String password) {
        Connection connection = MysqlConnection.getInstance().getConnection();
        long id = 0L;
        String firstName = "";
        String lastName = "";
        String pass = "";
        String log = "";
        PreparedStatement statement = connection.prepareStatement("select * from Users where login =\"" + login.toLowerCase(Locale.ROOT) + "\" and pass=\"" + password + "\"");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            id = (long) resultSet.getInt(1);
            firstName = resultSet.getString(2);
            lastName = resultSet.getString(3);
            pass = resultSet.getString(4);
            log = resultSet.getString(5);
        }
        if (!login.equals(log)) {
            System.out.println("Вход не выполнен");
            return null;
        }
        System.out.println("Вход выполнен");
        return new User(id, log, pass, firstName, lastName);
    }


    enum SQLUser {
        GET("SELECT * FROM users where login"),
        INSERT("INSERT INTO users (firstName, lastName , pass, login) VALUES ((?),(?),(?),(?))");

        String QUERY;

        SQLUser(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
