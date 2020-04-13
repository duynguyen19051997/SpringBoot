package duynguyen.loginsecutityjdbc.dao;

import duynguyen.loginsecutityjdbc.mapper.AppUserMapper;
import duynguyen.loginsecutityjdbc.model.AppUser;
import duynguyen.loginsecutityjdbc.utils.EncrytedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
@Transactional
public class AppUserDAO extends JdbcDaoSupport {

    @Autowired
    public AppUserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public static final String ADD_NEW_USER = "INSERT INTO app_users(username, password, enable) VALUE (?,?,?)";

    /* Insert New User
     * @param {AppUser}
     * @return {id}
     * */
    public BigInteger insertNewUser(AppUser appUser) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection
                .prepareStatement(ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, appUser.getUsername());
            ps.setString(2, EncrytedPasswordUtils.encrytePassword(appUser.getPassword()));
            ps.setByte(3, (byte) 1);
            return ps;
        }, keyHolder);

        return (BigInteger) keyHolder.getKey();
    }

    /*
     *findUserByUsername
     *@params(username)
     *@return Object{AppUser}
     * */
    public AppUser findUserByUsername(String username) {
        AppUserMapper appUserMapper = new AppUserMapper();
        String sql = AppUserMapper.FIND_USER + " WHERE u.username = ?";
        try {
            assert this.getJdbcTemplate() != null;
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{username}, appUserMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
