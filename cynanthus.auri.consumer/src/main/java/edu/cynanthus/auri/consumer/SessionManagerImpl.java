package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.AuthService;
import edu.cynanthus.domain.AuthenticatedUser;
import edu.cynanthus.domain.User;

class SessionManagerImpl implements SessionManager {

    private final User user;
    private final AuthService authService;

    private AuthenticatedUser authenticatedUser;

    SessionManagerImpl(User user, AuthService authService) {
        this.user = user;
        this.authService = authService;
    }

    @Override
    public AuthenticatedUser getAuthenticatedUser() {
        return authenticatedUser;
    }

    @Override
    public void login() {
        authenticatedUser = authService.auth(user);
    }

    @Override
    public void logout() {
        authenticatedUser = null;
    }

}
