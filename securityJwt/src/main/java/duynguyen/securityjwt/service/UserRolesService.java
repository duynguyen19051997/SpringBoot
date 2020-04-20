package duynguyen.securityjwt.service;

import duynguyen.securityjwt.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRolesService {

    @Autowired
    private UserRolesRepository userRolesRepository;

    public List<String> findRoleName(int userId) {
        return userRolesRepository.findRoleName(userId);
    }
}
