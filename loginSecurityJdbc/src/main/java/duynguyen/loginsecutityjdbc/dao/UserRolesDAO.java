package duynguyen.loginsecutityjdbc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;

@Repository
public class UserRolesDAO extends JdbcDaoSupport {
    @Autowired
    public UserRolesDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    private static final String ADD_USER_ROLES = "INSERT INTO user_roles(user_id, role_id) VALUE (?,?)";

    /*
     * Insert User Roles
     * @param {userID}
     * @return {int}
     *  */
    public int insertUserRoles(BigInteger userId) {
        return this.getJdbcTemplate().update(ADD_USER_ROLES, userId, 2);
    }
}
