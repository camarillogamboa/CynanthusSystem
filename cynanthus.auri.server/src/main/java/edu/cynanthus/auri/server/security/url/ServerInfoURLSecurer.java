package edu.cynanthus.auri.server.security.url;

import edu.cynanthus.auri.api.ServerInfoService;

public class ServerInfoURLSecurer extends BeanURLSecurer {

    public ServerInfoURLSecurer() {
        super(ServerInfoService.SERVER_INFO_SERVICE_PATH);
    }

}
