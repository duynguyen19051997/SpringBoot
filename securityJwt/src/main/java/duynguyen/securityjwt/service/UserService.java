package duynguyen.securityjwt.service;

import duynguyen.securityjwt.entity.UserEntity;
import duynguyen.securityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
