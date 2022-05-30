package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.SordidusServerService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.config.SordidusConfig;

/**
 * El tipo Sordidus server service consumer.
 */
class SordidusServerServiceConsumer
    extends CynanthusServerServiceConsumer<SordidusConfig> implements SordidusServerService {

    /**
     * Instancia un nuevo Sordidus server service consumer.
     *
     * @param lazyRequest el lazy request
     */
    SordidusServerServiceConsumer(LazyRequest lazyRequest) {
        super(lazyRequest, SORDIDUS_SERVER_SERVICE_PATH, SordidusConfig.class);
    }

}
