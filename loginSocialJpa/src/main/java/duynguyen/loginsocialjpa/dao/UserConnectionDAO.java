package duynguyen.loginsocialjpa.dao;

import duynguyen.loginsocialjpa.entity.UserConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserConnectionDAO {
    @Autowired
    private EntityManager entityManager;

    /*
     *  findUserConnectionByUserProviderId(String userProviderId)
     *  @param {userProviderId}
     *  @return {UserConnection}
     * */
    public UserConnection findUserConnectionByUserProviderId(String userProviderId) {
        try {
            String sql = "SELECT uc.* FROM " + UserConnection.class.getName() +
                " AS uc WHERE uc.user_provider_id = :userProviderId";
            Query query = this.entityManager.createQuery(sql, UserConnection.class);
            query.setParameter("userProviderId", userProviderId);
            List<UserConnection> list = query.getResultList();
            for (UserConnection uc : list) {
                System.out.println(uc);
            }
            return list.isEmpty() ? null : list.get(0);
        } catch (NoResultException e) {
            return null;
        }
    }
}
