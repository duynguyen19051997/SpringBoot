package duynguyen.loginsecutityjdbc.mapper;

import duynguyen.loginsecutityjdbc.model.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppUserMapper implements RowMapper<AppUser> {
    public static final String FIND_USER = "SELECT u.id, u.username, u.password  FROM app_user AS u ";

    @Override
    public AppUser mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");

        return new AppUser(id, username, password);
    }
}
