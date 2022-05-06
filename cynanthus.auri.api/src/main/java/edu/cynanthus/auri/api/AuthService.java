package edu.cynanthus.auri.api;

import edu.cynanthus.domain.AuthenticatedUser;
import edu.cynanthus.domain.User;

public interface AuthService extends AuriService{

    AuthenticatedUser auth(User user);

}
