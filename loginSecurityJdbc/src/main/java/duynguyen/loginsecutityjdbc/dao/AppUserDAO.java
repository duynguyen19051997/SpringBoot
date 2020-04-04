package duynguyen.loginsecutityjdbc.dao;

import duynguyen.loginsecutityjdbc.mapper.AppUserMapper;
import duynguyen.loginsecutityjdbc.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Repository
@Transactional
public class AppUserDAO extends JdbcDaoSupport {

    @Autowired
    public AppUserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
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
