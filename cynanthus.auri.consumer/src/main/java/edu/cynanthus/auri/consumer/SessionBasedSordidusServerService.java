package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.SordidusServerService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.config.SordidusConfig;

import java.util.function.Consumer;

/**
 * El tipo Session based sordidus server service.
 */
class SessionBasedSordidusServerService
    extends SessionBasedCynanthusServerService<SordidusConfig, SordidusServerService> implements SordidusServerService {

    /**
     * Instancia un nuevo Session based sordidus server service.
     *
     * @param auriServiceConsumer el auri service consumer
     * @param sessionStarter      el session starter
     * @param lazyRequestConsumer el lazy request consumer
     */
    SessionBasedSordidusServerService(
        AuriServiceConsumer<SordidusServerService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

}
