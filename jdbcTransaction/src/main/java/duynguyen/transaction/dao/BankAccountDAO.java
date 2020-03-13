package duynguyen.transaction.dao;

import duynguyen.transaction.mapper.BankAccountMapper;
import duynguyen.transaction.model.BankAccountInfor;
import jdk.nashorn.internal.runtime.PropertyDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.activation.DataSource;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class BankAccountDAO extends JdbcDaoSupport {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public BankAccountDAO(DataSource dataSource) {
        this.setDataSource((javax.sql.DataSource) dataSource);
    }

    private static final String GET_BANK_ACCOUNT = "SELECT ID, FULL_NAME, BALANCE FROM BANK_ACCOUNT";

    public List<BankAccountInfor> getBankAccounts () {
        BankAccountMapper bankAccountMapper = new BankAccountMapper();
        return Objects.requireNonNull(this.getJdbcTemplate())
                .query(GET_BANK_ACCOUNT, new Object[] {}, bankAccountMapper);
    }

    public BankAccountInfor findBankAccount(int id) {
        try {
            return Objects.requireNonNull(this.getJdbcTemplate()).queryForObject(GET_BANK_ACCOUNT + "WHERE ID = ?",
                    new Object[] {id}, new BankAccountMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
