package se.Mapping;

import org.springframework.jdbc.core.RowMapper;
import se.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapping implements RowMapper<User> {


    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(resultSet.getString("role"));
        return user;
    }
}