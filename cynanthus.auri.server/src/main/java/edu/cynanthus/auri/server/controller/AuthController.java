package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.AuthService;
import edu.cynanthus.bean.Required;
import edu.cynanthus.domain.AuthenticatedUser;
import edu.cynanthus.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthService.AUTH_SERVICE_PATH)
public class AuthController implements AuthService {

    private final AuthService authService;

    public AuthController(@Qualifier("basicAuthService") AuthService authService) {
        this.authService = authService;
    }

    @Override
    @PostMapping("/login")
    public AuthenticatedUser auth(@RequestBody @Validated(Required.class) User user) {
        return authService.auth(user);
    }

}
