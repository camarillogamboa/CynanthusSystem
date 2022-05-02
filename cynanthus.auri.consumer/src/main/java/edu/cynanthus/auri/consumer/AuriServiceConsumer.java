package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.AuriService;
import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.common.net.ConnectionPoint;

import java.util.Map;

public interface AuriServiceConsumer<T extends AuriService> {

    T prepare(Map<String, String> headers);

    T prepare(String headerName, String headerValue);

    static AuriServiceConsumer<ServerInfoService> createServerInfoServiceConsumer(ConnectionPoint connectionPoint) {
        return new AuriServiceConsumerImpl<>(connectionPoint, ServerInfoServiceConsumer::new);
    }

    static AuriServiceConsumer<NodeInfoService> createNodeInfoServiceConsumer(ConnectionPoint connectionPoint) {
        return new AuriServiceConsumerImpl<>(connectionPoint, NodeInfoServiceConsumer::new);
    }

}
