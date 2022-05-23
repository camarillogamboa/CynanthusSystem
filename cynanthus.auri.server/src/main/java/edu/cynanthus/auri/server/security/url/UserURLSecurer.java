package edu.cynanthus.auri.server.security.url;

import edu.cynanthus.auri.api.UserService;

public class UserURLSecurer extends BeanURLSecurer {

    public UserURLSecurer() {
        super(UserService.USER_SERVICE_PATH);
    }

}
