package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.NodeInfo;

import java.util.List;
import java.util.function.Consumer;

/**
 * El tipo Session based node info service.
 */
class SessionBasedNodeInfoService
    extends SessionBasedBeanService<NodeInfo, NodeInfoService> implements NodeInfoService {

    /**
     * Instancia un nuevo Session based node info service.
     *
     * @param auriServiceConsumer el auri service consumer
     * @param sessionStarter      el session starter
     * @param lazyRequestConsumer el lazy request consumer
     */
    SessionBasedNodeInfoService(
        AuriServiceConsumer<NodeInfoService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

    /**
     * Read all by id server info list.
     *
     * @param idServerInfo el id server info
     * @return el list
     */
    @Override
    public List<? extends NodeInfo> readAllByIdServerInfo(Integer idServerInfo) {
        return consume(service -> service.readAllByIdServerInfo(idServerInfo));
    }

}
