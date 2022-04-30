package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.SordidusServerService;
import edu.cynanthus.common.net.ClientInfo;
import edu.cynanthus.domain.config.SordidusConfig;

class SordidusServerServiceConsumer
    extends CynanthusServerServiceConsumer<SordidusConfig> implements SordidusServerService {

    SordidusServerServiceConsumer(ClientInfo clientInfo) {
        super(clientInfo, "/cynanthus/auri/server/sordidus", SordidusConfig.class);
    }

}
