package edu.cynanthus.auri.server.security.url;

import edu.cynanthus.auri.api.SordidusServerService;

public class SordidusServerURLSecurer extends CynanthusServerURLSecurer {

    public SordidusServerURLSecurer() {
        super(SordidusServerService.SORDIDUS_SERVER_SERVICE_PATH);
    }

}
