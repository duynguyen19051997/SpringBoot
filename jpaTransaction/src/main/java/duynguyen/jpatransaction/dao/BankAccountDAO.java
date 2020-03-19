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
     * Find  Bank Account by Id
     * @return Object
     */
    public BankAccount findById(int id) {
        return this.entityManager.find(BankAccount.class, id);
    }

    public BankAccount updateById(BankAccount bankAccountInfo) {
        String sql = "UPDATE " + BankAccount.class.getName() + " SET balance = " + bankAccountInfo.getBalance()
            + " WHERE id = " + bankAccountInfo.getId();
        return (BankAccount) this.entityManager.createQuery(sql,
            BankAccountInfo.class);
    }

    /*
     * Find All Bank Accounts
     * @return list
     */
    public List<BankAccountInfo> findAll() {
        String sql = "Select new " + BankAccountInfo.class.getName() //
            + "(e.id,e.full_name,e.balance) " //
            + " from " + BankAccount.class.getName() + " e ";
        TypedQuery<BankAccountInfo> query = entityManager.createQuery(sql, BankAccountInfo.class);
        return query.getResultList();
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
        if (this.updateById(account) != null) {
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
