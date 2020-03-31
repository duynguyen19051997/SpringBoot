package duynguyen.hibernatetransaction.dao;

import duynguyen.hibernatetransaction.entity.BankAccount;
import duynguyen.hibernatetransaction.exception.BankTransactionException;
import duynguyen.hibernatetransaction.model.BankAccountInfo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BankAccountDAO {
    @Autowired
    private SessionFactory sessionFactory;

    /* findById
     * @param {id}
     * @return {BankAccount}
     */
    public BankAccount findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(BankAccount.class, id);
    }

    /* findAll
     */
    public List<BankAccountInfo> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<BankAccountInfo>) session.get(BankAccount.class, "");
    }

    /* updateById
     * @param {BankAccountInfo}
     * @return {int}
     */
    public int updateById(BankAccount acc) {
        String sql = "UPDATE " + BankAccount.class.getName() + " SET full_name = '" + acc.getFull_name() + "', balance = "
            + acc.getBalance() + " WHERE id = " + acc.getId();
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery(sql).executeUpdate();
    }

    /* deleteById
     * @param {id}
     * @return {int}
     * */
    public int deleteOneById(int id) {
        String sql = "DELETE FROM " + BankAccount.class.getName() + " WHERE id = " + id;
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery(sql).executeUpdate();
    }

    //TODO insertOne

    /* addAmount
     * */
    @Transactional(propagation = Propagation.MANDATORY)
    public void addAmount(int id, double amount) throws BankTransactionException {
        BankAccount account = this.findById(id);
        if (account == null) {
            throw new BankTransactionException("Not found by " + id);
        }
        if (account.getBalance() + amount < 0) {
            throw new BankTransactionException("Not enough money");
        }
        account.setBalance(account.getBalance() + amount);
        if (this.updateById(account) > 0) {
            System.out.println("Add amount success");
        } else {
            System.out.println("Add amount fail");
        }
    }

    /* sendMoney
     * */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
    public void sendMoney(int fromId, int toId, double amount) throws BankTransactionException {
        addAmount(fromId, -amount);
        addAmount(toId, amount);
    }
}
