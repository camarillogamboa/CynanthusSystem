package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.ServerInfo;

import java.util.function.Consumer;

/**
 * El tipo Session based server info service.
 */
class SessionBasedServerInfoService
    extends SessionBasedBeanService<ServerInfo, ServerInfoService> implements ServerInfoService {

    /**
     * Instancia un nuevo Session based server info service.
     *
     * @param auriServiceConsumer el auri service consumer
     * @param sessionStarter      el session starter
     * @param lazyRequestConsumer el lazy request consumer
     */
    SessionBasedServerInfoService(
        AuriServiceConsumer<ServerInfoService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

}
