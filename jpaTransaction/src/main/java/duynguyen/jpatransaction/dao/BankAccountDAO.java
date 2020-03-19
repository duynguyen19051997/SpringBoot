package duynguyen.jpatransaction.dao;

import duynguyen.jpatransaction.entity.BankAccount;
import duynguyen.jpatransaction.exception.BankAccountException;
import duynguyen.jpatransaction.model.BankAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class BankAccountDAO {
    @Autowired
    private EntityManager entityManager;

    /*
     * Find All Bank Accounts
     * @return list
     */
    public List<BankAccountInfo> findAll() {
        String sql = "Select new " + BankAccountInfo.class.getName()
            + "(id, full_name, balance) "
            + " from " + BankAccount.class.getName();
        TypedQuery<BankAccountInfo> query = entityManager.createQuery(sql, BankAccountInfo.class);
        return query.getResultList();
    }

    /*
     * Find  Bank Account by Id
     * @return { int }
     */
    public BankAccount findById(int id) {
        return this.entityManager.find(BankAccount.class, id);
    }

    public int updateById(BankAccount acc) {
        String sql = "UPDATE " + BankAccount.class.getName() + " SET balance = " + acc.getBalance()
            + ", full_name = '" + acc.getFull_name() + "' WHERE id = " + acc.getId();
        return this.entityManager.createQuery(sql).executeUpdate();
    }

    /*
     * Delete a Bank Account
     * @Param {id}
     * @return {int}
     * */
    public int deleteOneById(int id) {
        String sql = "DELETE FROM " + BankAccount.class.getName() + " WHERE id = " + id;
        return this.entityManager.createQuery(sql).executeUpdate();
    }

    /*
     * Add Amount
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void addAmount(int id, double amount) throws BankAccountException {
        BankAccount account = this.findById(id);
        if (account == null) {
            throw new BankAccountException("Not found by " + id);
        }
        if (account.getBalance() + amount < 0) {
            throw new BankAccountException("Not enough money");
        }
        account.setBalance(account.getBalance() + amount);
        if (this.updateById(account) > 0) {
            System.out.println("Add amount success");
        } else {
            System.out.println("Add amount fail");
        }
    }

    /*
     * Send Money
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankAccountException.class)
    public void sendMoney(int fromId, int toId, double amount) throws BankAccountException {
        addAmount(fromId, -amount);
        addAmount(toId, amount);
    }
}
