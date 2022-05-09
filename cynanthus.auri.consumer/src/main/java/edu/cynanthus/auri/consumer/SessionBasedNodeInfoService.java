package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.domain.NodeInfo;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

class SessionBasedNodeInfoService
    extends SessionBasedBeanService<NodeInfo, NodeInfoService> implements NodeInfoService {

    SessionBasedNodeInfoService(
        AuriServiceConsumer<NodeInfoService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Supplier<Map<String, String>> headersSupplier
    ) {
        super(auriServiceConsumer, sessionStarter, headersSupplier);
    }

    @Override
    public List<? extends NodeInfo> readAllByIdServerInfo(Integer idServerInfo) {
        return consume(service -> service.readAllByIdServerInfo(idServerInfo));
    }

}
