package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.TreeServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.RuntimeNode;
import edu.cynanthus.domain.ServerInfo;

import java.util.List;
import java.util.function.Consumer;

/**
 * El tipo Session based tree server service.
 *
 * @param <T> el parámetro de tipo
 * @param <N> el parámetro de tipo
 * @param <S> el parámetro de tipo
 */
class SessionBasedTreeServerService<T extends Config, N extends RuntimeNode, S extends TreeServerService<T, N>>
    extends SessionBasedCynanthusServerService<T, S> implements TreeServerService<T, N> {

    /**
     * Instancia un nuevo Session based tree server service.
     *
     * @param auriServiceConsumer el auri service consumer
     * @param sessionStarter      el session starter
     * @param lazyRequestConsumer el lazy request consumer
     */
    SessionBasedTreeServerService(
        AuriServiceConsumer<S> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

    /**
     * Permite obtener general nodes of.
     *
     * @param serverInfo el server info
     * @param selector   el selector
     * @return el general nodes of
     */
    @Override
    public List<GeneralNode<N>> getGeneralNodesOf(ServerInfo serverInfo, String selector) {
        return consume(service -> service.getGeneralNodesOf(serverInfo, selector));
    }

}
