package edu.cynanthus.auri.consumer;

import edu.cynanthus.domain.AuthenticatedUser;

public interface SessionManager extends SessionStarter {

    AuthenticatedUser getAuthenticatedUser();

    void logout();

}
