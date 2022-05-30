package edu.cynanthus.auri.consumer;

import edu.cynanthus.domain.AuthenticatedUser;

/**
 * La interface Session manager.
 */
public interface SessionManager extends SessionStarter {

    /**
     * Permite obtener authenticated user.
     *
     * @return el authenticated user
     */
    AuthenticatedUser getAuthenticatedUser();

    /**
     * Logout.
     */
    void logout();

}
