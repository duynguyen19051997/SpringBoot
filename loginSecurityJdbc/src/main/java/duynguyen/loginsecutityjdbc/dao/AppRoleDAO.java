package duynguyen.loginsecutityjdbc.dao;

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
        String sql = "SELECT ar.role_name FROM app_roles AS ar INNER JOIN user_roles AS ur ON ur.role_id = ar.role_id WHERE ur.user_id = ?";
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().queryForList(sql, new Object[]{id}, String.class);
    }
}
