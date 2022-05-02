package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.SordidusServerService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.config.SordidusConfig;

class SordidusServerServiceConsumer
    extends CynanthusServerServiceConsumer<SordidusConfig> implements SordidusServerService {

    SordidusServerServiceConsumer(LazyRequest clientInfo) {
        super(clientInfo, "/cynanthus/auri/server/sordidus", SordidusConfig.class);
    }

}
