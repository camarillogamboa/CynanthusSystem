package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.TreeServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.RuntimeNode;
import edu.cynanthus.domain.ServerInfo;

import java.util.List;
import java.util.function.Consumer;

class SessionBasedTreeServerService<T extends Config, N extends RuntimeNode, S extends TreeServerService<T, N>>
    extends SessionBasedCynanthusServerService<T, S> implements TreeServerService<T, N> {

    SessionBasedTreeServerService(
        AuriServiceConsumer<S> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

    @Override
    public List<GeneralNode<N>> getGeneralNodesOf(ServerInfo serverInfo, String selector) {
        return consume(service -> service.getGeneralNodesOf(serverInfo, selector));
    }

}
