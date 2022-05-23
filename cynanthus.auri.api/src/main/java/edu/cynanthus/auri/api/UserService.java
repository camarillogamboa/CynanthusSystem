package edu.cynanthus.auri.api;

import edu.cynanthus.domain.User;

/**
 * La interface User service.
 */
public interface UserService extends BeanService<User> {

    /**
     * La constante USER_SERVICE_PATH.
     */
    String USER_SERVICE_PATH = AURI_PATH + "/user";

}
