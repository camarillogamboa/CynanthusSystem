package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.CynanthusServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.domain.ServerInfo;

import java.util.Map;
import java.util.function.Supplier;

public class SessionBasedCynanthusServerService<T extends Config, S extends CynanthusServerService<T>>
    extends SessionBasedConfigurationServerService<T, S> implements CynanthusServerService<T> {

    SessionBasedCynanthusServerService(
        AuriServiceConsumer<S> auriServiceConsumer,
        SessionStarter sessionStarter,
        Supplier<Map<String, String>> headersSupplier
    ) {
        super(auriServiceConsumer, sessionStarter, headersSupplier);
    }

    @Override
    public Boolean isAvailable(ServerInfo serverInfo) {
        return consume(service -> service.isAvailable(serverInfo));
    }

}
