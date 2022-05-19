package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.CynanthusServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.ServerInfo;

import java.util.function.Consumer;

public class SessionBasedCynanthusServerService<T extends Config, S extends CynanthusServerService<T>>
    extends SessionBasedConfigurationServerService<T, S> implements CynanthusServerService<T> {

    SessionBasedCynanthusServerService(
        AuriServiceConsumer<S> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

    @Override
    public Boolean isAvailable(ServerInfo serverInfo) {
        return consume(service -> service.isAvailable(serverInfo));
    }

}
