package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.AuthService;
import edu.cynanthus.auri.api.exception.InvalidArgumentException;
import edu.cynanthus.auri.server.security.JwtTokenProvider;
import edu.cynanthus.domain.AuthenticatedUser;
import edu.cynanthus.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BasicAuthService implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public BasicAuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AuthenticatedUser auth(User user) {
        if (user == null)
            throw new InvalidArgumentException("El objeto usuario no debe ser nulo");

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        System.out.println("TOKEN GENERADO: " + token);

        return new AuthenticatedUser(user.getUsername(), user.getPassword(), token);
    }

}
