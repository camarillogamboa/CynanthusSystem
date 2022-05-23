package edu.cynanthus.auri.server.security.url;

import edu.cynanthus.auri.api.LatiroServerService;

public class LatiroServerURLSecurer extends TreeServerURLSecurer {

    public LatiroServerURLSecurer() {
        super(LatiroServerService.LATIRO_SERVER_SERVICE_PATH);
    }

}
