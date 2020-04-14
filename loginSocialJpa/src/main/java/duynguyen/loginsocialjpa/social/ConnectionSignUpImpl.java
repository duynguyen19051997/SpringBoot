package duynguyen.loginsocialjpa.social;

import duynguyen.loginsocialjpa.dao.AppUserDAO;
import duynguyen.loginsocialjpa.entity.AppUser;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

public class ConnectionSignUpImpl implements ConnectionSignUp {
    private final AppUserDAO appUserDAO;

    public ConnectionSignUpImpl(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }

    @Override
    public String execute(Connection<?> connection) {
        AppUser account = appUserDAO.createUser(connection);
        return account.getUsername();
    }
}
