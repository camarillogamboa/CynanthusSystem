package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.SordidusServerService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.config.SordidusConfig;

class SordidusServerServiceConsumer
    extends CynanthusServerServiceConsumer<SordidusConfig> implements SordidusServerService {

    SordidusServerServiceConsumer(LazyRequest lazyRequest) {
        super(lazyRequest, SORDIDUS_SERVER_SERVICE_PATH, SordidusConfig.class);
    }

}
