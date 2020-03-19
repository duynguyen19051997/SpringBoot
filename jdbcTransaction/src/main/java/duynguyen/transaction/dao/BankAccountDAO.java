package duynguyen.transaction.dao;

import duynguyen.transaction.exception.BankTransactionException;
import duynguyen.transaction.mapper.BankAccountMapper;
import duynguyen.transaction.model.BankAccountInfor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class BankAccountDAO extends JdbcDaoSupport {

    @Autowired
    public BankAccountDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    private static final String GET_BANK_ACCOUNT = "SELECT * FROM BANK_ACCOUNT";
    private static final String FIND_BANK_ACCOUNT = "SELECT * FROM BANK_ACCOUNT WHERE id = ?";
    private static final String UPDATE_BANK_ACCOUNT = "UPDATE BANK_ACCOUNT SET balance = ? WHERE id = ?";

    /*
     *  get Bank Accounts
     * @return list
     */
    public List<BankAccountInfor> getBankAccounts() {
        Object[] params = new Object[]{};
        BankAccountMapper mapper = new BankAccountMapper();
        return this.getJdbcTemplate() != null ? this.getJdbcTemplate().
            query(GET_BANK_ACCOUNT, params, mapper) : null;
    }

    /*
     *  find Bank Account
     * @param { id }
     * @return { BankAccountInfor }
     */
    public BankAccountInfor findBankAccount(int id) {
        return this.getJdbcTemplate() != null ? this.getJdbcTemplate().
            queryForObject(FIND_BANK_ACCOUNT, new Object[]{id}, new BankAccountMapper()) : null;
    }

    /*
     *  add amount to bank account
     * @param {id, amount}
     * */
    @Transactional(propagation = Propagation.MANDATORY)
    public void addAmount(int id, double amount) throws BankTransactionException {
        BankAccountInfor infor = this.findBankAccount(id);
        if (infor == null) {
            throw new BankTransactionException("Not found Account by " + id);
        } else {
            double newBalance = infor.getBalance() + amount;
            if (newBalance < 0) {
                throw new BankTransactionException(
                    "The money in the account '" + id + "' is not enough (" + infor.getBalance() + ")");
            } else {
                infor.setBalance(newBalance);
                assert this.getJdbcTemplate() != null;
                this.getJdbcTemplate().update(UPDATE_BANK_ACCOUNT, infor.getBalance(), infor.getId());
            }
        }
    }

    /*
     *  send amount from bank account 1 to bank account 2
     * @param {fromId, toId, amount}
     * */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
    public void sendAmount(int fromId, int toId, double amount) throws BankTransactionException {
        addAmount(fromId, -amount);
        addAmount(toId, amount);
    }

}
