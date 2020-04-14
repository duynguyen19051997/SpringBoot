package duynguyen.loginsocialjpa.dao;

import duynguyen.loginsocialjpa.entity.AppRole;
import duynguyen.loginsocialjpa.entity.AppUser;
import duynguyen.loginsocialjpa.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AppRoleDAO {
    @Autowired
    private EntityManager entityManager;


    /*
     *  getRoleNames(long userId)
     *  @param {userId}
     *  @return {List<String>}
     * */
    public List<String> getRoleNames(long userId) {
        String sql = "SELECT ur.appUser.role_name FROM " + UserRole.class.getName() +
            " AS ur WHERE ur.appUser.user_id = :userId";

        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    /*
     *  findRoleByRoleName(String roleName)
     *  @param {roleName}
     *  @return {AppRole}
     * */
    public AppRole findRoleByRoleName(String roleName) {
        try {
            String sql = "SELECT r.* FROM " + AppRole.class.getName() + " AS r WHERE r.role_name = :roleName";
            Query query = this.entityManager.createQuery(sql, AppRole.class);
            query.setParameter("roleName", roleName);
            return (AppRole) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /*
     *  createRoleFor(AppUser appUser, List<String> roleNames)
     *  @param {AppUser, List<String>}
     * */
    public void createRoleFor(AppUser appUser, List<String> roleNames) {
        for (String roleName : roleNames) {
            AppRole role = this.findRoleByRoleName(roleName);
            if (role == null) {
                role = new AppRole();
                role.setRoleName(AppRole.ROLE_USER);
                this.entityManager.persist(role);
            } else {
                UserRole userRole = new UserRole();
                userRole.setAppRole(role);
                userRole.setAppUser(appUser);
                this.entityManager.persist(userRole);
            }
            this.entityManager.flush();
        }
    }
}
