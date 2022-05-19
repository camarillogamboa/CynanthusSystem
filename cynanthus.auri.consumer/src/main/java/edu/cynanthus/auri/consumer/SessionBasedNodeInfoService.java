package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.NodeInfo;

import java.util.List;
import java.util.function.Consumer;

class SessionBasedNodeInfoService
    extends SessionBasedBeanService<NodeInfo, NodeInfoService> implements NodeInfoService {

    SessionBasedNodeInfoService(
        AuriServiceConsumer<NodeInfoService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

    @Override
    public List<? extends NodeInfo> readAllByIdServerInfo(Integer idServerInfo) {
        return consume(service -> service.readAllByIdServerInfo(idServerInfo));
    }

}
