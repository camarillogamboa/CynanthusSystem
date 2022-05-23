package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.UserService;
import edu.cynanthus.auri.server.entity.UserEntity;
import edu.cynanthus.auri.server.security.SpringUser;
import edu.cynanthus.domain.Role;
import edu.cynanthus.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("userDetailsService")
public class BasicUserDetailsService implements UserDetailsService {

    private final User auriUser;
    private final UserService userService;

    @Autowired
    public BasicUserDetailsService(
        User auriUser,
        @Qualifier("transactionalUserService") UserService userService
    ) {
        this.auriUser = auriUser;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (auriUser.getUsername().equals(username)) {
            return domainUserToUserDetails(auriUser);
        } else {
            User user = new UserEntity();
            user.setUsername(username);
            try {
                user = userService.read(user);
                return domainUserToUserDetails(user);
            } catch (Exception ex) {
                throw new UsernameNotFoundException(username, ex);
            }
        }
    }


    private UserDetails domainUserToUserDetails(User user) {
        List<GrantedAuthority> roles = new LinkedList<>();

        for (Role role : user.getRoles())
            roles.add(new SimpleGrantedAuthority(role.getRoleType().name()));

        return new SpringUser(user.getUsername(), user.getPassword(), roles);
    }

}
