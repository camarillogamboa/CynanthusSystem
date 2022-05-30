package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.AuthService;
import edu.cynanthus.domain.AuthenticatedUser;
import edu.cynanthus.domain.User;

/**
 * El tipo Session manager.
 */
class SessionManagerImpl implements SessionManager {

    /**
     * El User.
     */
    private final User user;
    /**
     * El Auth service.
     */
    private final AuthService authService;

    /**
     * El Authenticated user.
     */
    private AuthenticatedUser authenticatedUser;

    /**
     * Instancia un nuevo Session manager.
     *
     * @param user        el user
     * @param authService el auth service
     */
    SessionManagerImpl(User user, AuthService authService) {
        this.user = user;
        this.authService = authService;
    }

    /**
     * Permite obtener authenticated user.
     *
     * @return el authenticated user
     */
    @Override
    public AuthenticatedUser getAuthenticatedUser() {
        return authenticatedUser;
    }

    /**
     * Login.
     */
    @Override
    public void login() {
        authenticatedUser = authService.auth(user);
    }

    /**
     * Logout.
     */
    @Override
    public void logout() {
        authenticatedUser = null;
    }

}
