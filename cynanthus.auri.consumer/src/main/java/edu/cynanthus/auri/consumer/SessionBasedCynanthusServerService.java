package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.CynanthusServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.ServerInfo;

import java.util.function.Consumer;

/**
 * El tipo Session based cynanthus server service.
 *
 * @param <T> el parámetro de tipo
 * @param <S> el parámetro de tipo
 */
public class SessionBasedCynanthusServerService<T extends Config, S extends CynanthusServerService<T>>
    extends SessionBasedConfigurationServerService<T, S> implements CynanthusServerService<T> {

    /**
     * Instancia un nuevo Session based cynanthus server service.
     *
     * @param auriServiceConsumer el auri service consumer
     * @param sessionStarter      el session starter
     * @param lazyRequestConsumer el lazy request consumer
     */
    SessionBasedCynanthusServerService(
        AuriServiceConsumer<S> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

    /**
     * Is available boolean.
     *
     * @param serverInfo el server info
     * @return el boolean
     */
    @Override
    public Boolean isAvailable(ServerInfo serverInfo) {
        return consume(service -> service.isAvailable(serverInfo));
    }

}
