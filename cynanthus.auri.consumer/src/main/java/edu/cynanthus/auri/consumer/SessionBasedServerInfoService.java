package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.ServerInfo;

import java.util.function.Consumer;

class SessionBasedServerInfoService
    extends SessionBasedBeanService<ServerInfo, ServerInfoService> implements ServerInfoService {

    SessionBasedServerInfoService(
        AuriServiceConsumer<ServerInfoService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

}
