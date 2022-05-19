package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.SordidusServerService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.config.SordidusConfig;

import java.util.function.Consumer;

class SessionBasedSordidusServerService
    extends SessionBasedCynanthusServerService<SordidusConfig, SordidusServerService> implements SordidusServerService {

    SessionBasedSordidusServerService(
        AuriServiceConsumer<SordidusServerService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

}
