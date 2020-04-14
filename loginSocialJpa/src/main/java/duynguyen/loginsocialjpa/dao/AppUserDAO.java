package duynguyen.loginsocialjpa.dao;

import duynguyen.loginsocialjpa.entity.AppRole;
import duynguyen.loginsocialjpa.entity.AppUser;
import duynguyen.loginsocialjpa.form.AppUserForm;
import duynguyen.loginsocialjpa.utils.EncrytedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class AppUserDAO {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AppRoleDAO appRoleDAO;

    /*
     * findUserByUserId(long userId)
     * @param {userId}
     * @return {AppUser}
     * */
    public AppUser findUserByUserId(long userId) {
        try {
            String sql = "SELECT u.* FROM " + AppUser.class.getName() + " AS u WHERE u.userId = :userId";
            Query query = this.entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userId", userId);
            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /*
     * findUserByUsername(String username)
     * @param {username}
     * @return {AppUser}
     * */
    public AppUser findUserByUsername(String username) {
        try {
            String sql = "SELECT u.* FROM " + AppUser.class.getName() + " AS u WHERE u.username = :username";
            Query query = this.entityManager.createQuery(sql, AppUser.class);
            query.setParameter("username", username);
            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /*
     *  findUserByEmail(String email)
     *  @param  {email}
     *  @return {AppUser}
     * */
    public AppUser findUserByEmail(String email) {
        try {
            String sql = "SELECT u.* FROM " + AppUser.class.getName() + " AS u WHERE u.email = :email";
            Query query = this.entityManager.createQuery(sql, AppUser.class);
            query.setParameter("email", email);
            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /*
     *  findAvailableByUsername(String username_prefix)
     *  @param {username_prefix}
     *  @return {username}
     * */
    public String findAvailableByUsername(String username_prefix) {
        AppUser account = this.findUserByUsername(username_prefix);
        if (account == null) {
            return username_prefix;
        }
        int i = 0;
        while (true) {
            String username = username_prefix + "_" + i++;
            account = this.findUserByUsername(username);
            if (account == null) {
                return username;
            }
        }
    }

    /*
     *  createUser(Connection<?> connection)
     *  @return {AppUser}
     * */
    public AppUser createUser(Connection<?> connection) {
        ConnectionKey key = connection.getKey();
        // (facebook,12345), (google,123) ...

        System.out.println("key= (" + key.getProviderId() + "," + key.getProviderUserId() + ")");

        UserProfile userProfile = connection.fetchUserProfile();

        String email = userProfile.getEmail();
        AppUser appUser = this.findUserByEmail(email);
        if (appUser != null) {
            return appUser;
        }
        String userName_prefix = userProfile.getFirstName().trim().toLowerCase()//
            + "_" + userProfile.getLastName().trim().toLowerCase();

        String userName = this.findAvailableByUsername(userName_prefix);
        //
        // Random Password! TODO: Need send email to User!
        //
        String randomPassword = UUID.randomUUID().toString().substring(0, 5);
        String encrytedPassword = EncrytedPasswordUtils.encrytePassword(randomPassword);
        //
        appUser = new AppUser();
        appUser.setEnabled(true);
        appUser.setPassword(encrytedPassword);
        appUser.setUsername(userName);
        appUser.setEmail(email);
        appUser.setFirstName(userProfile.getFirstName());
        appUser.setLastName(userProfile.getLastName());

        this.entityManager.persist(appUser);

        // Create default Role
        List<String> roleNames = new ArrayList<String>();
        roleNames.add(AppRole.ROLE_USER);
        this.appRoleDAO.createRoleFor(appUser, roleNames);

        return appUser;
    }

    /*
     *  registerNewUser(AppUser appUser, List<String> roleNames)
     *  @return {AppUser}
     * */
    public AppUser registerNewUser(AppUserForm appUserForm, List<String> roleNames) {
        AppUser appUser = new AppUser();
        appUser.setUsername(appUserForm.getUserName());
        appUser.setEmail(appUserForm.getEmail());
        appUser.setFirstName(appUserForm.getFirstName());
        appUser.setLastName(appUserForm.getLastName());
        appUser.setEnabled(true);
        String encrytedPassword = EncrytedPasswordUtils.encrytePassword(appUserForm.getPassword());
        appUser.setPassword(encrytedPassword);
        this.entityManager.persist(appUser);
        this.entityManager.flush();

        this.appRoleDAO.createRoleFor(appUser, roleNames);

        return appUser;
    }
}
