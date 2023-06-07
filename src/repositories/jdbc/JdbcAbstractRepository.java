package repositories.jdbc;

import exceptions.JdbcException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class JdbcAbstractRepository<T> {

    protected abstract String getJdbcUrl();
    protected abstract String getJdbcUser();
    protected abstract String getJdbcPassword();


    protected T queryForObject(String sql, Function<ResultSet, T> mapper, Object... params) {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(getJdbcUrl(), getJdbcUser(), getJdbcPassword());
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 1; i <= params.length; i++) {
                statement.setObject(i, params[i-1]);
            }
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapper.apply(resultSet);
            }
            if (connection != null) {
                try {
                    connection.close();
                }catch (SQLException e) {
                    throw new JdbcException(e);
                }
            }
            return null;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    protected List<T> query(String sql, Function<ResultSet, T> mapper, Object... params) {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(getJdbcUrl(), getJdbcUser(), getJdbcPassword());
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 1; i <= params.length; i++) {
                statement.setObject(i, params[i-1]);
            }
            ResultSet resultSet = statement.executeQuery();

            List<T> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(mapper.apply(resultSet));
            }
            if (connection != null) {
                try {
                    connection.close();
                }catch (SQLException e) {
                    throw new JdbcException(e);
                }
            }
            return list;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    protected void execute(String sql, Object... params) {
        Connection connection = null;
        try {

            connection = DriverManager.getConnection(getJdbcUrl(), getJdbcUser(), getJdbcPassword());
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 1; i <= params.length; i++) {
                statement.setObject(i, params[i-1]);
            }
            statement.executeUpdate();
            if (connection != null) {
                try {
                    connection.close();
                }catch (SQLException e) {
                    throw new JdbcException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
