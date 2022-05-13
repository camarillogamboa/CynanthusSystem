package edu.cynanthus.dayi.service;

import edu.cynanthus.auri.api.UserService;
import edu.cynanthus.auri.api.exception.WebServiceException;
import edu.cynanthus.auri.consumer.AuriSession;
import edu.cynanthus.bean.ErrorMessage;
import edu.cynanthus.common.net.http.HttpStatusCode;
import edu.cynanthus.domain.Role;
import edu.cynanthus.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final UserService userService;

    @Autowired
    public BasicUserDetailsService(AuriSession auriSession) {
        this.userService = auriSession.userService();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User(username);
        try {
            user = userService.read(user);
        } catch (WebServiceException webServiceException) {
            ErrorMessage<String> errorMessage = webServiceException.getErrorMessage();
            if (errorMessage.getCode() == HttpStatusCode.NOT_FOUND) {
                throw new UsernameNotFoundException(username, webServiceException);
            }
        }

        List<GrantedAuthority> roles = new LinkedList<>();

        for (Role role : user.getRoles())
            roles.add(new SimpleGrantedAuthority(role.getRoleType().name()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }

}
