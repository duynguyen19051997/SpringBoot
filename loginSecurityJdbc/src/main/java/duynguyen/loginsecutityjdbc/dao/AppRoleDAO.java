package duynguyen.loginsecutityjdbc.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class AppRoleDAO extends JdbcDaoSupport {

    public AppRoleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /* getRoleName
     * @param id
     * @return {list<String>}
     * */
    public List<String> getRoleName(int id) {
        String sql = "SELECT FROM user_roles AS ur " +
            "INNER ar.role_name JOIN app_roles AS ar ON ar.role_id = ur.role_id WHERE ur.id = ?";
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().queryForList(sql, new Object[]{id}, String.class);
    }
}
