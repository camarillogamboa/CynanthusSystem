package edu.cynanthus.auri.api;

import edu.cynanthus.domain.User;

/**
 * La interface User service.
 */
public interface UserService extends BeanService<User> {

    String USER_SERVICE_PATH = AURI_PATH + "/user";

}
