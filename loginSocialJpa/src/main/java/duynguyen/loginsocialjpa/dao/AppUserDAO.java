package duynguyen.loginsocialjpa.dao;

import duynguyen.loginsocialjpa.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AppUserDAO {
    @Autowired
    private EntityManager entityManager;

    /*
    * TODO findUserByUserId(long userId)
    * */
    public AppUser findUserByUserId(long userId) {
        return null;
    }

    /*
    * TODO findUserByUsername(String username)
    * */
    public AppUser findUserByUsername(String username) {
        return null;
    }

    /*
    * TODO findUserByEmail(String email)
    * */
    public AppUser findUserByEmail(String email) {
        return null;
    }

    /*
    * TODO findAvailableByUsername(String username_prefix)
    * */
    public String findAvailableByUsername(String username_prefix) {
        return null;
    }

    /*
    * TODO createUser(Connection<?> connection)
    * */
    public int createUser(Connection<?> connection) {
        return 0;
    }

    /*
    * TODO registerNewUser(AppUser appUser, List<String> roleNames)
    * */
    public int registerNewUser(AppUser appUser, List<String> roleNames) {
        return 0;
    }
}
