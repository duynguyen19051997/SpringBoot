package duynguyen.securityjwt.service;

import duynguyen.securityjwt.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRolesService userRolesService;

    public UserDetails loadUserByUsername(String username) {
        UserEntity userInfo = userService.findUserByUsername(username);
        if (userInfo == null) {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        System.out.println("[User Info]: " + userInfo);

        List<String> roleNames = userRolesService.findRoleName(userInfo.getUserId());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(userInfo.getUsername(), userInfo.getPassword(), grantList);

        return userDetails;
    }
}
