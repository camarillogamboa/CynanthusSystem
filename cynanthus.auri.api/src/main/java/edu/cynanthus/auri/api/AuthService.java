package edu.cynanthus.auri.api;

import edu.cynanthus.domain.AuthenticatedUser;
import edu.cynanthus.domain.User;

/**
 * La interface Auth service.
 */
public interface AuthService extends AuriService {

    /**
     * Auth authenticated user.
     *
     * @param user el user
     * @return el authenticated user
     */
    AuthenticatedUser auth(User user);

}
