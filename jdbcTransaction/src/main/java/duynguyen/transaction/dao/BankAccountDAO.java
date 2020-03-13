package duynguyen.transaction.dao;

import duynguyen.transaction.exception.BankTransactionException;
import duynguyen.transaction.mapper.BankAccountMapper;
import duynguyen.transaction.model.BankAccountInfor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class BankAccountDAO {

    private JdbcTemplate jdbcTemplate;

    private static final String GET_BANK_ACCOUNT = "SELECT * FROM BANK_ACCOUNT";
    private static final String FIND_BANK_ACCOUNT = "SELECT * FROM BANK_ACCOUNT WHERE id = ?";
    private static final String UPDATE_BANK_ACCOUNT = "UPDATE BANK_ACCOUNT SET balance = ? WHERE id = ?";

    public List<BankAccountInfor> getBankAccounts() {
        try {
            return jdbcTemplate.query(GET_BANK_ACCOUNT,
                    new BeanPropertyRowMapper<BankAccountInfor>(BankAccountInfor.class));
        } catch (Exception e) {
            return null;
        }
    }

    public BankAccountInfor findBankAccount(int id) {
        try {
            return Objects.requireNonNull(jdbcTemplate).queryForObject(FIND_BANK_ACCOUNT,
                    new Object[]{id}, new BankAccountMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void addAmount(int id, double amount) {
        BankAccountInfor bankAccountInfor = this.findBankAccount(id);
        if (bankAccountInfor == null) {
            try {
                throw new BankTransactionException("Account not found by " + id);
            } catch (BankTransactionException e) {
                e.printStackTrace();
            }
        }
        double newBalance = bankAccountInfor.getBalance() + amount;
        jdbcTemplate.update(UPDATE_BANK_ACCOUNT, id, newBalance);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
    public void sendMoney(int fromAccountId, int toAccountId, int amount) {
        addAmount(toAccountId, amount);
        addAmount(fromAccountId, -amount);
    }

}
